package com.orderManagement.orderManagementApp.services;

import com.orderManagement.orderManagementApp.dto.*;
import com.orderManagement.orderManagementApp.utils.ApiResponse;
import org.springframework.transaction.annotation.Transactional;

public interface CartService {
    ApiResponse<CartResponse> getAllCartItems(String userId);

    ApiResponse<CartResponse> addToCart(CartRequest request);

    ApiResponse<CartResponse> increaseItemQuantity(CartItemQuantityUpdateRequest request);
    ApiResponse<CartResponse> decreaseItemQuantity(CartItemQuantityUpdateRequest request);
    ApiResponse<CartResponse> deleteItem(CartItemQuantityUpdateRequest request);

    @Transactional
    ApiResponse<String> clearCart(String userId);

    ApiResponse<Double> calculateCartValue(String userId);

    ApiResponse<BillDto> placeOrder(IdRequest request);
}
