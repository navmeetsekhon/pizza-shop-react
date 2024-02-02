package com.orderManagement.orderManagementApp.dao;

import com.orderManagement.orderManagementApp.model.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDao extends JpaRepository<UserCart,String> {
    UserCart findByUserId(String userId);

    boolean existsByUserId(String userId);
}
