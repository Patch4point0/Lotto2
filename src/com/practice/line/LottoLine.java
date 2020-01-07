package com.practice.line;

import java.util.ArrayList;
import java.util.List;

public class LottoLine {
    private List<Integer> numbers;

    public LottoLine() {
        numbers = new ArrayList<>();
    }

    public boolean addNumberToLine(Integer integer) {
        if(numbers.size() == 6){
            return false;
        }
        numbers.add(integer);
        return true;
    }

    public boolean isValid(){
        return numbers.size() == 6;
    }

    private void print(){
        System.out.println("Line : ");
        for(Integer integer : numbers){
            System.out.print(integer + " ");
        }
    }
}
