package com.orderManagement.orderManagementApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private int tableId;
    private float totalAmount;

    public Order(int tableId,float totalAmount){
        this.tableId=tableId;
//        this.orderDate=orderDate;
        this.totalAmount=totalAmount;
    }
}
