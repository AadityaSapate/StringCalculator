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
        String customDelimiter = "//(.*)";
        String multiDelimiters = "\\[(.*?)]";
        Pattern customDelimiterPattern = Pattern.compile(customDelimiter);
        Pattern multiDelimiterPattern = Pattern.compile(multiDelimiters);
        String delimiter = ",";
        List<Integer> argsList = new ArrayList<>();
        String[] lines = numberString.split("\n");
        for (int i=0; i< lines.length; i++)
        {
            if(i == 0)
            {
                Matcher matcher = customDelimiterPattern.matcher(lines[i]);
                if(matcher.find())
                {
                    delimiter = matcher.group(1);
                    Matcher matchMultiDelimiters = multiDelimiterPattern.matcher(delimiter);
                    StringBuilder stringBuilder = new StringBuilder();
                    while (matchMultiDelimiters.find()) {

                            stringBuilder.append(matchMultiDelimiters.group(1));
                            stringBuilder.append("|");
                    }
                    if(stringBuilder.length() > 0)
                    {
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                        delimiter = stringBuilder.toString();
                    }
                    continue;
                }
            }

            String[] args = lines[i].split(delimiter.toString());
            argsList.addAll(Arrays.stream(args).map(arg -> Integer.parseInt(arg)).collect(Collectors.toList()));
        }

        return argsList;
    }

    public int getCalledCount()
    {
        return calledCount;
    }
}
