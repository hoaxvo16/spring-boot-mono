package com.example.springrestapi.mappers;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.springrestapi.entities.CatalogItem;
import com.example.springrestapi.entities.Order;
import com.example.springrestapi.entities.OrderDetail;
import com.example.springrestapi.models.OrderDetailDto;
import com.example.springrestapi.responseBodies.OrderDetailResponse;
import com.example.springrestapi.responseBodies.OrderDetailResponse.CatalogItemOrder;

@Component
public class OrderDetailMapper {
    public OrderDetail toOrderDetail(OrderDetailDto dto, Order order, CatalogItem item) {
        return new OrderDetail(order, item, dto.getQuantity(), dto.getStatus());
    }

    public OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail) {

        CatalogItem temp = orderDetail.getItem();
        UUID orderId = orderDetail.getOrder().getOrderId();
        CatalogItemOrder item = new CatalogItemOrder(temp.getId(), temp.getItemId(), temp.getItemName(),
                temp.getDescription());

        return new OrderDetailResponse(orderDetail.getDetailId(), orderId, item, orderDetail.getCreatedDate(),
                orderDetail.getModifiedDate(), orderDetail.getQuantity(), orderDetail.getStatus());
    }
}
