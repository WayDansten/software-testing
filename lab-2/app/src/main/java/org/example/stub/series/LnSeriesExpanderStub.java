package org.example.stub.series;

import java.util.HashMap;
import java.util.Map;

import org.example.math.exception.ToleranceException;
import org.example.math.series.LnSeriesExpander;

public class LnSeriesExpanderStub extends LnSeriesExpander {
    private static final Map<Double, Double> answers = new HashMap<>();

    static {
        answers.put(0.05, -2.9957);
        answers.put(0.1, -2.3026);
        answers.put(0.5, -0.6931);
        answers.put(1.5, 0.4055);
        answers.put(2.0, 0.6931);
        answers.put(2.5, 0.9163);
        answers.put(3.0, 1.0986);
        answers.put(4.0, 1.3863);
        answers.put(5.0, 1.6094);
        answers.put(9.0, 2.1972);
        answers.put(10.0, 2.3026);
        answers.put(12.5, 2.5257);
        answers.put(20.0, 2.9957);
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
