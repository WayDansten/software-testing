package org.example.stub.trigonometry;

import java.util.HashMap;
import java.util.Map;

import org.example.math.exception.ToleranceException;
import org.example.math.trigonometry.CosCalculator;

public class CosCalculatorStub extends CosCalculator {
    private static final Map<Double, Double> answers = new HashMap<>();

    static {
        answers.put(-0.5, 0.8776);
        answers.put(-1.0, 0.5403);
        answers.put(-1.2, 0.3624);
        answers.put(-1.25, 0.3153);
        answers.put(-2.0, -0.4161);
        answers.put(-2.3, -0.6663);
        answers.put(-3.95, -0.6907);
        answers.put(-4.4, -0.3073);
        answers.put(-5.2, 0.4685);
        answers.put(-5.35, 0.5953);
        answers.put(-5.57, 0.7563);
        answers.put(-5.69, 0.8292);
        answers.put(-5.87, 0.9158);
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
