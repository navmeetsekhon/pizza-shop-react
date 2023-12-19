package com.orderManagement.orderManagementApp.controllers;

import com.orderManagement.orderManagementApp.dto.CartRequest;
import com.orderManagement.orderManagementApp.dto.CartResponse;
import com.orderManagement.orderManagementApp.exception.ResourceNotFoundException;
import com.orderManagement.orderManagementApp.services.CartService;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

//@RestController
//@RequestMapping("cart")
//public class CartController {
//    @Autowired
//    CartService cartService;
////    @GetMapping
////    public List<CartResponse> getAllCart(@RequestParam Integer tableId){
////        try{
////            return CartService.getUserCartDetails(tableId);
////        }
////        catch (ResourceNotFoundException e){
////            System.out.println("cart is empty"+e);
////            return Collections.emptyList();
////        }
////    }
//    @PostMapping("/addToCart")
//    public CartResponse addToCart(CartRequest request){
//        return cartService.addCartItem(request);
//    }
//}
