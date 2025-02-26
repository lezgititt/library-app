package com.group.libraryapp.dto.calculator.request;

public class CalculatorAddRequest{
    //외부에서 number1,2 조작 못하게 private으로 고정시켜둠. (이 클래스 외부에서 값 변경 불가)
    //한 번만 값을 할당할 수 있도록 final로 막아놓음 (이 클래스 내부에서 값 변경 불가)
    private final int number1;
    private final int number2;

    //외부에서 number1,2를 할당할 수만 있음
    //final 키워드가 있기 때문에 생성자 필수 (원랜 생성자 굳이 안써도 값이 알아서 할당됨)
    public CalculatorAddRequest(int number1, int number2){
        this.number1 = number1;
        this.number2 = number2;
    }

    //외부에서 number을 get할 때
    public int getNumber1(){
        return number1;
    }

    public int getNumber2(){
        return number2;
    }
}