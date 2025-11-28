package com.lebuitienthuan.coffee_management.models;

public class Order {
    private int id;
    private OrderItem orderItem;
    private double total;

    public Order(){};

    public Order(int id, OrderItem orderItem, double total) {
        this.id = id;
        this.orderItem = orderItem;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    

    
}
