import React from "react";
import "../styles/MenuItem.css";
import vegPizza from "../assets/veg-pizza.png";
import nonVegPizza from "../assets/non-veg-pizza.png";
import appetizer from "../assets/appetizer.png";
import drink from "../assets/drink.png";
import soup from "../assets/soup.png";
import salad from "../assets/salad.png";

function MenuItem({ userId, itemId, itemName, itemPrice, itemCategory, incrementCartCount }) {
  const images = {
    "veg-pizza": vegPizza,
    "non-veg-pizza": nonVegPizza,
    "appetizer": appetizer,
    "drink": drink,
    "soup":soup,
    "salad": salad
  }

  const addToCart = async () => {
    try {
      const data = {
        userId: userId,
        itemId: itemId,
        quantity: 1,
        operation: "add",
      };
      const response = await fetch("http://localhost:8080/userCart/addToCart", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      });
      if (!response.ok) {
        throw new Error();
      }
      incrementCartCount();
    } catch (error) {
      console.error(error);
    }
  };
  return (
    <div className="product-card">
      <img src={images[itemCategory]} alt="Product" className="product-image" />
      <div className="product-info">
        <h2 className="product-title">{itemName}</h2>
        <p className="product-description">{itemCategory}</p>
        <span className="product-price">${itemPrice}</span>
        <button className="add-to-cart-button" onClick={addToCart}>
          Add to Cart
        </button>
      </div>
    </div>
  );
}

export default MenuItem;
