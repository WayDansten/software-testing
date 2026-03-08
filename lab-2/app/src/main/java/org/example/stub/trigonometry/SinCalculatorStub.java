package org.example.stub.trigonometry;

import java.util.HashMap;
import java.util.Map;

import org.example.math.exception.ToleranceException;
import org.example.math.trigonometry.SinCalculator;

public class SinCalculatorStub extends SinCalculator {
    private static final Map<Double, Double> answers = new HashMap<>();

    static {
        answers.put(-0.5, -0.4794);
        answers.put(-1.0, -0.8415);
        answers.put(-1.2, -0.9320);
        answers.put(-1.25, -0.9490);
        answers.put(-2.0, -0.9093);
        answers.put(-2.3, -0.7457);
        answers.put(-3.95, 0.7232);
        answers.put(-4.4, 0.9516);
        answers.put(-5.2, 0.8835);
        answers.put(-5.35, 0.8035);
        answers.put(-5.57, 0.6542);
        answers.put(-5.69, 0.5590);
        answers.put(-5.87, 0.4015);

        answers.put(0.05, 0.0500);
        answers.put(0.1, 0.0999);
        answers.put(0.5, 0.4794);
        answers.put(1.5, 0.9975);
        answers.put(2.0, 0.9093);
        answers.put(2.5, 0.5985);
        answers.put(3.0, 0.1411);
        answers.put(4.0, -0.7568);
        answers.put(5.0, -0.9589);
        answers.put(9.0, 0.4121);
        answers.put(12.5, -0.0663);
        answers.put(20.0, 0.9129);
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
