package com.orderManagement.orderManagementApp.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartRequest {
    private String userId;
    private int itemId;
    private int quantity;
    private String operation;

    @Override
    public String toString() {
        return "CartRequest{" +
                "userId='" + userId + '\'' +
                ", itemId=" + itemId +
                ", quantity=" + quantity +
                ", operation='" + operation + '\'' +
                '}';
    }
}
