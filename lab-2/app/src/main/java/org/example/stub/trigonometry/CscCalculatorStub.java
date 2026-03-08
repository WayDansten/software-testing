package org.example.stub.trigonometry;

import java.util.HashMap;
import java.util.Map;

import org.example.math.exception.ToleranceException;
import org.example.math.trigonometry.CscCalculator;

public class CscCalculatorStub extends CscCalculator {
    private static final Map<Double, Double> answers = new HashMap<>();

    static {
        answers.put(-0.5, -2.0858);
        answers.put(-1.0, -1.1884);
        answers.put(-1.2, -1.0729);
        answers.put(-1.25, -1.0538);
        answers.put(-2.0, -1.0998);
        answers.put(-2.3, -1.3410);
        answers.put(-3.95, 1.3828);
        answers.put(-4.4, 1.0509);
        answers.put(-5.2, 1.1319);
        answers.put(-5.35, 1.2445);
        answers.put(-5.57, 1.5285);
        answers.put(-5.69, 1.7889);
        answers.put(-5.87, 2.4905);
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

        return answers.get(x);
    }
}
