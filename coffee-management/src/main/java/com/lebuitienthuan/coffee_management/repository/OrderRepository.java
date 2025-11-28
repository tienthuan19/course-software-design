package com.lebuitienthuan.coffee_management.repository;

import com.lebuitienthuan.coffee_management.models.Order;
import com.lebuitienthuan.coffee_management.models.OrderStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {
    private List<Order> orders = new ArrayList<>();
    
    public Order save(Order order) {
        orders.add(order);
        return order;
    }

    public Order findById(int id) {
        return orders.stream()
                    .filter(o -> o.getId() == id)
                    .findFirst()
                    .orElse(null);
    }

    public List<Order> findByStatus(OrderStatus status) {
        return orders.stream()
                    .filter(o -> o.getStatus() == status)
                    .collect(Collectors.toList());
    }

    public List<Order> findAll() {
        return orders;
    }
}