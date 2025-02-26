package com.group.libraryapp.domain.fruit;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false, length=20)
    private String name;
    @Column(name = "warehousing_date", nullable = false)
    private LocalDate warehousingDate;
    @Column(nullable = false)
    private long price;
    private boolean sold = false;

    protected Fruit(){}

    public Fruit(String name, LocalDate warehousingDate, long price) {
        this.name = name;
        this.warehousingDate = warehousingDate;
        this.price = price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public long getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public LocalDate getWarehousingDate() {
        return warehousingDate;
    }

    public boolean isSold() {
        return sold;
    }

    public void sellFruit(){
        this.sold=true;
    }
}
