package com.orderManagement.orderManagementApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDataDto {
    private int tableId;
    private int[] itemId;
    private int[] quantity;
}
