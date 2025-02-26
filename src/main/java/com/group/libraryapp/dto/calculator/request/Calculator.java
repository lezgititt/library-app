package com.group.libraryapp.dto.calculator.request;

public class Calculator {
    private int add;
    private int minus;
    private int multiply;

    public Calculator(int num1, int num2){
        this.add = num1 + num2;
        this.minus = num1 - num2;
        this.multiply = num1 * num2;
    }

    public int getAdd() {
        return add;
    }

    public int getMinus() {
        return minus;
    }

    public int getMultiply() {
        return multiply;
    }
}
