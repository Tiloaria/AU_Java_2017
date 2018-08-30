package ru.spbau.savon.yuliya;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyStackTest {
    MyStack<Integer> stack;

    /*
    Every Stack will have 3 numbers at start
     */
    @Before
    public void SetUp() {
        stack = new MyStack<Integer>();
        stack.add(2);
        stack.add(3);
        stack.add(9);
    }

    /*
    Test check how works how Stack work with large numbers of elements
     */
    @org.junit.Test
    public void add() throws Exception {
        for (int i = 0; i < 2_000_000; i++) {
            stack.add(1);
        }
    }

    /*
    Test check numbers of values after pop and that it's doing nothing if Stack is empty
     */
    @org.junit.Test
    public void pop() throws Exception {
        assertEquals(stack.size(), 3);
        stack.pop();
        assertEquals(stack.size(), 2);
        stack.pop();
        assertEquals(stack.size(), 1);
        stack.pop();
        assertEquals(stack.size(), 0);
    }

    /*
    Check order of elements in stack.
     */
    @org.junit.Test
    public void top() throws Exception {
        for (int i = 0; i < 10; i++) {
            stack.add(i);
        }
        for (Integer i = 9; i >= 0; i--) {
            assertEquals(stack.top(), i);
            stack.pop();
        }
        assertEquals(stack.top(), (Integer) 9);
        stack.pop();
        assertEquals(stack.top(), (Integer) 3);
        stack.pop();
        assertEquals(stack.top(), (Integer) 2);
        stack.pop();
    }

    /*
    Check size after other methods
     */
    @org.junit.Test
    public void size() throws Exception {
        stack.add(1);
        assertEquals(stack.size(), 4);
        stack.top();
        assertEquals(stack.size(), 4);
        for (int i = 0; i < 4; i++)
            stack.pop();
        assertEquals(stack.size(), 0);
    }

    /*
    Check what happen if call notEmpty after other methods
     */
    @org.junit.Test
    public void notEmpty() throws Exception {
        assertTrue(stack.notEmpty());
        stack.add(1);
        assertTrue(stack.notEmpty());
        stack.top();
        assertTrue(stack.notEmpty());
        for (int i = 0; i < 4; i++)
            stack.pop();
        assertFalse(stack.notEmpty());
    }

    @org.junit.Test
    public void largeTest() throws Exception {
        for (int i = 0; i < 2_000_000; i++) {
            stack.add(i);
        }
        for (int i = 1_999_999; i >= 0; i--) {
            Integer curI = i;
            assertEquals(curI, stack.top());
            stack.pop();
            assertFalse(stack.top().equals(i));
        }
    }
}