import React from "react";
import "../styles/CartItem.css";
import ProductImage from "../assets/product.png";
function CartItem({
  userId,
  itemId,
  itemName,
  price,
  quantity,
  amount,
  increaseQty,
  decreaseQty,
  remove,
}) {
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
        <div className="cart-item-amount">{price}</div>
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
