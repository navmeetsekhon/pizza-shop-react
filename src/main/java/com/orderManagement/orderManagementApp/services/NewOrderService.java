package com.orderManagement.orderManagementApp.services;

import com.orderManagement.orderManagementApp.dao.OrderDao;
import com.orderManagement.orderManagementApp.dao.OrderDescriptionDao;
import com.orderManagement.orderManagementApp.dto.OrderDataDto;
import com.orderManagement.orderManagementApp.model.Order;
import com.orderManagement.orderManagementApp.model.OrderDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewOrderService {
    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderDescriptionDao orderDescriptionDao;
    public String newOrder(OrderDataDto orderDataDto) {
        Order order = new Order(orderDataDto.getTableId(), orderDataDto.calculateTotal(orderDataDto));
        orderDao.save(order);
        List<OrderDesc> orderDesc=new ArrayList<>();
        //OrderDesc[] orderDesc=new OrderDesc[orderDataDto.getItemId().length];
        for (int i=0;i<orderDataDto.getItemId().length;i++){
            orderDesc.set(i, new OrderDesc(order.getOrderId(), orderDataDto.getItemId()[i], orderDataDto.getQuantity()[i]));
        }
        orderDescriptionDao.saveAll(orderDesc);
        return "order placed succesfully";

    }
}
