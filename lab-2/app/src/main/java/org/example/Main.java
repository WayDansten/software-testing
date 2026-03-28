package org.example;

import org.example.math.FunctionSystemCalculator;
import org.example.math.exception.ToleranceException;

public class Main {
    public static void main(String[] args) {
        double a = -10.0;
        double b = 0.0;
        double step = 0.1;
        double epsilon = 0.001;

        FunctionSystemCalculator calculator = new FunctionSystemCalculator();

        for (double x = a; x <= b; x += step) {
            try {
                calculator.calculate(x, epsilon);
            } catch (ToleranceException e) {
                // No need for stopping when encountering an out of tolerance range point
            }
        }
    }
}
