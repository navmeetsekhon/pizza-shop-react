package com.orderManagement.orderManagementApp.dao;

import com.orderManagement.orderManagementApp.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartItemDao extends JpaRepository<CartItem,String> {
    List<CartItem> findByCartId(String cartId);

    void deleteAllByCartId(String cartId);

    boolean existsByCartId(String cartId);
}
