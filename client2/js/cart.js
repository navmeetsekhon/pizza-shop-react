var items = JSON.parse(localStorage.getItem("cartList")) || [];
var totalValueString = localStorage.getItem("cartTotal");
// var totalValue = totalValueString ? JSON.parse(totalValueString) : 0;
if (totalValueString === undefined || totalValueString === "undefined") {
  var totalValue = 0;
} else {
  var totalValue = JSON.parse(totalValueString);
}

// this function adds items to cart
const addToCart = (itemId, itemName, itemPrice) => {
  var existing = items.find((item) => {
    return item.cartItemId === itemId;
  });
  if (existing) {
    existing.quantity++;
  } else {
    var cartItemObj = {
      cartItemId: itemId,
      cartItemName: itemName,
      cartItemPrice: itemPrice,
      quantity: 1,
    };
    items.push(cartItemObj);
  }
  // updating localStorage list
  localStorage.setItem("cartList", JSON.stringify(items));

  total = items.reduce(
    (total, item) => total + item.quantity * item.cartItemPrice,
    0
  );
  //update local storage total value
  localStorage.setItem("cartTotal", total);
  console.log(items);
  console.log(totalValue);
  updateCart();
};

//this function dynamically updates the dropdown cart list.
const updateCart = () => {
  //selecting the cart list
  const cartItem = document.getElementById("cartItemsList");
  //clearing existing html.
  cartItem.innerHTML = "";
  //getting list from localstorage.
  var cartItems = JSON.parse(localStorage.getItem("cartList"));
  //dynamically adding items to shopping cart dropdown list.

  if (cartItems != null) {
    cartItems.forEach((item) => {
      const cardCart = document.createElement("li");
      cardCart.className = "dropdown-item";
      cardCart.innerHTML = `<span>${item.cartItemName} - ${item.quantity} - ${(
        item.quantity * item.cartItemPrice
      ).toFixed(2)}</span>`;
      cartItem.appendChild(cardCart);
    });
    const cartItemCount = document.getElementById("cartItemCount");
    cartItemCount.textContent = cartItems.reduce(
      (totalAmount, item) => totalAmount + item.quantity,
      0
    );
    var cartTotal = JSON.parse(localStorage.getItem("cartTotal"));
    // Update the total amount display
    const totalAmountDisplay = document.getElementById("totalAmount");
    console.log(cartTotal);
    totalAmountDisplay.textContent = `$${cartTotal.toFixed(2)}`;
  }
};
updateCart();

const clearCart = () => {
  localStorage.clear();
};
