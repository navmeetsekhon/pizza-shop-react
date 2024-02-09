import React, { useState, useEffect } from "react";
import Api from "../helper/Api.json";
import "../styles/AdminPage.css";

function AdminPage() {
  const ApiPrefix = Api.prefix;
  const [pendingOrders, setPendingOrders] = useState([]);
  const [allOrders, setAllOrders] = useState([]);

  useEffect(() => {
    const fetchAllOrders = async () => {
      try {
        const response = await fetch(ApiPrefix + "/order/allOrders");
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        if (data.code !== 200) {
          throw new Error(
            `Server error! status: ${{
              HttpCode: data.code,
              message: data.message,
              errorCode: data.errorCode,
            }}`
          );
        }
        setAllOrders(data.data);
        console.log(data.data);
        setPendingOrders(
          data.data.filter((item) => item.orderStatus === false)
        );
      } catch (e) {
        console.error(e);
      }
    };
    fetchAllOrders();
  }, [ApiPrefix]);

  const markAsCompleted = async (curOrderId) => {
    try {
      const response = await fetch(ApiPrefix + "/order/markAsCompleted", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          id: curOrderId,
        }),
      });
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const data = await response.json();
      if (data.code !== 200) {
        throw new Error(
          `Server error! status: ${data.code} , ${data.message} , ${data.errorCode}`
        );
      } else {
        const updatedOrders = allOrders.map((order) => {
          if (order.orderId === curOrderId) {
            return { ...order, orderStatus: true }; // Mark as completed
          }
          return order;
        });
        setAllOrders(updatedOrders);
        setPendingOrders(pendingOrders.filter((order) => order.orderId !== curOrderId));
        console.log("order marked completed.");
      }
    } catch (error) {}
  };

  return (
    <div className="container">
      <h1>Admin Orders Page</h1>
      <div>
        <h2>Pending Orders</h2>
        <table className="order-table">
          <thead>
            <tr>
              <th>Order Date</th>
              <th>Total Amount</th>
            </tr>
          </thead>
          <tbody>
            {pendingOrders.map((order) => (
              <tr key={order.orderId}>
                <td>{order.orderDate.substring(0,10)}</td>
                <td>${order.orderTotalAmount.toFixed(2)}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <div>
        <h2>All Orders</h2>
        <table className="order-table">
          <thead>
            <tr>
              <th>Order Date</th>
              <th>Total Amount</th>
              <th>Status</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {allOrders.map((order) => (
              <tr key={order.orderId}>
                <td>{order.orderDate.substring(0, 10)}</td>
                <td>${order.orderTotalAmount.toFixed(2)}</td>
                <td>{order.orderStatus ? "Completed" : "Pending"}</td>
                <td>
                  <button
                    className="btn"
                    onClick={() => markAsCompleted(order.orderId)}
                    disabled={order.orderStatus}
                  >
                    Mark as Completed
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default AdminPage;
