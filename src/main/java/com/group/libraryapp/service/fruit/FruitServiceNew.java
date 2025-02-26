package com.group.libraryapp.service.fruit;

import com.group.libraryapp.domain.fruit.Fruit;
import com.group.libraryapp.domain.fruit.FruitRepository;
import com.group.libraryapp.dto.fruit.request.FruitCreateRequest;
import com.group.libraryapp.dto.fruit.request.FruitSoldRequest;
import com.group.libraryapp.dto.fruit.response.FruitCount;
import com.group.libraryapp.dto.fruit.response.FruitMoney;
import com.group.libraryapp.dto.fruit.response.FruitSales;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FruitServiceNew {
    private final FruitRepository fruitRepository;

    public FruitServiceNew(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public void createFruit(FruitCreateRequest request){
        fruitRepository.save(new Fruit(request.getName(), request.getWarehousingDate(), request.getPrice()));
    }

    public void sellFruit(FruitSoldRequest request){
        Fruit fruit = fruitRepository.findById(request.getId()).orElseThrow(IllegalArgumentException::new);
        fruit.sellFruit();
        fruitRepository.save(fruit);
    }

    public FruitSales checkSales(String name){
        long sales=0;
        long notSales=0;

        List<Fruit> fruits = fruitRepository.findByName(name);
        for(Fruit fruit:fruits){
            if(fruit.isSold()){
                sales+=fruit.getPrice();
            }
            else{
                notSales+=fruit.getPrice();
            }
        }
        return new FruitSales(sales,notSales);
    }

    public FruitCount countFruit(String name){
        List<Fruit> fruits= fruitRepository.findByName(name);
        if(fruits==null){
            throw new IllegalArgumentException();
        }
        return new FruitCount(fruits.size());
    }

    public List<FruitMoney> moneyFruit(String option, long price){
        List<Fruit> fruits = new ArrayList<>();
        if(option.equals("GTE")){
            fruits = fruitRepository.findByPriceGreaterThanEqual(price);
        } else if (option.equals("LTE")) {
            fruits = fruitRepository.findByPriceLessThanEqual(price);
        }else{
            throw new IllegalArgumentException();
        }

        return fruits.stream().map(fruit -> new FruitMoney(fruit.getName(), fruit.getPrice(), fruit.getWarehousingDate())).collect(Collectors.toList());
    }
}
