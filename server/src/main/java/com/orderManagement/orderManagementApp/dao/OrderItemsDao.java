package com.orderManagement.orderManagementApp.dao;

import com.orderManagement.orderManagementApp.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemsDao extends JpaRepository<OrderItems,Integer> {
    List<OrderItems> findByOrderId(String orderId);
}
