package com.orderManagement.orderManagementApp.dao;

import com.orderManagement.orderManagementApp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<Order,Integer> {
}
