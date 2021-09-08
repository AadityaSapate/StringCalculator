package com.example;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {
    public static void main(String[] args){

    }

    public int add(String numberString)
    {
        if(numberString.equals(""))
            return 0;
        int result = 0;
        List<Integer> args = parseString(numberString);
        for (Integer arg: args)
            result += arg;
        return result;
    }

    private List<Integer> parseString(String numberString)
    {
        List<Integer> argsList = new ArrayList<>();
        String[] lines = numberString.split("\n");
        for (String line: lines)
        {
            String[] args = line.split(",");
            argsList.addAll(Arrays.stream(args).map(arg -> Integer.parseInt(arg)).collect(Collectors.toList()));
        }

        return argsList;
    }
}
