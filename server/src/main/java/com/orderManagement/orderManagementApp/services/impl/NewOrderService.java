package com.orderManagement.orderManagementApp.services.impl;

import com.orderManagement.orderManagementApp.dao.MenuDao;
import com.orderManagement.orderManagementApp.dao.OrderDao;
import com.orderManagement.orderManagementApp.dao.OrderDescriptionDao;
import com.orderManagement.orderManagementApp.dto.BillDto;
import com.orderManagement.orderManagementApp.dto.OrderDataDto;
import com.orderManagement.orderManagementApp.model.MenuItem;
import com.orderManagement.orderManagementApp.model.Order;
import com.orderManagement.orderManagementApp.model.OrderDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewOrderService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    MenuDao menuDao;
    @Autowired
    OrderDescriptionDao orderDescriptionDao;

    //method to create a new order
    public BillDto newOrder(OrderDataDto orderDataDto) {
        // timestamp of order.
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        // creating a new order object.
        Order order = new Order(orderDataDto.getTableId(),ts, calculateTotal(orderDataDto));
        orderDao.save(order);
        // orderDesc stores the description of the current order in an array.
        ArrayList<OrderDesc> orderDesc=new ArrayList<>();
        // adding objects orderDesc.
        for (int i=0;i<orderDataDto.getItemId().length;i++){
            orderDesc.add(new OrderDesc(order.getOrderId(), orderDataDto.getItemId()[i], orderDataDto.getQuantity()[i]));
        }
        // adding each item to the order with its respective quantity and price from the dto.
        orderDescriptionDao.saveAll(orderDesc);
        // return bill dto
        return getOrderDetail(order.getOrderId());
    }
    //Calculates the total amount of an order.
    public float calculateTotal(OrderDataDto orderDataDto){
        float total=0;
        for (int i=0;i<orderDataDto.getItemId().length;i++){
            Optional<MenuItem> menuItemOptional =menuDao.findById(orderDataDto.getItemId()[i]);
            if (menuItemOptional.isPresent()) {
                MenuItem menuItem = menuItemOptional.get();
                total = total + (menuItem.getItemPrice() * orderDataDto.getQuantity()[i]);
            }
        }
        return total;
    }
    // returns a list of all the orders ever placed.
    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }
    // returns a list of all the orders' details.
    public List<OrderDesc> getAllOrdersDetail() {
        return orderDescriptionDao.findAll();
    }
    
    // this method creates a billdto which combines the order class and order desc class.
    public BillDto getOrderDetail(int orderId) {
        List<OrderDesc> descObj=orderDescriptionDao.findByOrderId(orderId);
        
        Order orderObj=orderDao.findByOrderId(orderId);
        
        List<MenuItem> itemId=new ArrayList<>();

        List<Integer> quantity=new ArrayList<>();

        for (OrderDesc orderDesc : descObj) {
            Optional<MenuItem> menuOptional = menuDao.findById(orderDesc.getItemId());
            if(menuOptional.isPresent()) {
                itemId.add(menuOptional.get());
                quantity.add(orderDesc.getQuantity());
            }
        }
        return new BillDto(orderObj.getTableId(),orderObj.getOrderId(),orderObj.getOrderDate(),itemId,quantity,orderObj.getTotalAmount());
    }
}
