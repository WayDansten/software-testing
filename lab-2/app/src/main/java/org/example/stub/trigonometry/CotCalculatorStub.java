package org.example.stub.trigonometry;

import java.util.HashMap;
import java.util.Map;

import org.example.math.exception.ToleranceException;
import org.example.math.trigonometry.CotCalculator;

public class CotCalculatorStub extends CotCalculator {
    private static final Map<Double, Double> answers = new HashMap<>();

    static {
        answers.put(-0.5, -1.8305);
        answers.put(-1.0, -0.6421);
        answers.put(-1.2, -0.3888);
        answers.put(-1.25, -0.3323);
        answers.put(-2.0, 0.4577);
        answers.put(-2.3, 0.8935);
        answers.put(-3.95, -0.9550);
        answers.put(-4.4, -0.3230);
        answers.put(-5.2, 0.5303);
        answers.put(-5.35, 0.7408);
        answers.put(-5.57, 1.1560);
        answers.put(-5.69, 1.4833);
        answers.put(-5.87, 2.2809);
    }

    @Override
    protected boolean checkToleranceHit(double x) {
        return answers.containsKey(x);
    }

    @Override
    public double calculate(double x) {
        if (!checkToleranceHit(x)) {
            throw new ToleranceException("");
        }

        double result = answers.get(x);
        writeCalculationResult(x, result);
        return result;
    }
}
