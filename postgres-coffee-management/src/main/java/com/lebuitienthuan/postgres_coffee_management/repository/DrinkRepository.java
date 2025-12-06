package com.lebuitienthuan.postgres_coffee_management.repository;

import com.lebuitienthuan.postgres_coffee_management.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Integer> {
    default Drink addDrink (String name, int price) {
        Drink d = new Drink();
        d.setName(name);
        d.setPrice(price); 
        
        return this.save(d); 
    }
}