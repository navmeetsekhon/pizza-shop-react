package com.orderManagement.orderManagementApp.model;

import com.orderManagement.orderManagementApp.dto.MenuItemRequest;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="menu")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    private String itemName;
    private Double itemPrice;
    private String itemDescription;
    private String itemCategory;

    public MenuItem(MenuItemRequest menuItemRequest) {
        this.itemName = menuItemRequest.getItemName();
        this.itemPrice = menuItemRequest.getItemPrice();
        this.itemDescription = menuItemRequest.getItemDescription();
        this.itemCategory = menuItemRequest.getItemCategory();
    }
}
