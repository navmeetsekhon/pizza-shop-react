const updateCheckout = () => {
  var items = JSON.parse(localStorage.getItem("cartList"));
  var total = JSON.parse(localStorage.getItem("cartTotal"));
  if (items != null && items.length > 0) {
    const displayList = document.getElementById("orderSummary");

    displayList.innerHTML = "";

    items.map((item) => {
      const checkoutItem = document.createElement("li");
      checkoutItem.className = "list-group-item";

      checkoutItem.innerHTML = `${item.cartItemName} - ${item.quantity} - ${(
        item.quantity * item.cartItemPrice
      ).toFixed(2)}`;
      displayList.append(checkoutItem);
    });
    const totalAmount = document.getElementById("totalAmount");
    totalAmount.innerHTML = "";
    totalAmount.innerHTML = total.toFixed(2);
  }
};

document.addEventListener("DOMContentLoaded", updateCheckout);

const placeOrder = async () => {
  var items = JSON.parse(localStorage.getItem("cartList"));
  if (items === null) {
    window.alert("your cart is empty");
  } else {
    var itemIdList = [];
    var itemQtyList = [];
    for (var i = 0; i < items.length; i++) {
      itemIdList.push(items[i].cartItemId);
      itemQtyList.push(items[i].quantity);
    }
    var reqObj = {
      tableId: 1,
      itemId: itemIdList,
      quantity: itemQtyList,
    };
    var resp = await sendOrder(reqObj);
    displayBill(resp);
   
  }
};
const sendOrder = async (reqObj) => {
  const url = "http://localhost:8080/order/newOrder";
  const options = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(reqObj),
  };

  try {
    const response = await fetch(url, options);
    if (!response.ok) {
      throw new Error("HTTP error! status: ${response.status}");
    }
    const data = await response.json();
    // console.log("order placed\n" + data);
    localStorage.clear();
    // window.location.href='bill.html';
    return data;
  } catch {
    console.error("error in placing order" + error);
  }
};
const displayBill = async (resp) => {
  const root = document.getElementById("root");
  console.log("response"+ await resp.JSON);
};