package com.orderManagement.orderManagementApp.model;

import com.orderManagement.orderManagementApp.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

public class OrderData {
    private int orderId;
    private int tableId;
    private Date orderDate;
    private int[] item_id;
    private int[] quantity;
    private float totalAmount;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int[] getItem_id() {
        return item_id;
    }

    public void setItem_id(int[] item_id) {
        this.item_id = item_id;
    }

    public int[] getQuantity() {
        return quantity;
    }

    public void setQuantity(int[] quantity) {
        this.quantity = quantity;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }
    @Autowired
    OrderDao orderDao;

    @Autowired
    public void save(OrderData orderData) {

    }
}
