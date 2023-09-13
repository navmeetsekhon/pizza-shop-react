package com.orderManagement.orderManagementApp.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_description")
public class OrderDesc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderedDescId;
    @Getter
    private int orderId;
    @Getter
    private int itemId;
    @Getter
    private int quantity;

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderDesc(int orderId, int itemId, int quantity) {
        this.orderId=orderId;
        this.itemId=itemId;
        this.quantity=quantity;

    }

}
