package org.example.stub.trigonometry;

import java.util.HashMap;
import java.util.Map;

import org.example.math.exception.ToleranceException;
import org.example.math.trigonometry.TanCalculator;

public class TanCalculatorStub extends TanCalculator {
    private static final Map<Double, Double> answers = new HashMap<>();

    static {
        answers.put(-0.5, -0.5463);
        answers.put(-1.0, -1.5574);
        answers.put(-1.2, -2.5722);
        answers.put(-1.25, -3.0096);
        answers.put(-2.0, 2.1850);
        answers.put(-2.3, 1.1192);
        answers.put(-3.95, -1.0471);
        answers.put(-4.4, -3.0963);
        answers.put(-5.2, 1.8856);
        answers.put(-5.35, 1.3498);
        answers.put(-5.57, 0.8651);
        answers.put(-5.69, 0.6742);
        answers.put(-5.87, 0.4384);
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
