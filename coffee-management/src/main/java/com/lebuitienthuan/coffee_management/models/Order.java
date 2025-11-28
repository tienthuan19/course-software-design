package com.lebuitienthuan.coffee_management.models;

import java.util.List;

public class Order {
    private int id;
    private List<OrderItem> items;
    private double total;
    private OrderStatus status; 

    public Order() {}

    public Order(int id, List<OrderItem> items, double total, OrderStatus status) {
        this.id = id;
        this.items = items;
        this.total = total;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
}