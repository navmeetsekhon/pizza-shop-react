import React from "react";
import "../styles/CartItem.css";
import ProductImage from '../assets/product.png'
import vegPizza from "../assets/veg-pizza.png";
import nonVegPizza from "../assets/non-veg-pizza.png";
import appetizer from "../assets/appetizer.png";
import drink from "../assets/drink.png";
import soup from "../assets/soup.png";
import salad from "../assets/salad.png";


function CartItem({
  userId,
  itemId,
  itemName,
  price,
  quantity,
  amount,
  itemCategory,
  increaseQty,
  decreaseQty,
  remove,
}){    
  const images = {
      "veg-pizza": vegPizza,
      "non-veg-pizza": nonVegPizza,
      "appetizer": appetizer,
      "drink": drink,
      "soup":soup,
      "salad": salad
    }
  return (
    <div className="cart-item">
      <div className="cart-item-image-box">
        <img src={ProductImage} alt={itemName} className="cart-item-image" />
      </div>
      <div className="cart-item-details">
        <h1 className="cart-item-name">{itemName}</h1>
      </div>
      <div className="cart-counter">
        <div className="cart-btn" onClick={() => increaseQty(userId, itemId)}>
          +
        </div>
        <div className="cart-count">{quantity}</div>
        <div className="cart-btn" onClick={() => decreaseQty(userId, itemId)}>
          -
        </div>
      </div>
      <div className="cart-item-prices">
        <div className="cart-item-amount">{price.toFixed(2)}</div>
        <div
          className="cart-item-remove"
          onClick={() => remove(userId, itemId)}
        >
          <u>remove</u>
        </div>
      </div>
    </div>
  );
}

export default CartItem;
