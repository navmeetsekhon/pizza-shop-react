import React, { useEffect, useState } from "react";
import "../styles/Profile.css";
import ProfilePhoto from "../assets/sample-profile.png";
import Api from "../helper/Api.json";

function Profile({user}) {
  // const user = localStorage.getItem("user");
  const ApiPrefix = Api.prefix;
  const [pendingOrders, setPendingOrders] = useState([]);
  const [allOrders, setAllOrders] = useState([]);

  const userId = user.userId;

  useEffect(() => {
    const fetchAllOrders = async (userId) => {
      try {
        const response = await fetch(ApiPrefix + "/order/getAllUserOrders", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            id: userId,
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
        }
        const sortedAllOrders = data.data.sort((a, b) => new Date(b.orderDate) - new Date(a.orderDate));
        
        // Filter and sort pending orders
        const sortedPendingOrders = sortedAllOrders.filter(order => !order.orderStatus)
                                                  .sort((a, b) => new Date(b.orderDate) - new Date(a.orderDate));
        
        setAllOrders(sortedAllOrders);
        setPendingOrders(sortedPendingOrders);
      } catch (e) {
        console.error(e);
      }
    };
    fetchAllOrders(userId);
  }, [userId,ApiPrefix]);
  return (
    <div className="container">
      <div className="section">
        <h2 className="section-header">User Details</h2>

        <img className="profile-photo" src={ProfilePhoto} alt="User"></img>
        <p>Name: {user.name}</p>
        <p>Email: {user.email}</p>
      </div>
      <div className="section">
        <h2 className="section-header">Pending Orders</h2>
        <table className="order-table">
          <thead>
            <tr>
              <th>Date</th>
              <th>Total Amount</th>
            </tr>
          </thead>
          <tbody>
          {pendingOrders.map((item, key) => (
              <tr key={key}>
                <td>{item.orderDate.substring(0,10)}</td>
                <td>${item.orderTotalAmount.toFixed(2)}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <div className="section">
        <h2 className="section-header">All Orders</h2>
        <table className="order-table">
          <thead>
            <tr>
              <th>Date</th>
              <th>Total Amount</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {allOrders.map((item, key) => (
              <tr key={key}>
                <td>{item.orderDate.substring(0,10)}</td>
                <td>${item.orderTotalAmount.toFixed(2)}</td>
                <td>{item.orderStatus ? 'Completed' : 'Pending'}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default Profile;
