package com.orderManagement.orderManagementApp.services;

import com.orderManagement.orderManagementApp.dto.CartRequest;
import com.orderManagement.orderManagementApp.dto.CartResponse;
import com.orderManagement.orderManagementApp.utils.ApiResponse;

import java.util.List;

public interface CartService {
    ApiResponse<List<CartResponse>> getAllCartItems(Integer userId);

    ApiResponse<CartResponse> addToCart(CartRequest request);

    ApiResponse<CartResponse> updateItemQty(CartRequest request);

    ApiResponse<CartResponse> deleteItem(CartRequest request);

    ApiResponse<String> clearCart(String userId);

    ApiResponse<Double> calculateCartValue(String userId);
}
