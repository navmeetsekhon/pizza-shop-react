package com.orderManagement.orderManagementApp.controllers;

import com.orderManagement.orderManagementApp.dto.BillDto;
import com.orderManagement.orderManagementApp.dto.OrderDataDto;
import com.orderManagement.orderManagementApp.dto.IdRequest;
import com.orderManagement.orderManagementApp.model.Order;
import com.orderManagement.orderManagementApp.model.OrderItems;
import com.orderManagement.orderManagementApp.services.impl.OrderServiceImp;
import com.orderManagement.orderManagementApp.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    
    // Dependency injection.
    @Autowired
    OrderServiceImp orderServiceImp;

    @GetMapping("/allOrders")
    public ApiResponse<List<Order>> getAllOrders(){
        return orderServiceImp.getAllOrders();
    }
    @GetMapping("/allDetails")
    public List<OrderItems> getAllOrdersDesc(){
        return orderServiceImp.getAllOrdersDetail();
    }
    @GetMapping("details/{orderId}")
    public ApiResponse<BillDto> getOrdersDesc(@PathVariable IdRequest request){
        return orderServiceImp.getOrderDetail(request);
    }
    @PostMapping("newOrder")
    public ApiResponse<BillDto> placeOrder(@RequestBody OrderDataDto orderDataDto){
        return orderServiceImp.newOrder(orderDataDto);
    }

    @PostMapping("/getAllUserOrders")
    public ApiResponse<List<Order>> getAllUserOrders(@RequestBody IdRequest idRequest){
        return orderServiceImp.getAllOrdersByUserId(idRequest);
    }

    @PostMapping("/markAsCompleted")
    public ApiResponse<String> markAsCompleted(@RequestBody IdRequest request){
        return orderServiceImp.adminMarkOrderAsCompleted(request);
    }
}
