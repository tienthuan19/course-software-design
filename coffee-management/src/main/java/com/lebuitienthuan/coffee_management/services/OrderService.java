package com.lebuitienthuan.coffee_management.services;

import com.lebuitienthuan.coffee_management.dto.OrderRequestDTO;
import com.lebuitienthuan.coffee_management.models.Drink;
import com.lebuitienthuan.coffee_management.models.Order;
import com.lebuitienthuan.coffee_management.models.OrderItem;
import com.lebuitienthuan.coffee_management.models.OrderStatus;
import com.lebuitienthuan.coffee_management.repository.DrinkRepository;
import com.lebuitienthuan.coffee_management.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final DrinkRepository drinkRepository;
    private final AtomicInteger idCounter = new AtomicInteger(1); // Tự động tăng ID

    public OrderService(OrderRepository orderRepository, DrinkRepository drinkRepository) {
        this.orderRepository = orderRepository;
        this.drinkRepository = drinkRepository;
    }

    // 1. Waiter -> OrderManagement: Tạo đơn hàng mới
    public Order createOrder(OrderRequestDTO request) {
        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0;

        for (OrderRequestDTO.ItemRequest itemReq : request.getItems()) {
            
            Drink drink = drinkRepository.findAll().stream()
                    .filter(d -> d.getId() == itemReq.getDrinkId())
                    .findFirst()
                    .orElse(null);

            if (drink != null) {
                //Get quantity of the itemReq then use it for OrderItem Constructure
                OrderItem orderItem = new OrderItem(drink, itemReq.getQuantity());
                orderItems.add(orderItem);
                totalAmount += drink.getPrice() * itemReq.getQuantity();
            }
        }

        // Tạo Order mới với trạng thái PENDING (Chờ bếp)
        Order newOrder = new Order(
                idCounter.getAndIncrement(),
                orderItems,
                totalAmount,
                OrderStatus.PENDING 
        );

        return orderRepository.save(newOrder);
    }

    // 2. Kitchen -> OrderManagement: Lấy danh sách món cần làm (Available Order)
    public List<Order> getPendingOrders() {
        return orderRepository.findByStatus(OrderStatus.PENDING);
    }

    // 3. Kitchen -> OrderManagement: Báo món đã xong (Order Ready)
    public Order markOrderAsReady(int orderId) {
        Order order = orderRepository.findById(orderId);
        if (order != null) {
            order.setStatus(OrderStatus.READY);
        }
        return order;
    }

    // 4. Waiter -> OrderManagement: Lấy danh sách món đã xong để bưng ra
    public List<Order> getReadyOrders() {
        return orderRepository.findByStatus(OrderStatus.READY);
    }

    // 5. Cashier -> OrderManagement: Lấy thông tin tính tiền
    public Order getOrderById(int id) {
        return orderRepository.findById(id);
    }

    // 6. Cashier -> OrderManagement: Thanh toán (Payment Request)
    public Order payOrder(int orderId) {
        Order order = orderRepository.findById(orderId);
        if (order != null) {
            order.setStatus(OrderStatus.PAID);
        }
        return order;
    }
}