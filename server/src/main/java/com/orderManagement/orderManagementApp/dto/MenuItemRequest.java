package com.orderManagement.orderManagementApp.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemRequest {
    private String itemName;
    private Double itemPrice;
    private String itemDescription;
    private String itemCategory;
}
