package com.orderManagement.orderManagementApp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_description")
public class OrderDesc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDescriptionId;
    private int orderId;
    private int itemId;
    private int quantity;
    private float subtotal;
}
