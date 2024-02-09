package com.orderManagement.orderManagementApp.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_items_table")
public class OrderItems {
    @Id
    private String orderItemId;
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "item_id")
    private int itemId;
    @Column(name = "item_quantity")
    private int quantity;
}
