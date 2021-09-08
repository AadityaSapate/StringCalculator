package com.example;

import java.util.Arrays;

public class StringCalculator {
    public static void main(String[] args){

    }

    public int add(String numberString)
    {
        if(numberString.equals(""))
            return 0;
        int result = 0;
        Integer[] args = parseString(numberString);
        for (Integer arg: args)
            result += arg;
        return result;
    }

    private Integer[] parseString(String numberString)
    {
        String[] args = numberString.split(",");
        return  Arrays.stream(args).map(arg -> Integer.parseInt(arg)).toArray(size -> new Integer[size]);
    }
}
