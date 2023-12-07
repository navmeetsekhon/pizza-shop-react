
// Add items to cart
var items = [];
var totalAmount = 0;

function addToCart(itemId, itemName, itemPrice) {
  console.log(items);

  // Check if the item already exists in the cart
  var existingItem = items.find(function (item) {
    return item.ItemID === itemId;
  });

  if (existingItem) {
    // If the item exists, increment its quantity and update the total amount
    existingItem.Quantity++;
  } else {
    // If the item doesn't exist, add it to the cart
    var cartItemObj = {
      ItemID: itemId,
      ItemName: itemName,
      ItemPrice: itemPrice,
      Quantity: 1, // Default quantity is 1, you can change it as needed
    };
    items.push(cartItemObj);
  }

  // Update the total amount based on the quantity of the item being added
  totalAmount = items.reduce(
    (total, item) => total + item.Quantity * item.ItemPrice,
    0
  );

  // Update the cart display
  updateCartDisplay();
}

function updateCartDisplay() {
  const cartItem = document.getElementById("cartItemsList");
  cartItem.innerHTML = ""; // Clear the existing cart items

  // Populate the cart with the updated items
  items.forEach(function (item) {
    const cardCart = document.createElement("li");
    cardCart.className = "dropdown-item";
    cardCart.innerHTML = `<span>${item.Quantity}x ${item.ItemName} - $${(
      item.Quantity * item.ItemPrice
    ).toFixed(2)}</span>`;
    cartItem.appendChild(cardCart);
  });

  // Update the total quantity badge
  const cartItemCount = document.getElementById("cartItemCount");
  cartItemCount.textContent = items.reduce(
    (total, item) => total + item.Quantity,
    0
  );

  // Update the total amount display
  const totalAmountDisplay = document.getElementById("totalAmount");
  totalAmountDisplay.textContent = `$${totalAmount.toFixed(2)}`;
}

// Function to dynamically update the order summary
function updateOrderSummary() {
  const orderSummary = document.getElementById("orderSummary");
  const totalAmountElement = document.getElementById("totalAmount");

  // Clear existing order summary
  orderSummary.innerHTML = "";

  // Populate order summary
  items.forEach((item) => {
    const listItem = document.createElement("li");
    listItem.className = "list-group-item";
    listItem.textContent = `${item.Quantity}x ${item.ItemName} - $${(
      item.Quantity * item.ItemPrice
    ).toFixed(2)}`;
    orderSummary.appendChild(listItem);
  });

  // Calculate total amount
  const totalAmount = items.reduce(
    (total, item) => total + item.Quantity * item.ItemPrice,
    0
  );

  // Update total amount display
  totalAmountElement.textContent = totalAmount.toFixed(2);
}

// Call the function to update order summary
updateOrderSummary();

function checkOut(cartItems) {
    const orderSummary = document.getElementById("orderSummary");
    const totalAmountElement = document.getElementById("totalAmount");
    
}