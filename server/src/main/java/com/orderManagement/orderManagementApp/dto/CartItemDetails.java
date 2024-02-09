package com.orderManagement.orderManagementApp.dto;

import com.orderManagement.orderManagementApp.model.CartItem;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItemDetails {
    private int itemId;
    private String itemName;
    private Double itemPrice;
    private Integer quantity;

    public CartItemDetails(CartItem cartItem,String itemName,Double itemPrice) {
        this.itemId = cartItem.getItemId();
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = cartItem.getQuantity();
    }
}
