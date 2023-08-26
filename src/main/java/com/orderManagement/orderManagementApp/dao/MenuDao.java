package com.orderManagement.orderManagementApp.dao;

import com.orderManagement.orderManagementApp.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao extends JpaRepository<MenuItem,Integer> {
    List<MenuItem> findByItemCategory(String category);
}
