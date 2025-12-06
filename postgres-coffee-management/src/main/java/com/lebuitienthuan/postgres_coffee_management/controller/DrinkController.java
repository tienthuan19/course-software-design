package com.lebuitienthuan.postgres_coffee_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lebuitienthuan.postgres_coffee_management.model.Drink;
import com.lebuitienthuan.postgres_coffee_management.service.DrinkService;

@RestController
@RequestMapping("/api/v1")
public class DrinkController {
    private final DrinkService drinkService;

    @Autowired 
    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @PostMapping("/drink/add")
    public Drink addNewDrink(@RequestBody Drink drinkRequest){
        Drink newDrink = drinkService.addNewDrink(drinkRequest.getName(), drinkRequest.getPrice());
        return newDrink;
    }

    @GetMapping("/drink/all")
    public List<Drink> getAllDrink(){
        List<Drink> menu = drinkService.getAllDrink();
        return menu;
    }
}
