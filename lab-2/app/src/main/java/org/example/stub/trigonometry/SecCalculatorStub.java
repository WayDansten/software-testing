package org.example.stub.trigonometry;

import java.util.HashMap;
import java.util.Map;

import org.example.math.exception.ToleranceException;
import org.example.math.trigonometry.SecCalculator;

public class SecCalculatorStub extends SecCalculator {
    private static final Map<Double, Double> answers = new HashMap<>();

    static {
        answers.put(-0.5, 1.1395);
        answers.put(-1.0, 1.8508);
        answers.put(-1.2, 2.7597);
        answers.put(-1.25, 3.1714);
        answers.put(-2.0, -2.4030);
        answers.put(-2.3, -1.5009);
        answers.put(-3.95, -1.4479);
        answers.put(-4.4, -3.2538);
        answers.put(-5.2, 2.1344);
        answers.put(-5.35, 1.6799);
        answers.put(-5.57, 1.3223);
        answers.put(-5.69, 1.2060);
        answers.put(-5.87, 1.0919);
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
