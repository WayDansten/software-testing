package org.example.math.logarithmic;

import org.example.math.common.FunctionCalculator;
import org.example.math.common.FunctionType;
import org.example.math.exception.ToleranceException;

public class LogCalculator extends FunctionCalculator {
    private final LnCalculator lnCalculator;

    public LogCalculator() {
        super(FunctionType.LOG);
        this.lnCalculator = new LnCalculator();
    }

    @Override
    protected boolean checkToleranceHit(double x) {
        return Double.isFinite(x) && !Double.isNaN(x) && (x > 0);
    }

    public double calculate(double x) {
        if (!checkToleranceHit(x)) {
            throw new ToleranceException(String.format(
                    "Argument x = %f is out of tolerance range for the '%s' function.",
                    x, getFunction().getName()));
        }

        return lnCalculator.calculate(x);
    }

    public double calculate(double x, int base) {
        if (!checkToleranceHit(x)) {
            throw new ToleranceException(String.format(
                    "Argument x = %f is out of tolerance range for the '%s' function.",
                    x, getFunction().getName()));
        }

        if ((base == 1) || base <= 0) {
            throw new IllegalArgumentException(
                    String.format("Invalid base for logarithm: base = %d, should be > 0 and != 1.", base));
        }

        return lnCalculator.calculate(x) / lnCalculator.calculate(base);
    }

    public double calculate(double x, int base, double epsilon) {
        if (!checkToleranceHit(x)) {
            throw new ToleranceException(String.format(
                    "Argument x = %f is out of tolerance range for the '%s' function.",
                    x, getFunction().getName()));
        }

        if ((base == 1) || base <= 0) {
            throw new IllegalArgumentException(
                    String.format("Invalid base for logarithm: base = %d, should be > 0 and != 1.", base));
        }

        return lnCalculator.calculate(x, epsilon) / lnCalculator.calculate(base, epsilon);
    }
}
