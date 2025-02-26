package com.group.libraryapp.dto.calculator.request;

import java.util.List;

//json을 객체로 변환하기 위해서는 생성자 + getter 조합 혹은 getter + setter 조합이 필요
public class NumberList {
    private List<Integer> numbers;

    public NumberList(List<Integer> numbers){
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int sigma(){
        int sigma=0;
        for(int num : numbers){
            sigma+=num;
        }
        return sigma;
    }
}
