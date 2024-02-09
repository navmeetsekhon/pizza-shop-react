package com.orderManagement.orderManagementApp.services.impl;

import com.orderManagement.orderManagementApp.dao.MenuDao;
import com.orderManagement.orderManagementApp.dao.OrderDao;
import com.orderManagement.orderManagementApp.dao.OrderItemsDao;
import com.orderManagement.orderManagementApp.dto.BillDto;
import com.orderManagement.orderManagementApp.dto.OrderDataDto;
import com.orderManagement.orderManagementApp.dto.OrderItemDto;
import com.orderManagement.orderManagementApp.dto.IdRequest;
import com.orderManagement.orderManagementApp.model.MenuItem;
import com.orderManagement.orderManagementApp.model.Order;
import com.orderManagement.orderManagementApp.model.OrderItems;
import com.orderManagement.orderManagementApp.services.OrderService;
import com.orderManagement.orderManagementApp.utils.ApiResponse;
import com.orderManagement.orderManagementApp.utils.IdGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    MenuDao menuDao;
    @Autowired
    OrderItemsDao orderItemsDao;

    //method to create a new order
    @Override
    public ApiResponse<BillDto> newOrder(OrderDataDto orderDataDto) {
        try {
            if (orderDataDto.getOrderItemsList().isEmpty()){
                return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),"Empty cart");
            }
            // Creating an order object and saving in db order_table.
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String orderId = IdGeneratorUtil.generateId("orderId");
            String userId = orderDataDto.getUserId();
            Double totalAmount = calculateTotal(orderDataDto);
            Boolean status = false;
            Order order = new Order(orderId, userId, timestamp, totalAmount, status);
            orderDao.save(order);

            // Creating an order_item object and saving in db order_items_table.
            for (OrderItemDto orderItemDto : orderDataDto.getOrderItemsList()) {
                String orderItemsId = IdGeneratorUtil.generateId("orderItemId");
                Integer itemId = orderItemDto.getItemId();
                Integer quantity = orderItemDto.getQuantity();
                OrderItems orderItemsObject = new OrderItems(orderItemsId, orderId, itemId, quantity);
                orderItemsDao.save(orderItemsObject);
            }
            return new ApiResponse<>(HttpStatus.OK.value(), generateBill(order.getOrderId()).getData());
        } catch (Exception e) {
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }

    public ApiResponse<BillDto> generateBill(String orderId) {
        try {
            BillDto billDto = new BillDto();

            List<OrderItems> orderItems = orderItemsDao.findByOrderId(orderId);
            Order orderObject = orderDao.findByOrderId(orderId);

            billDto.setUserId(orderObject.getUserId());
            billDto.setOrderId(orderId);
            billDto.setOrderDate(orderObject.getOrderDate());
            billDto.setSubtotal(orderObject.getOrderTotalAmount());
            List<OrderItemDto> orderItemDtoList = new ArrayList<>();
            for (OrderItems o : orderItems) {
                OrderItemDto orderItemDto = new OrderItemDto();
                orderItemDto.setItemId(o.getItemId());
                orderItemDto.setQuantity(o.getQuantity());
                orderItemDtoList.add(orderItemDto);
            }
            billDto.setOrderItemsList(orderItemDtoList);
            return new ApiResponse<>(HttpStatus.OK.value(), billDto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }

    @Override
    //Calculates the total amount of an order.
    public Double calculateTotal(OrderDataDto orderDataDto) {
        Double total = 0.0;
        for (OrderItemDto o : orderDataDto.getOrderItemsList()) {
            MenuItem menuItem = menuDao.findById(o.getItemId()).orElse(null);
            if (menuItem != null) {
                total += menuItem.getItemPrice() * o.getQuantity();
            }
        }
        return total;
    }

    @Override
    public ApiResponse<List<Order>> getAllOrdersByUserId(IdRequest request){
        try {
            String userId = request.getId();
            List<Order> orders = orderDao.findOrdersByUserId(userId);
            return new ApiResponse<>(HttpStatus.OK.value(),orders);
        }
        catch (Exception e){
            return  new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
        }
    }

    @Override
    public ApiResponse<String> adminMarkOrderAsCompleted(IdRequest request){
        try {
            String orderId = request.getId();
            Order order = orderDao.findByOrderId(orderId);
            if (order.getOrderStatus()){
                System.out.println("bad req.");
                return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),"Order is already completed.");
            }
            order.setOrderStatus(true);
            orderDao.save(order);
            return new ApiResponse<>(HttpStatus.OK.value(),"Order marked as completed.");
        }
        catch (Exception e){
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
        }
    }


    // returns a list of all the orders ever placed.
    @Override
    public ApiResponse<List<Order>> getAllOrders() {
        try{
            List<Order> orderList = orderDao.findAll();
            return new ApiResponse<>(HttpStatus.OK.value(),orderList);
        }
        catch (Exception e){
            return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
        }

    }

    // returns a list of all the orders' details.
    public List<OrderItems> getAllOrdersDetail() {
        return orderItemsDao.findAll();
    }

    @Override
    public ApiResponse<BillDto> getOrderDetail(IdRequest request) {
        return null;
    }

// this method creates a billdto which combines the order class and order desc class.
//    public ApiResponse<BillDto> getOrderDetail(String orderId) {
//        List<OrderItems> descObj= orderItemsDao.findByOrderId(orderId);
//
//        Order orderObj=orderDao.findByOrderId(orderId);
//
//        List<OrderItemDto> items=new ArrayList<>();
//
//        for (OrderItems orderItems : descObj) {
//            Optional<MenuItem> menuOptional = menuDao.findById(orderItems.getItemId());
//            if(menuOptional.isPresent()) {
//                items.add(menuOptional.get());
//            }
//        }
//        return new ApiResponse<>(HttpStatus.OK.value(),new BillDto(orderObj.getUserId(),orderObj.getOrderId(),orderObj.getOrderDate(),itemId,quantity,orderObj.getOrderTotalAmount()));
//    }
}
