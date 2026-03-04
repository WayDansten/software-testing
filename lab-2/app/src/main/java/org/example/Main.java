package org.example;

import org.example.math.FunctionSystemCalculator;

public class Main {
    public static void main(String[] args) {
        FunctionSystemCalculator calculator = new FunctionSystemCalculator();
        System.out.println(calculator.calculate(-2));
        System.out.println(calculator.calculate(0.5));
    }
}
