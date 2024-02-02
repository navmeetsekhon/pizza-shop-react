package com.orderManagement.orderManagementApp.dto;

// import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import com.orderManagement.orderManagementApp.model.MenuItem;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class BillDto {
    private Integer tableId;
    private Integer orderId;
    private Timestamp orderDate;
    private List<MenuItem> items;
    private List<Integer> quantity;
    private Double subtotal;

    public BillDto(Integer tableId, Integer orderId,Timestamp orderDate, List<MenuItem> items, List<Integer> quantity, Double subtotal) {
        this.tableId = tableId;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.items = items;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }
}

