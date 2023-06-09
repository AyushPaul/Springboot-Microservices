package com.ayush.service;

import com.ayush.dto.InventoryResponse;
import com.ayush.dto.OrderLineItemsDto;
import com.ayush.dto.OrderRequest;
import com.ayush.event.OrderPlacedEvent;
import com.ayush.model.Order;
import com.ayush.model.OrderLineItems;
import com.ayush.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final ApplicationEventPublisher applicationEventPublisher;

    private final KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;

    public String placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        log.info(orderRequest.toString());


        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get().
                uri("http://inventory-service/api/inventory",uriBuilder -> uriBuilder.queryParam("skuCodes",skuCodes).build())
                .retrieve().bodyToMono(InventoryResponse[].class).block();

        boolean allProducts = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::getIsInStock);
        if(allProducts)
        {
            orderRepository.save(order);
            applicationEventPublisher.publishEvent(new OrderPlacedEvent(this, order.getOrderNumber()));
            kafkaTemplate.send("notificationTopic",new OrderPlacedEvent(order.getOrderNumber()));
            return "Order Placed Successfully";
        }
        else {
            throw new IllegalArgumentException("Product Not in Stock, Plz try again Later");
        }

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
