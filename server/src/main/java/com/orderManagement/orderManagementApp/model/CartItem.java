package com.orderManagement.orderManagementApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name = "shopping_cart_item")
public class CartItem {
    @Id
    private String cartDetailMapping;
    @Column(name = "cart_id")
    private String cartId;
    @Column(name = "item_id")
    private int itemId;
    @Column(name = "quantity")
    private Integer quantity;
}
