package org.example.math.series;

import org.example.math.common.FactorialCalculator;
import org.example.math.common.FunctionCalculator;
import org.example.math.common.FunctionType;
import org.example.math.exception.ToleranceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class SeriesExpander extends FunctionCalculator {
    private static final int MAX_TERMS = 100;

    protected final FactorialCalculator calculator;

    protected SeriesExpander(FunctionType type) {
        super(type);
        this.calculator = new FactorialCalculator();
    }

    protected abstract double calculateNthTerm(double x, int n);

    public double calculate(double x, int termCount) {
        if (!checkToleranceHit(x)) {
            throw new ToleranceException(String.format(
                    "Argument x = %f is out of tolerance range for the '%s' function.",
                    x, getFunction().getName()));
        }

        if (termCount < 0) {
            throw new IllegalArgumentException(
                    String.format("Cannot calculate a series expansion for n = %d of first terms", termCount));
        }

        double result = 0;
        for (int i = 0; i <= termCount; i++) {
            result = result + calculateNthTerm(x, i);
        }

        return result;
    }

    public double calculate(double x, double epsilon) {
        if (!checkToleranceHit(x)) {
            throw new ToleranceException(String.format(
                    "Argument x = %f is out of tolerance range for the '%s' function.",
                    x, getFunction().getName()));
        }

        double result = 0;
        double delta = 0;
        double previousTerm = 0;
        double nextTerm = 0;
        for (int i = 0; i <= MAX_TERMS; i++) {
            nextTerm = calculateNthTerm(x, i);
            result = result + nextTerm;
            delta = Math.abs(nextTerm - previousTerm);
            if (delta <= epsilon) {
                return result;
            }
            previousTerm = nextTerm;
        }

        log.warn(String.format(
                "Could not calculate function '%s' for x = %f with accuracy epsilon = %f. Reached accuracy of delta = %f",
                getFunction().getName(), x, epsilon, delta));
        return result;
    }
}
