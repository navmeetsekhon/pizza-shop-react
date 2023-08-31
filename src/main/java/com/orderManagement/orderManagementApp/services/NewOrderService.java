package com.orderManagement.orderManagementApp.services;

import com.orderManagement.orderManagementApp.dao.OrderDao;
import com.orderManagement.orderManagementApp.model.Order;
import com.orderManagement.orderManagementApp.model.OrderData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewOrderService {
    @Autowired
    OrderDao orderDao;

    OrderData Mydata=new OrderData();
    public String newOrder(OrderData orderData) {
        Mydata.save(orderData);
        return "Order was succesfully placed";
    }
}
