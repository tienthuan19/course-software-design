package com.lebuitienthuan.coffee_management.dto;

import java.util.List;

public class OrderRequestDTO {
    private List<ItemRequest> items;

    public List<ItemRequest> getItems() {
        return items;
    }

    public void setItems(List<ItemRequest> items) {
        this.items = items;
    }

    public static class ItemRequest {
        private int drinkId;
        private int quantity;

        public int getDrinkId() { return drinkId; }
        public void setDrinkId(int drinkId) { this.drinkId = drinkId; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }
}