package com.orderManagement.orderManagementApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartRequest {
    private int userId;
    private int itemId;
    private int quantity;
    private String operation;
}
