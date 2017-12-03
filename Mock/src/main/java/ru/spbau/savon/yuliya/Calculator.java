package ru.spbau.savon.yuliya;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class Calculator {
    public static ArrayList<String> PolishNote (@NotNull String expression, @NotNull MyStack<Character> signStack) {
        StringBuilder curInt = new StringBuilder();
        boolean needToAddInt = true;
        ArrayList<String> ans = new ArrayList<>();
        for(int i = 0; i < expression.length(); i++) {
            Character curSymbol = expression.charAt(i);
            if (curSymbol >= '0' & curSymbol <= '9') {
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
        while (signStack.size() > 0) {
            ans.add(signStack.top().toString());
            signStack.pop();
        }
        return ans;
    }

    private static boolean count (char operator1, char operator2) {
        return (priority(operator1) >= priority(operator2));
    }

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

    public static int calculate (@NotNull String expression, @NotNull MyStack<Character> signStack, @NotNull MyStack<Integer> intStack) {
        return calculatePolishNote(PolishNote(expression, signStack), intStack);
    }
}
