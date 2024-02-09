package com.orderManagement.orderManagementApp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemDto {
    private Integer itemId;
    private Integer Quantity;
}
