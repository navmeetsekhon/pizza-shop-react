package com.orderManagement.orderManagementApp.services;

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
    public BillDto newOrder(OrderDataDto orderDataDto) {
        Order order = new Order(orderDataDto.getTableId(), calculateTotal(orderDataDto));
        orderDao.save(order);
        ArrayList<OrderDesc> orderDesc=new ArrayList<>();
        for (int i=0;i<orderDataDto.getItemId().length;i++){
            orderDesc.add(new OrderDesc(order.getOrderId(), orderDataDto.getItemId()[i], orderDataDto.getQuantity()[i]));
        }
        orderDescriptionDao.saveAll(orderDesc);
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

    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }
    public List<OrderDesc> getAllOrdersDetail() {
        return orderDescriptionDao.findAll();

    }
    public BillDto getOrderDetail(int orderId) {
        List<OrderDesc> descObj=orderDescriptionDao.findByOrderId(orderId);
        System.out.println("desc:"+descObj);
        Order orderObj=orderDao.findByOrderId(orderId);
        System.out.println("o2:"+orderObj);
        List<String> itemId=new ArrayList<>();
        List<Integer> quantity=new ArrayList<>();
        for (OrderDesc orderDesc : descObj) {
            itemId.add(String.valueOf(menuDao.findById(orderDesc.getItemId())));
            quantity.add(orderDesc.getQuantity());
        }
        return new BillDto(orderObj.getTableId(),orderObj.getOrderId(),orderObj.getOrderDate(),itemId,quantity,orderObj.getTotalAmount());
    }
}
