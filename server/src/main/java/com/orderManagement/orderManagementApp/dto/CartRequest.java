package com.orderManagement.orderManagementApp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {
    private int userId;
    private int itemId;
    private int quantity;

    @Override
    public String toString() {
        return "user: "+userId+" itemId: "+itemId+" quantity: "+quantity;
    }
}
