package com.lebuitienthuan.postgres_coffee_management.service;

import com.lebuitienthuan.postgres_coffee_management.model.Drink;
import com.lebuitienthuan.postgres_coffee_management.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkService {

    @Autowired
    private DrinkRepository drinkRepository;

    // SỬA 1: Dùng hàm save() chuẩn thay vì custom addDrink
    public Drink addNewDrink(String name, int price){
        Drink newDrink = new Drink();
        newDrink.setName(name);
        // Lưu ý: Nếu trong Model giá là Double thì ép kiểu về double
        newDrink.setPrice(price); 
        
        return drinkRepository.save(newDrink);
    }
    
    // SỬA 2: Dùng findAll() thay vì getAllDrinks() (nguyên nhân gây crash app)
    public List<Drink> getAllDrink(){
        return drinkRepository.findAll();
    }
    
    // SỬA 3: Xử lý Optional trả về từ findById()
    public Drink findDrink(int id){
        // findById trả về một cái hộp (Optional), có thể rỗng.
        // orElse(null) nghĩa là: nếu tìm thấy thì lấy ra, không thấy thì trả về null.
        return drinkRepository.findById(id).orElse(null);
    }
}