package com.group.libraryapp.service.fruit;

import com.group.libraryapp.domain.fruit.Fruit;
import com.group.libraryapp.dto.fruit.request.FruitCreateRequest;
import com.group.libraryapp.dto.fruit.request.FruitSoldRequest;
import com.group.libraryapp.dto.fruit.response.FruitSales;
import com.group.libraryapp.repository.fruit.FruitJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitService {
    private final FruitJdbcRepository fruitJdbcRepository;

    public FruitService(FruitJdbcRepository fruitJdbcRepository) {
        this.fruitJdbcRepository = fruitJdbcRepository;
    }

    public void createFruit(FruitCreateRequest request){
        System.out.println("Received fruit data: " + request);
        fruitJdbcRepository.createFruit(request);
    }

    public void sellFruit(FruitSoldRequest request){
        fruitJdbcRepository.sellFruit(request);
    }

    /*public FruitSales checkSales(String name){
        List<Fruit> selectedFruits = fruitJdbcRepository.checkSales(name);
        long sales=0;
        long notSales=0;
        for (Fruit fruit : selectedFruits){
            if (fruit.isSold()){
                sales+=fruit.getPrice();
            }
            else{
                notSales+= fruit.getPrice();
            }
        }
        return new FruitSales(sales,notSales);
    }*/

}
