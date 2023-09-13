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
    public String newOrder(OrderDataDto orderDataDto) {
        Order order = new Order(orderDataDto.getTableId(), calculateTotal(orderDataDto));
        orderDao.save(order);
        ArrayList<OrderDesc> orderDesc=new ArrayList<>();
        //OrderDesc[] orderDesc=new OrderDesc[orderDataDto.getItemId().length];
        for (int i=0;i<orderDataDto.getItemId().length;i++){
            orderDesc.add(new OrderDesc(order.getOrderId(), orderDataDto.getItemId()[i], orderDataDto.getQuantity()[i]));
        }
        orderDescriptionDao.saveAll(orderDesc);
        return "order placed succesfully";
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

//        List<OrderDataDto> fullData=new ArrayList<>();
//        for (Order i : orderDao.findAll()){
//            OrderDataDto orderDataDto=new OrderDataDto();
//        }

        return orderDao.findAll();

    }
    public List<OrderDesc> getAllOrdersDetail() {
        return orderDescriptionDao.findAll();

    }
    public BillDto getOrderDetail(int orderId) {
//        return orderDescriptionDao.findByOrderId(orderId);
        List<OrderDesc> descObj=orderDescriptionDao.findByOrderId(orderId);
        Order orderObj=orderDao.findByOrderId(orderId);
        List<Integer> itemId=new ArrayList<>();
        List<Integer> quantity=new ArrayList<>();
        for (OrderDesc orderDesc : descObj) {
            itemId.add(orderDesc.getItemId());
            quantity.add(orderDesc.getQuantity());
        }
        BillDto billDto = new BillDto(orderObj.getTableId(),orderObj.getOrderId(),orderObj.getOrderDate(),itemId,quantity,orderObj.getTotalAmount());
        return billDto;
    }
}
