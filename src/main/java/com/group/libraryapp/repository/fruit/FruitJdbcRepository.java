package com.group.libraryapp.repository.fruit;

import com.group.libraryapp.domain.fruit.Fruit;
import com.group.libraryapp.dto.fruit.request.FruitCreateRequest;
import com.group.libraryapp.dto.fruit.request.FruitSoldRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class FruitJdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    public FruitJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createFruit(FruitCreateRequest request){
        String sql = "insert into fruit (name, warehousingDate, price) values (?, ?, ?)";
        jdbcTemplate.update(sql, request.getName(), Date.valueOf(request.getWarehousingDate()),request.getPrice());
    }

    public void sellFruit(FruitSoldRequest request){
        String sql = "update fruit set sold=true where id=?";
        jdbcTemplate.update(sql, request.getId());
    }

    /*public List<Fruit> checkSales(String name){
        String readSql = "select price, sold from fruit where name=?";
        List<Fruit> selectedFruits= jdbcTemplate.query(readSql, ((rs, rowNum) -> {
            Fruit fruit = new Fruit();
            fruit.setPrice(rs.getLong("price"));
            fruit.setSold(rs.getBoolean("sold"));
            return fruit;
        }),name);

        return selectedFruits;
    }*/
}


