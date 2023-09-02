package com.orderManagement.orderManagementApp.controllers;

import com.orderManagement.orderManagementApp.dto.OrderDataDto;
import com.orderManagement.orderManagementApp.model.Order;
import com.orderManagement.orderManagementApp.services.NewOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    NewOrderService newOrderService;
    @GetMapping("/allOrders")
    public String getAllOrders(){
        return "Hello world";
    }

    @PostMapping("newOrder")
    public String placeOrder(@RequestBody OrderDataDto orderDataDto){
        return newOrderService.newOrder(orderDataDto);
    }
}
