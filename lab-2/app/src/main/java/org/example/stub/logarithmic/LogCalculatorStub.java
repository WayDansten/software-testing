package org.example.stub.logarithmic;

import java.util.HashMap;
import java.util.Map;

import org.example.math.exception.ToleranceException;
import org.example.math.logarithmic.LogCalculator;

public class LogCalculatorStub extends LogCalculator {
    private static final Map<Double, Map<Double, Double>> answers = new HashMap<>();

    static {
        answers.put(0.05, Map.of(
            2.0, -4.3219,
            3.0, -2.7268,
            5.0, -1.8614,
            10.0, -1.3010
        ));
        answers.put(0.1, Map.of(
            2.0, -3.3219,
            3.0, -2.0959,
            5.0, -1.4307,
            10.0, -1.0000
        ));
        answers.put(0.5, Map.of(
            2.0, -1.0000,
            3.0, -0.6309,
            5.0, -0.4307,
            10.0, -0.3010
        ));
        answers.put(1.5, Map.of(
            2.0, 0.5850,
            3.0, 0.3691,
            5.0, 0.2519,
            10.0, 0.1761
        ));
        answers.put(2.0, Map.of(
            2.0, 1.0000,
            3.0, 0.6309,
            5.0, 0.4307,
            10.0, 0.3010
        ));
        answers.put(2.5, Map.of(
            2.0, 1.3219,
            3.0, 0.8340,
            5.0, 0.5693,
            10.0, 0.3979
        ));
        answers.put(3.0, Map.of(
            2.0, 1.5850,
            3.0, 1.0000,
            5.0, 0.6826,
            10.0, 0.4771
        ));
        answers.put(4.0, Map.of(
            2.0, 2.0000,
            3.0, 1.2619,
            5.0, 0.8614,
            10.0, 0.6021
        ));
        answers.put(5.0, Map.of(
            2.0, 2.3219,
            3.0, 1.4650,
            5.0, 1.0000,
            10.0, 0.6990
        ));
        answers.put(9.0, Map.of(
            2.0, 3.1699,
            3.0, 2.0000,
            5.0, 1.3652,
            10.0, 0.9542
        ));
        answers.put(12.5, Map.of(
            2.0, 3.6439,
            3.0, 2.2990,
            5.0, 1.5693,
            10.0, 1.0969
        ));
        answers.put(20.0, Map.of(
            2.0, 4.3219,
            3.0, 2.7268,
            5.0, 1.8614,
            10.0, 1.3010
        ));
    }

    @Override
    protected boolean checkToleranceHit(double x) {
        return answers.containsKey(x);
    }

    @Override
    public double calculate(double x, double base) {
        if (!checkToleranceHit(x)) {
            throw new ToleranceException("");
        }

        if (!answers.get(x).containsKey(base)) {
            throw new IllegalArgumentException();
        }

        double result = answers.get(x).get(base);
        writeCalculationResult(x, result);
        return result;
    }
}
