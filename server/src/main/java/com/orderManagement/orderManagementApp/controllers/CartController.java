package com.orderManagement.orderManagementApp.controllers;

import com.orderManagement.orderManagementApp.dto.CartRequest;
import com.orderManagement.orderManagementApp.dto.CartResponse;
import com.orderManagement.orderManagementApp.services.CartService;
import com.orderManagement.orderManagementApp.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("userCart")
public class CartController {

//    @Autowired
    CartService cartService;
    @GetMapping
    public ApiResponse<List<CartResponse>> getAllCartItems(@RequestParam Integer userId){
        return cartService.getAllCartItems(userId);
    }
    @PostMapping("/addToCart")
    public ApiResponse<CartResponse> addToCart(CartRequest request){
        return cartService.addToCart(request);
    }

    @PutMapping("/updateItemQty")
    public ApiResponse<CartResponse> updateItemQty(CartRequest request){
        return cartService.updateItemQty(request);
    }

    @DeleteMapping("/deleteItem")
    public ApiResponse<CartResponse> deleteCartItem(CartRequest request){
        return cartService.deleteItem(request);
    }

    @DeleteMapping("/clear")
    public ApiResponse<String> clearCart(String userId){
        return cartService.clearCart(userId);
    }

    @GetMapping("/calculateTotalValue")
    public ApiResponse<Double> calculateCartValue(String userId){
        return cartService.calculateCartValue(userId);
    }
}
