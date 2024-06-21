import React, { useState } from 'react';
import '../../styles/Orders.css'; // Import the CSS file

function createOrderCard(title, count, borderColorClass, onClick) {
  return (
    <div className="col-lg-3 col-md-6 mb-4" key={title} onClick={onClick}>
      <div className={`card ${borderColorClass} order-card`}>
        <div className="card-body">
          <h5 className="card-title">{title}</h5>
          <p className="card-text">{count}</p>
        </div>
      </div>
    </div>
  );
}

function Orders({ allOrders, pendingOrders, cancelledOrders, completedOrders, markAsCompleted }) {
  const [selectedType, setSelectedType] = useState('totalOrdersToday');

  const orderDetails = {
    totalOrdersToday: allOrders,
    pendingOrders: pendingOrders,
    completedOrders: completedOrders,
    cancelledOrders: cancelledOrders
  };

  return (
    <div className="container">
      <div className="row">
        {createOrderCard('Total Orders Today', orderDetails.totalOrdersToday.length, 'border-left-primary', () => setSelectedType('totalOrdersToday'))}
        {createOrderCard('Pending Orders', orderDetails.pendingOrders.length, 'border-left-warning', () => setSelectedType('pendingOrders'))}
        {createOrderCard('Completed Orders', orderDetails.completedOrders.length, 'border-left-success', () => setSelectedType('completedOrders'))}
        {createOrderCard('Cancelled Orders', orderDetails.cancelledOrders.length, 'border-left-danger', () => setSelectedType('cancelledOrders'))}
      </div>

      <h2 className="mt-5">Order Details</h2>

      {selectedType === 'pendingOrders' ? (
        <table className="table table-bordered mt-3">
          <thead>
            <tr>
              <th>Date</th>
              <th>Order ID</th>
              <th>Total Amount</th>
              <th>Mode</th>
              <th>Server Name</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {orderDetails[selectedType].map((order, index) => (
              <tr key={index}>
                <td>{(order.orderDate).substring(0, 10)}</td>
                <td>{order.orderId}</td>
                <td>{(order.orderTotalAmount).toFixed(2)}</td>
                <td>{'delivery'}</td>
                <td>{order.serverName}</td>
                <td>
                  <button className="btn btn-primary" onClick={() => markAsCompleted(order.orderId)}>Mark as Completed</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <table className="table table-bordered mt-3">
          <thead>
            <tr>
              <th>Date</th>
              <th>Order ID</th>
              <th>Total Amount</th>
              <th>Mode</th>
              <th>Server Name</th>
            </tr>
          </thead>
          <tbody>
            {orderDetails[selectedType].map((order, index) => (
              <tr key={index}>
                <td>{(order.orderDate).substring(0, 10)}</td>
                <td>{order.orderId}</td>
                <td>{(order.orderTotalAmount).toFixed(2)}</td>
                <td>{'delivery'}</td>
                <td>{order.serverName}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default Orders;
