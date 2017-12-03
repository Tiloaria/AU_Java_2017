package ru.spbau.savon.yuliya;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class CalculatorTest {
    private MyStack<Character> signStack;
    private MyStack<Integer> intStack;

    @Test
    @SuppressWarnings("unchecked")
    public void polishNote() throws Exception {
        String ex = "12+3-4";
        signStack = mock(MyStack.class);
        when(signStack.top()).thenReturn('+', '+', '+', '-');
        when(signStack.size()).thenReturn(0,1, 0, 1, 0);
        ArrayList<String> ans = Calculator.polishNote(ex, signStack);
        assertEquals("12", ans.get(0));
        assertEquals("3", ans.get(1));
        assertEquals("+", ans.get(2));
        assertEquals("4", ans.get(3));
        assertEquals("-", ans.get(4));
    }

    @Test
    public void calculatePolishNote() throws Exception {
    }

    @Test
    public void calculate() throws Exception {
    }

}