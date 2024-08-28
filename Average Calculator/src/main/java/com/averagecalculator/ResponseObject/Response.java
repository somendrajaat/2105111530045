package com.averagecalculator.ResponseObject;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Response {
    List<Integer> numbers;
    List<Integer> windowPrevState;
    List<Integer> windowCurrState;
    double average;

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getWindowPrevState() {
        return windowPrevState;
    }

    public void setWindowPrevState(List<Integer> windowPrevState) {
        this.windowPrevState = windowPrevState;
    }

    public List<Integer> getWindowCurrState() {
        return windowCurrState;
    }

    public void setWindowCurrState(List<Integer> windowCurrState) {
        ArrayList<Integer> ans=new ArrayList<>();
        int i;
        if(windowCurrState.size()-10<0){
            i=0;
        }else{
            i=windowCurrState.size()-10;
        }
        while(i<windowCurrState.size() ){
            ans.add(windowCurrState.get(i));
            i++;
        }
        this.windowCurrState =ans;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
