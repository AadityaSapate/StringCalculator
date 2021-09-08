package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

    private static int calledCount = 0;

    public static void main(String[] args){

    }

    public int add(String numberString)
    {
        this.calledCount++;
        System.out.println(calledCount);
        if(numberString.equals(""))
            return 0;
        int result = 0;
        List<Integer> negativeNumbers = new ArrayList<>();
        List<Integer> args = parseString(numberString);
        for (Integer arg: args) {
            if (arg < 0)
                negativeNumbers.add(arg);
            if(arg <= 1000)
                result += arg;
        }
        if(!negativeNumbers.isEmpty())
            throw new NegativeNumberException("negatives not allowed", negativeNumbers);
        return result;
    }

    private List<Integer> parseString(String numberString)
    {
        Pattern p = Pattern.compile("//(.*)");
        String delimiter = ",";
        List<Integer> argsList = new ArrayList<>();
        String[] lines = numberString.split("\n");
        for (int i=0; i< lines.length; i++)
        {
            if(i == 0)
            {
                Matcher matcher = p.matcher(lines[i]);
                if(matcher.find())
                {
                    delimiter = matcher.group(1);
                    continue;
                }
            }

            String[] args = lines[i].split(delimiter);
            argsList.addAll(Arrays.stream(args).map(arg -> Integer.parseInt(arg)).collect(Collectors.toList()));
        }

        return argsList;
    }

    public int getCalledCount()
    {
        return calledCount;
    }
}
