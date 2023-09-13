package com.orderManagement.orderManagementApp.dao;

import com.orderManagement.orderManagementApp.model.OrderDesc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDescriptionDao extends JpaRepository<OrderDesc,Integer> {
    List<OrderDesc> findByOrderId(int orderId);
}
