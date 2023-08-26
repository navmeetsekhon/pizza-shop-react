package com.orderManagement.orderManagementApp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @RequestMapping("/allOrders")
    public String getAllOrders(){
        return "Hello world";
    }
}
