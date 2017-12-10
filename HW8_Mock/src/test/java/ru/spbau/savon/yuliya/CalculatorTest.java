package ru.spbau.savon.yuliya;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class CalculatorTest {
    private MyStack<Character> signStack;
    private MyStack<Double> intStack;

    /*
    Test for check right order in ArrayList
     */
    @Test
    @SuppressWarnings("unchecked")
    public void polishNote() throws Exception {
        String ex = "12+3-4";
        signStack = mock(MyStack.class);
        when(signStack.top()).thenReturn('+', '-');
        when(signStack.notEmpty()).thenReturn(false,true, false);
        when(signStack.size()).thenReturn(1);
        ArrayList<String> ans = Calculator.polishNote(ex, signStack);
        assertEquals("12", ans.get(0));
        assertEquals("3", ans.get(1));
        assertEquals("+", ans.get(2));
        assertEquals("4", ans.get(3));
        assertEquals("-", ans.get(4));
    }

    /*
    Check how works Calculate with Polish Notification
     */
    @Test
    public void calculatePolishNote() throws Exception {
        intStack = mock(MyStack.class);
        when(intStack.top()).thenReturn(12.0, 3.0, 15.0, 6.0, 2.5, 2.0, 0.5);
        ArrayList<String> str = new ArrayList<String>();
        str.add("12");
        str.add("3");
        str.add("+");
        str.add("6");
        str.add("/");
        str.add("2");
        str.add("-");
        double ans = Calculator.calculatePolishNote(str, intStack);
        assertEquals(ans, 0.5, 1e-8);
    }

    /*
    Test check how functions works with double values and brackets
     */
    @Test
    public void calculate() throws Exception {
        String ex = "12 - (4/8 + 5)";
        signStack = mock(MyStack.class);
        when(signStack.top()).thenReturn('(', '/', '(', '+', '(', '-');
        when(signStack.notEmpty()).thenReturn(false, true, true, true, true, true);
        when(signStack.size()).thenReturn(1);

        intStack = mock(MyStack.class);
        when(intStack.top()).thenReturn(4.0, 8.0, 0.5, 5.0, 12.0, 5.5, 6.5);

        double ans = Calculator.calculate(ex, signStack, intStack);
        assertEquals(ans, 6.5, 1e-8);
    }
}