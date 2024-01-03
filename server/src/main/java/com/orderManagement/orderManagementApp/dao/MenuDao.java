package com.orderManagement.orderManagementApp.dao;

import com.orderManagement.orderManagementApp.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//<MenuItem,Integer> ,<Data type of table,Data type of primary key>
public interface MenuDao extends JpaRepository<MenuItem,Integer> {
    List<MenuItem> findByItemCategory(String category);
    MenuItem findByItemId(int i);
}
