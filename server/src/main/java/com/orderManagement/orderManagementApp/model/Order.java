package com.orderManagement.orderManagementApp.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

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
    private Timestamp orderDate;
    private Double totalAmount;

    public Order(int tableId,Timestamp orderDate,Double totalAmount){
        this.tableId=tableId;
        this.orderDate=orderDate;
        this.totalAmount=totalAmount;
    }
}
