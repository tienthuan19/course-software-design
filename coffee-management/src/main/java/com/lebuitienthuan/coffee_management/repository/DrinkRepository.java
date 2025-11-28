package com.lebuitienthuan.coffee_management.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.lebuitienthuan.coffee_management.models.Drink;

@Repository
public class DrinkRepository {
    private List<Drink> menu = new ArrayList<>();

    public DrinkRepository(){
        menu.add(new Drink(001, "Americano", 50000));
        menu.add(new Drink(002, "Matcha Latte", 55000));
        menu.add(new Drink(003, "Milk Tea", 45000));
        menu.add(new Drink(003, "Black Tea", 35000));
        menu.add(new Drink(003, "Peach Tea", 45000));
    }

    // 1. Hàm lấy tất cả danh sách (để sửa lỗi findAll)
    public List<Drink> findAll() {
        return menu;
    }

    // 2. Hàm tìm món theo ID (OrderService sẽ cần cái này)
    public Drink findById(int id) {
        for (Drink drink : menu) {
            if (drink.getId() == id) {
                return drink;
            }
        }
        return null; // Không tìm thấy
    }

}