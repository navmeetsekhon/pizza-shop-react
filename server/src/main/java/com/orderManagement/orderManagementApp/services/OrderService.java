package com.orderManagement.orderManagementApp.services;

import com.orderManagement.orderManagementApp.dto.BillDto;
import com.orderManagement.orderManagementApp.dto.OrderDataDto;
import com.orderManagement.orderManagementApp.dto.IdRequest;
import com.orderManagement.orderManagementApp.model.Order;
import com.orderManagement.orderManagementApp.model.OrderItems;
import com.orderManagement.orderManagementApp.utils.ApiResponse;

import java.util.List;

public interface OrderService {
    public ApiResponse<BillDto> newOrder(OrderDataDto orderDataDto);

    //Calculates the total amount of an order.
    public Double calculateTotal(OrderDataDto orderDataDto);

    ApiResponse<List<Order>> getAllOrdersByUserId(IdRequest request);

    ApiResponse<String> adminMarkOrderAsCompleted(IdRequest request);

    // returns a list of all the orders ever placed.
    public ApiResponse<List<Order>> getAllOrders();
    // returns a list of all the orders' details.
    public List<OrderItems> getAllOrdersDetail();

    // this method creates a billdto which combines the order class and order desc class.
     ApiResponse<BillDto> getOrderDetail(IdRequest request);
}
