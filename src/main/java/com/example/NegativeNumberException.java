package com.example;

import java.util.List;

public class NegativeNumberException extends RuntimeException {
    private final List<Integer> numbers;
    public NegativeNumberException(String message, List<Integer> numbers) {
        super(message);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers(){
        return this.numbers;
    }
}
