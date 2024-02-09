package com.orderManagement.orderManagementApp.dao;

import com.orderManagement.orderManagementApp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order,Integer> {
    Order findByOrderId(String orderId);

    List<String> findByUserId(String userId);

    List<Order> findOrdersByUserId(String userId);
}
