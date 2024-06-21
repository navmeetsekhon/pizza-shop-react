import React, { useEffect, useState } from "react";
import CartItem from "../components/CartItem";
import "../styles/Cart.css";
// import items from "../helper/CartData.json";
import Api from "../helper/Api.json";

const user = {
  userId: 1,
  name: "John Doe",
  email: "johndoe@example.com",
};
function CartPage({
  user,
  setCartCountCustom,
}) {
  const userId = user.userId;
  const uriPrefix = Api.prefix;

  // cartData will store the state of current user's cart.
  const [cartData, setCartData] = useState([]);
  console.log(cartData);

  // This use effect hook will fetchData when there is a change in cart data.
  useEffect(() => {
    const fetchCart = async () => {
      try {
        const response = await fetch(uriPrefix + `/userCart?userId=${userId}`);
        const responseData = await response.json();

        if (responseData && responseData.data && responseData.data.cartItems) {
          setCartData(responseData.data.cartItems);
        } else {
          console.error("Invalid response format:", responseData);
        }
      } catch (error) {
        console.error("Error fetching cart:", error);
      }
    };
    fetchCart();
  }, [userId, uriPrefix]);

  useEffect(() => {
    // console.log("change in cart");
    function calculateTotalQuantity(items) {
      return items.reduce((total, item) => total + item.quantity, 0);
    }
    setCartCountCustom(calculateTotalQuantity(cartData));
  }, [cartData]);

  const increaseQty = async (userId, itemId) => {
    const request = {
      userId: userId,
      itemId: itemId,
    };
    try {
      const response = await fetch(uriPrefix + "/userCart/increaseItemQty", {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(request),
      });
      if (!response.ok) {
        throw new Error();
      }
      const result = await response.json();
      if (result.code === 200) {
        const updatedData = cartData.map((item) => {
          if (item.itemId === itemId && item.quantity > 0) {
            // Increase the quantity by 1 for the specified item
            // incrementCartCount();
            return { ...item, quantity: item.quantity + 1 };
          }
          return item;
        });
        setCartData(updatedData);
      }
    } catch (error) {
      console.error(error);
    }
  };

  const decreaseQty = async (userId, itemId) => {
    const request = {
      userId: userId,
      itemId: itemId,
    };
    try {
      const response = await fetch(uriPrefix + "/userCart/decreaseItemQty", {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(request),
      });
      if (!response.ok) {
        throw new Error();
      }
      const result = await response.json();
      if (result.code === 200) {
        const updatedData = cartData.map((item) => {
          if (item.itemId === itemId && item.quantity > 1) {
            // Decrease the quantity by 1 for the specified item
            // decrementCartCount();
            return { ...item, quantity: item.quantity - 1 };
          }
          return item;
        });
        setCartData(updatedData);
      }
    } catch (error) {
      console.error(error);
    }
  };

  const removeItem = async (userId, itemId) => {
    const request = {
      userId: userId,
      itemId: itemId,
    };
    try {
      const response = await fetch(uriPrefix + "/userCart/deleteItem", {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(request),
      });
      if (!response.ok) {
        throw new Error();
      }
      const result = await response.json();
      if (result.code === 200) {
        const newCartData = cartData.filter((item) => item.itemId !== itemId);
        setCartData(newCartData);
        // setCartCountCustom(cartData.length);
      }
    } catch (error) {
      console.error(error);
    }
  };

  const clearCart = async (userId) => {
    try {
      const response = await fetch(
        uriPrefix + "/userCart/clear?userId=" + userId,
        {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      if (!response.ok) {
        throw new Error();
      }
      const result = await response.json();
      if (result.code === 200) {
        // setZeroCount();
        setCartData([]);
      }
    } catch (error) {
      console.error(error);
    }
  };

  const placeOrder = async (cartData) => {
    // console.log(cartData);
    try {
      if (cartData.length < 1) {
        alert("cart is empty!");
        return null;
      }
      const response = await fetch(uriPrefix + "/userCart/placeOrder", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          id: userId,
        }),
      });
      if (!response.ok) {
        throw new Error();
      }
      const result = await response.json();
      if (result.code === 200) {
        setCartData([]);
        window.alert("Your order has been placed");
      } else {
        window.alert(result.code + result.message);
      }
    } catch (error) {
      console.error(error);
    }
  };

  const total = cartData.reduce(
    (total, item) => total + item.itemPrice * item.quantity,
    0
  );
  return (
    <div className="cart-page">
      <div className="cart-container">
        <div className="cart-header">
          <h1 className="cart-page-title">Your order summary</h1>
          <h5 className="Action" onClick={() => clearCart(userId)}>
            Remove all
          </h5>
        </div>
        <div className="cartList">
          {cartData.map((item, key) => {
            return (
              <CartItem
                key={key}
                userId={userId}
                itemId={item.itemId}
                itemName={item.itemName}
                price={item.itemPrice}
                quantity={item.quantity}
                amount={(item.itemPrice * item.quantity).toFixed(2)}
                itemCategory={item.itemCategory}
                increaseQty={increaseQty}
                decreaseQty={decreaseQty}
                remove={removeItem}
              />
            );
          })}
        </div>
        <hr className="cart-hr" />
        <div className="cart-checkout">
          <div className="cart-total">
            <div>
              <div className="cart-subtotal">Sub-total</div>
              <div className="cart-items">{cartData.length} items</div>
            </div>
            <div className="cart-total-amount">${total.toFixed(2)}</div>
          </div>
          <button
            className="cart-checkout-button"
            onClick={() => {
              placeOrder(cartData);
            }}
          >
            Checkout
          </button>
        </div>
      </div>
    </div>
  );
}

export default CartPage;
