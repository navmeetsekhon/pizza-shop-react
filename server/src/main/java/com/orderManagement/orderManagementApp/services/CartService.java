package com.orderManagement.orderManagementApp.services;

import com.orderManagement.orderManagementApp.dto.CartItemQuantityUpdateRequest;
import com.orderManagement.orderManagementApp.dto.CartRequest;
import com.orderManagement.orderManagementApp.dto.CartResponse;
import com.orderManagement.orderManagementApp.utils.ApiResponse;

public interface CartService {
    ApiResponse<CartResponse> getAllCartItems(String userId);

    ApiResponse<CartResponse> addToCart(CartRequest request);

    ApiResponse<CartResponse> increaseItemQuantity(CartItemQuantityUpdateRequest request);
    ApiResponse<CartResponse> decreaseItemQuantity(CartItemQuantityUpdateRequest request);
    ApiResponse<CartResponse> deleteItem(CartItemQuantityUpdateRequest request);

    ApiResponse<String> clearCart(String userId);

    ApiResponse<Double> calculateCartValue(String userId);
}
