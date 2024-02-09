package com.orderManagement.orderManagementApp.controllers;

import com.orderManagement.orderManagementApp.dto.*;
import com.orderManagement.orderManagementApp.services.impl.CartServiceImp;
import com.orderManagement.orderManagementApp.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("userCart")
public class CartController {

    @Autowired
    CartServiceImp cartService;
    @GetMapping
    public ApiResponse<CartResponse> getAllCartItems(@RequestParam String userId){
        return cartService.getAllCartItems(userId);
    }
    @PostMapping("/addToCart")
    public ApiResponse<CartResponse> addToCart(@RequestBody CartRequest request){
        return cartService.addToCart(request);
    }

    @PutMapping("/increaseItemQty")
    public ApiResponse<CartResponse> increaseQuantity(@RequestBody CartItemQuantityUpdateRequest request){
        return cartService.increaseItemQuantity(request);
    }

    @PutMapping("/decreaseItemQty")
    public ApiResponse<CartResponse> decreaseQuantity(@RequestBody CartItemQuantityUpdateRequest request){
        return cartService.decreaseItemQuantity(request);
    }


    @DeleteMapping("/deleteItem")
    public ApiResponse<CartResponse> deleteCartItem(@RequestBody CartItemQuantityUpdateRequest request){
        return cartService.deleteItem(request);
    }

    @DeleteMapping("/clear")
    public ApiResponse<String> clearCart(@RequestParam String userId){
        return cartService.clearCart(userId);
    }

    @GetMapping("/calculateTotalValue")
    public ApiResponse<Double> calculateCartValue(@RequestParam String userId){
        return cartService.calculateCartValue(userId);
    }
    @PostMapping("/placeOrder")
    public ApiResponse<BillDto> addToCart(@RequestBody IdRequest request){
        return cartService.placeOrder(request);
    }
}
