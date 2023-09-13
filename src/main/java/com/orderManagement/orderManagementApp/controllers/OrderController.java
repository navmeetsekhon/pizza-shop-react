package com.orderManagement.orderManagementApp.controllers;

import com.orderManagement.orderManagementApp.dto.BillDto;
import com.orderManagement.orderManagementApp.dto.OrderDataDto;
import com.orderManagement.orderManagementApp.model.Order;
import com.orderManagement.orderManagementApp.model.OrderDesc;
import com.orderManagement.orderManagementApp.services.NewOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    NewOrderService newOrderService;
    @GetMapping("/allOrders")
    public List<Order> getAllOrders(){
        return newOrderService.getAllOrders();
    }
    @GetMapping("/allDetails")
    public List<OrderDesc> getAllOrdersDesc(){
        return newOrderService.getAllOrdersDetail();
    }
    @GetMapping("details/{orderId}")
    public BillDto getOrdersDesc(@PathVariable int orderId){
        return newOrderService.getOrderDetail(orderId);
    }

    @PostMapping("newOrder")
    public String placeOrder(@RequestBody OrderDataDto orderDataDto){
        return newOrderService.newOrder(orderDataDto);
    }
}
