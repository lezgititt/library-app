package com.group.libraryapp.controller.fruit;

import com.group.libraryapp.dto.fruit.request.FruitCreateRequest;
import com.group.libraryapp.dto.fruit.request.FruitSoldRequest;
import com.group.libraryapp.dto.fruit.response.FruitCount;
import com.group.libraryapp.dto.fruit.response.FruitMoney;
import com.group.libraryapp.dto.fruit.response.FruitSales;
import com.group.libraryapp.service.fruit.FruitService;
import com.group.libraryapp.service.fruit.FruitServiceNew;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FruitController {
    private final FruitServiceNew fruitService;

    public FruitController(FruitServiceNew fruitService) {
        this.fruitService = fruitService;
    }

    @PostMapping("/api/v1/fruit")
    public void saveFruit(@RequestBody FruitCreateRequest request){
        fruitService.createFruit(request);
    }

    @PutMapping("/api/v1/fruit")
    public void sellFruit(@RequestBody FruitSoldRequest request){
        fruitService.sellFruit(request);
    }

    @GetMapping("/api/v1/fruit/stat")
    public FruitSales checkFruitSales(@RequestParam String name){
        return fruitService.checkSales(name);
    }

    @GetMapping("/api/v1/fruit/count")
    public FruitCount countFruit(@RequestParam String name){
        return fruitService.countFruit(name);
    }

    @GetMapping("api/v1/fruit/list")
    public List<FruitMoney> moneyFruit(@RequestParam String option, @RequestParam long price){
        return fruitService.moneyFruit(option, price);
    }
}
