package com.orderManagement.orderManagementApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_description")
public class OrderDesc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDescriptionId;
    private int orderId;
    private int itemId;
    private int quantity;

    public OrderDesc(int orderId, int itemId, int quantity) {
        this.orderId=orderId;
        this.itemId=itemId;
        this.quantity=quantity;

    }

}
