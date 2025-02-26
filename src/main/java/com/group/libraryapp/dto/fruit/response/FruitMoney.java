package com.group.libraryapp.dto.fruit.response;

import com.group.libraryapp.domain.fruit.Fruit;

import java.time.LocalDate;

public class FruitMoney {
    private String name;
    private long price;
    private LocalDate warehousingDate;

    public FruitMoney(String name, long price, LocalDate warehousingDate) {
        this.name = name;
        this.warehousingDate = warehousingDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public LocalDate getWarehousingDate() {
        return warehousingDate;
    }

    public long getPrice() {
        return price;
    }
}
