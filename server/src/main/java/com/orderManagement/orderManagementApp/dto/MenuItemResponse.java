package com.orderManagement.orderManagementApp.dto;

import com.orderManagement.orderManagementApp.model.MenuItem;
import lombok.*;

import java.util.Map;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemResponse {
    private int itemId;
    private String itemName;
    private Double itemPrice;
    private String itemDescription;
    private String itemCategory;

    public MenuItemResponse(MenuItem menuItem){
        this.itemId = menuItem.getItemId();
        this.itemName = menuItem.getItemName();
        this.itemPrice = menuItem.getItemPrice();
        this.itemDescription = menuItem.getItemDescription();
        this.itemCategory = menuItem.getItemCategory();
    }
}
