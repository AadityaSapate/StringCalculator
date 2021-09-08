package com.example;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test String Calculator")
public class StringCalculatorTest {

    @DisplayName("First methods take 0, 1 or 2 numbers")
    @ValueSource(strings = {"", "1","1,2"})
    @ParameterizedTest
    public void add(String arg)
    {
        StringCalculator stringCalculator = new StringCalculator();
        int result = stringCalculator.add(arg);
        if(arg.equals(""))
            assertEquals(0, result);
        if(arg.equals("1"))
            assertEquals(1,result);
        if(arg.equals("1,2"))
            assertEquals(3, result);
    }

    @DisplayName("Allow unknown number of numbers")
    @ValueSource(strings = {"1,2,3,45", "1,3,6","1,2"})
    @ParameterizedTest
    public void addWithUnknownNumbers(String arg)
    {
        StringCalculator stringCalculator = new StringCalculator();
        int result = stringCalculator.add(arg);
        if(arg.equals("1,2,3,45"))
            assertEquals(51, result);
        if(arg.equals("1,3,6"))
            assertEquals(10,result);
        if(arg.equals("1,2"))
            assertEquals(3, result);
    }

    @DisplayName("Allow new lines")
    @ValueSource(strings = {"1\n2,3,45", "1\n3,6","1\n2"})
    @ParameterizedTest
    public void addHandleNewLines(String arg)
    {
        StringCalculator stringCalculator = new StringCalculator();
        int result = stringCalculator.add(arg);
        if(arg.equals("1\n2,3,45"))
            assertEquals(51, result);
        if(arg.equals("1\n3,6"))
            assertEquals(10,result);
        if(arg.equals("1\n2"))
            assertEquals(3, result);
    }
}
