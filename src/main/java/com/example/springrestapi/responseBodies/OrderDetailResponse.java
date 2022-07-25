package com.example.springrestapi.responseBodies;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponse {

    private UUID detailId;

    private UUID orderId;
    CatalogItemOrder item;

    Instant createdDate;

    private Instant modifiedDate;

    private int quantity;

    private String status;

    @Data
    @AllArgsConstructor
    public static class CatalogItemOrder {
        Long id;
        String itemId;
        String itemName;
        String description;
    }
}
