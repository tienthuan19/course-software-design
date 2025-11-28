package com.lebuitienthuan.coffee_management.models;

public class OrderItem {
    private int quantity;
    private Drink drink;
    public OrderItem(){};

    public OrderItem(Drink drink, int quantity){
        this.drink = drink;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    

}
