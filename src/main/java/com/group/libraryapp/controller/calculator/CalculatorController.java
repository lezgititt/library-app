package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class CalculatorController{
    @GetMapping("/add")
    public int addTwoNumbers(CalculatorAddRequest request){
        return request.getNumber1()+ request.getNumber2();
    }

    @PostMapping("/multiply")
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request){
        return request.getNumber1()* request.getNumber2();
    }

    @GetMapping("/api/v1/calc")
    public Calculator calculate(@RequestParam int num1, @RequestParam int num2){
        return new Calculator(num1, num2);
    }

    @GetMapping("api/v1/day-of-the-week")
    public TodayIs findDay(@RequestParam String date){
        return new TodayIs(LocalDate.parse(date));
    }

    @PostMapping("/add")
    public int sigma(@RequestBody NumberList request){
        return request.sigma();
    }
}