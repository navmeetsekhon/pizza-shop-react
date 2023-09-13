package com.orderManagement.orderManagementApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class BillDto {
    private Integer tableId;
    private Integer orderId;
    private Timestamp orderDate;
    private List<Integer> items;
    private List<Integer> quantity;
    private Float subtotal;

    public BillDto(Integer tableId, Integer orderId,Timestamp orderDate, List<Integer> items, List<Integer> quantity, Float subtotal) {
        this.tableId = tableId;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.items = items;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }
}

