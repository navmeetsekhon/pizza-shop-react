package com.orderManagement.orderManagementApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class CartItem {
    @Id
    private Long cartItemId;
    @Column
    private Long itemId;
    @Column
    private int quantity;
}
