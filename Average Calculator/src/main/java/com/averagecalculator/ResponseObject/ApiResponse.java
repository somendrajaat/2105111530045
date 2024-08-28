package com.averagecalculator.ResponseObject;

import java.util.ArrayList;
import java.util.List;

public class ApiResponse {
    private List<Integer> numbers;

    public ArrayList<Integer> getNumbers() {
        return (ArrayList<Integer>) numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }
}