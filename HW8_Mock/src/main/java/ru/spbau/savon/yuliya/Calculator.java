package ru.spbau.savon.yuliya;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Class can convert expression with positive numbers and left-associative binary operators -,+,*,/
 * to Array of strings with Polish Notification and count it.
 */
public class Calculator {
    /**
     * Can convert expression in Array with Polish Notification
     * @param expression incoming expression
     * @param signStack help stack for saving order of operations
     * @return Array with Strings - numbers and signs (in Polish Notification order)
     */
    public static ArrayList<String> polishNote (@NotNull String expression, @NotNull MyStack<Character> signStack) {
        StringBuilder curInt = new StringBuilder();
        boolean needToAddInt = true;
        ArrayList<String> ans = new ArrayList<>();
        for(int i = 0; i < expression.length(); i++) {
            Character curSymbol = expression.charAt(i);
            if (curSymbol >= '0' && curSymbol <= '9') {
                needToAddInt = true;
                curInt.append(curSymbol);
            }
            else {
                if (curSymbol == '+'  || curSymbol == '-' || curSymbol == '*' || curSymbol == '/' || curSymbol == ')') {
                    if (needToAddInt) {
                        ans.add(curInt.toString());
                        curInt = new StringBuilder();
                        needToAddInt = false;
                    }
                    while ((signStack.size() > 0) && (count(curSymbol, signStack.top()))) {
                        if (signStack.top() == '(') {
                            signStack.pop();
                            break;
                        }
                        ans.add(signStack.top().toString());
                        signStack.pop();
                    }
                    if (curSymbol != ')') {
                        signStack.add(curSymbol);
                    }
                }
            }
        }
        if (needToAddInt) {
            ans.add(curInt.toString());
            curInt = new StringBuilder();
            needToAddInt = false;
        }
        while (signStack.size() > 0) { //add remaining operations in array
            ans.add(signStack.top().toString());
            signStack.pop();
        }
        return ans;
    }

    /**
     * compares order of 2 operators
     * @param operator1 first operator
     * @param operator2 second operator
     * @return true if priority of 1st operator is more or equal than 2nd, false otherwise
     */
    private static boolean count (char operator1, char operator2) {
        return (priority(operator1) >= priority(operator2));
    }

    /**
     * count number of priority(than bigger is number - than higher is priority)
     * @param sign operator in type Char
     * @return number of operator priority
     */
    private static int priority (char sign) {
        if (sign == '-' || sign == '+') {
            return 1;
        }
        if (sign == '*' || sign == '/') {
            return 2;
        }
        if (sign == ')') {
            return 3;
        }
        if (sign == '(') {
            return 0;
        }
        return 0;
    }

    /**
     * Calcucate Polish Notification if it's correct
     * @param str Polish Notification wrote in Array of Strings
     * @param intString Stack for intermediate values
     * @return result of expression
     */
    public static int calculatePolishNote (ArrayList<String> str, @NotNull MyStack<Integer> intString) {
        for (int i = 0; i < str.size(); i++) {
            String cur = str.get(i);
            if (cur.charAt(i) <= '9' && cur.charAt(i) >= '0') {
                intString.add(Integer.parseInt(cur));
            }
            else {
                int x2 = intString.top();
                intString.pop();
                int x1 = intString.top();
                intString.pop();
                intString.add(count(x1, x2, cur));
            }
        }
        return intString.top();
    }

    /**
     * Count binary operation
     * @param x1 first value
     * @param x2 second value
     * @param sign operator
     * @return result of applied operator
     */
    private static int count(int x1, int x2, String sign) {
        if (sign == "-") {
            return x1 - x2;
        }
        if (sign == "+") {
            return x1 + x2;
        }

        if (sign == "*") {
            return x1 * x2;
        }

        if (sign == "/") {
            return x1 / x2;
        }
        return 0;
    }

    /**
     * count result of expression with using Polish Notification
     * @param expression income expression
     * @param signStack Stack for creating Array List with Polish Notification
     * @param intStack Stack for intermediate values
     * @return result of expression if it's correct
     */
    public static int calculate (@NotNull String expression, @NotNull MyStack<Character> signStack, @NotNull MyStack<Integer> intStack) {
        return calculatePolishNote(polishNote(expression, signStack), intStack);
    }
}
