package com.orderManagement.orderManagementApp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItemQuantityUpdateRequest {
    String userId;
    Integer itemId;
}
