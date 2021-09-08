package com.example;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test String Calculator")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StringCalculatorTest {
    private static StringCalculator stringCalculator;

    @BeforeAll
    public static void initBeforeAll(){
        stringCalculator = new StringCalculator();
    }

    @DisplayName("First methods take 0, 1 or 2 numbers")
    @ValueSource(strings = {"", "1","1,2"})
    @ParameterizedTest
    @Order(1)
    public void add(String arg)
    {
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
    @Order(2)
    public void addWithUnknownNumbers(String arg)
    {
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
    @Order(3)
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


    @DisplayName("Allow new delimiter")
    @ValueSource(strings = {"//;\n2;3;45", "//%\n3%6","//,\n2"})
    @ParameterizedTest
    @Order(4)
    public void addHandleNewDelimiter(String arg)
    {
        int result = stringCalculator.add(arg);
        if(arg.equals("//;\n2;3;45"))
            assertEquals(50, result);
        if(arg.equals("//%\n3%6"))
            assertEquals(9,result);
        if(arg.equals("//,\n2"))
            assertEquals(2, result);
    }

    @DisplayName("Throws exception if negative number")
    @Test
    @Order(5)
    public void checkExceptionForNegativeNumber()
    {
       NegativeNumberException exception = assertThrows(NegativeNumberException.class, () -> {
            stringCalculator.add("-1,3,5");
        });
       String expectedMessage =  "negatives not allowed";
       String actual = exception.getMessage();
       assertTrue(actual.contains(expectedMessage));
    }

    @DisplayName("Throws exception if negative number and check the numbers")
    @Test
    @Order(6)
    public void checkExceptionForNegativeNumbers()
    {
        NegativeNumberException negativeNumberException = assertThrows(NegativeNumberException.class, () -> {
            stringCalculator.add("-1,3,-5");
        });
        String expectedMessage =  "negatives not allowed";
        String actual = negativeNumberException.getMessage();
        assertTrue(actual.contains(expectedMessage));

        List<Integer> negativeIntegers = negativeNumberException.getNumbers();
        assertTrue(negativeIntegers.contains(-1));
        assertTrue(negativeIntegers.contains(-5));
    }

    @DisplayName("Test number of times add() method is called")
    @Test
    @Order(7)
    public void testGetCalledCount()
    {
        int calledCount = stringCalculator.getCalledCount();
        assertEquals(14, calledCount);
    }

    @DisplayName("Number bigger then 1000 should be ignored")
    @ValueSource(strings = {"1,2,3,45,1001", "1,3,6,1003","1,2,1009"})
    @ParameterizedTest
    @Order(2)
    public void addIgnoreBiggerThen1000(String arg)
    {
        int result = stringCalculator.add(arg);
        if(arg.equals("1,2,3,45,1001"))
            assertEquals(51, result);
        if(arg.equals("1,3,6,1003"))
            assertEquals(10,result);
        if(arg.equals("1,2,1009"))
            assertEquals(3, result);
    }


}
