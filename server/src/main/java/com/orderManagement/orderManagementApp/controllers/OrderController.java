package com.orderManagement.orderManagementApp.controllers;

import com.orderManagement.orderManagementApp.dto.BillDto;
import com.orderManagement.orderManagementApp.dto.OrderDataDto;
import com.orderManagement.orderManagementApp.model.Order;
import com.orderManagement.orderManagementApp.model.OrderDesc;
import com.orderManagement.orderManagementApp.services.impl.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderServiceImp orderServiceImp;
    @GetMapping("/allOrders")
    public List<Order> getAllOrders(){
        return orderServiceImp.getAllOrders();
    }
    @GetMapping("/allDetails")
    public List<OrderDesc> getAllOrdersDesc(){
        return orderServiceImp.getAllOrdersDetail();
    }
    @GetMapping("details/{orderId}")
    public BillDto getOrdersDesc(@PathVariable int orderId){
        return orderServiceImp.getOrderDetail(orderId);
    }

    @PostMapping("newOrder")
    public BillDto placeOrder(@RequestBody OrderDataDto orderDataDto){
        return orderServiceImp.newOrder(orderDataDto);
    }
}
