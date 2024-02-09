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
@Table(name="orders_table")
public class Order {
    @Id
    private String orderId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "order_timestamp")
    private Timestamp orderDate;
    @Column(name = "order_total_amount")
    private Double orderTotalAmount;
    @Column(name = "order_status")
    private Boolean orderStatus;
}
