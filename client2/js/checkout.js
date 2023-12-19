const updateCheckout = () => {
    var items=JSON.parse(localStorage.getItem('cartList'));
    var total=JSON.parse(localStorage.getItem("cartTotal"));
    if (items.length > 0) {
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
    }
    const totalAmount=document.getElementById("totalAmount");
    totalAmount.innerHTML="";
    totalAmount.innerHTML=total.toFixed(2);
  };
  
  const display = () => {
    var data = localStorage.getItem("cartList");
    console.log(data);
  };
  
  document.addEventListener('DOMContentLoaded',updateCheckout);