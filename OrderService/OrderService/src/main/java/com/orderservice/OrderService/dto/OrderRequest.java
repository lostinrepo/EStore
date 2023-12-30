package com.orderservice.OrderService.dto;

import com.orderservice.OrderService.model.OrderItems;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private List<OrderItemsDto> orderItemsDtoList;
}
