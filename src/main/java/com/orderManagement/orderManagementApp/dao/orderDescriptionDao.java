package com.orderManagement.orderManagementApp.dao;

import com.orderManagement.orderManagementApp.model.OrderDesc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface orderDescriptionDao extends JpaRepository<OrderDesc,Integer> {
}
