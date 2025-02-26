package com.group.libraryapp.domain.fruit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FruitRepository extends JpaRepository<Fruit, Long> {

    List<Fruit> findByName(String name);

    List<Fruit> findByPriceGreaterThanEqual(long priceIsGreaterThan);

    List<Fruit> findByPriceLessThanEqual(long priceIsLessThan);
}
