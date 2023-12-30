package com.orderservice.OrderService.service;

import com.orderservice.OrderService.dto.InventoryResponse;
import com.orderservice.OrderService.dto.OrderItemsDto;
import com.orderservice.OrderService.dto.OrderRequest;
import com.orderservice.OrderService.model.Order;
import com.orderservice.OrderService.model.OrderItems;
import com.orderservice.OrderService.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private final WebClient webClient;
    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderItems> orderItems = orderRequest.getOrderItemsDtoList().stream().map(this::mapToOrderItems).toList();
        order.setOrderItems(orderItems);

        List<String> skuCodes = order.getOrderItems().stream()
                .map(OrderItems::getSkuCode)
                .toList();

        // Call Inventory Service, and place order if product is in
        // stock

        InventoryResponse[] inventoryResponses = webClient.get()
                .uri("http://localhost:8082/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponses)
                .allMatch(InventoryResponse::isInStock);


        if(allProductsInStock){
            orderRepository.save(order);
        }else{
            throw new IllegalArgumentException("Product is not in stock ! Please try again later !!");
        }




    }

    public OrderItems mapToOrderItems(OrderItemsDto orderItemsDto){
        OrderItems orderItems = new OrderItems();
        orderItems.setPrice(orderItemsDto.getPrice());
        orderItems.setQuantity(orderItemsDto.getQuantity());
        orderItems.setSkuCode(orderItemsDto.getSkuCode());
        return orderItems;

    }
}
