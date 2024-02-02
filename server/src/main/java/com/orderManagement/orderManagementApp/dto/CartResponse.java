package com.orderManagement.orderManagementApp.dto;

import com.orderManagement.orderManagementApp.model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private String userId;
    private String cartId;
    private List<CartItemDetails> cartItems;
}
