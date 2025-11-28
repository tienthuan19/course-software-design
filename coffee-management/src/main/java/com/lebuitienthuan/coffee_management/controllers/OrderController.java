package com.lebuitienthuan.coffee_management.controllers;

import com.lebuitienthuan.coffee_management.dto.OrderRequestDTO;
import com.lebuitienthuan.coffee_management.models.Order;
import com.lebuitienthuan.coffee_management.services.OrderService;
import com.lebuitienthuan.coffee_management.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // --- 1. WAITER: TẠO ORDER ---
    //order request
    @PostMapping
    public ResponseEntity<ApiResponse<Order>> createOrder(@RequestBody OrderRequestDTO request) {
        Order newOrder = orderService.createOrder(request);
        return ResponseEntity.ok(new ApiResponse<>(201, "Order created successfully", newOrder));
    }

    // --- 2. KITCHEN: LẤY DANH SÁCH CẦN LÀM ---
    //available order request
    @GetMapping("/pending")
    public ResponseEntity<ApiResponse<List<Order>>> getPendingOrders() {
        List<Order> orders = orderService.getPendingOrders();
        return ResponseEntity.ok(new ApiResponse<>(200, "List of pending orders", orders));
    }

    // --- 3. KITCHEN: BÁO LÀM XONG ---
    //order ready
    @PutMapping("/{id}/ready")
    public ResponseEntity<ApiResponse<Order>> markOrderReady(@PathVariable int id) {
        Order order = orderService.markOrderAsReady(id);
        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Order not found", null));
        }
        return ResponseEntity.ok(new ApiResponse<>(200, "Order marked as READY", order));
    }

    // --- 4. WAITER: LẤY DANH SÁCH ĐỂ BƯNG RA ---
    //available order ready request
    @GetMapping("/ready")
    public ResponseEntity<ApiResponse<List<Order>>> getReadyOrders() {
        List<Order> orders = orderService.getReadyOrders();
        return ResponseEntity.ok(new ApiResponse<>(200, "List of ready orders", orders));
    }

    // --- 5. CASHIER: XEM TỔNG TIỀN ---
    //order amount request
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>> getOrderDetails(@PathVariable int id) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Order not found", null));
        }
        return ResponseEntity.ok(new ApiResponse<>(200, "Order details", order));
    }

    // --- 6. CASHIER: THANH TOÁN ---
    //payment request -> order paid
    @PostMapping("/{id}/pay")
    public ResponseEntity<ApiResponse<Order>> payOrder(@PathVariable int id) {
        Order order = orderService.payOrder(id);
        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Order not found", null));
        }
        return ResponseEntity.ok(new ApiResponse<>(200, "Order PAID successfully", order));
    }
}