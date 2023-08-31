package com.orderManagement.orderManagementApp.dao;

import com.orderManagement.orderManagementApp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order,Integer> {
}
