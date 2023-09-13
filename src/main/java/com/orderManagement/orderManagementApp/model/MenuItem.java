package com.orderManagement.orderManagementApp.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="menu")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    private String itemName;
    private float itemPrice;
    private String itemDescription;
    private String itemCategory;
}
