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
}