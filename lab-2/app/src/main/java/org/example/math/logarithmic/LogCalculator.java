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

    public LogCalculator(LnCalculator lnCalculator) {
        super(FunctionType.LOG);
        this.lnCalculator = lnCalculator;
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

    @Override
    public double calculate(double x, double base) {
        if (!checkToleranceHit(x)) {
            throw new ToleranceException(String.format(
                    "Argument x = %f is out of tolerance range for the '%s' function.",
                    x, getFunction().getName()));
        }

        if ((base == 1) || base <= 0 || !Double.isFinite(base) || Double.isNaN(base)) {
            throw new IllegalArgumentException(
                    String.format("Invalid base for logarithm: base = %d, should be > 0 and != 1.", base));
        }

        return lnCalculator.calculate(x) / lnCalculator.calculate(base);
    }

    public double calculate(double x, double base, double epsilon) {
        if (!checkToleranceHit(x)) {
            throw new ToleranceException(String.format(
                    "Argument x = %f is out of tolerance range for the '%s' function.",
                    x, getFunction().getName()));
        }

        if ((base == 1) || base <= 0 || !Double.isFinite(base) || Double.isNaN(base)) {
            throw new IllegalArgumentException(
                    String.format("Invalid base for logarithm: base = %d, should be > 0 and != 1.", base));
        }

        return lnCalculator.calculate(x, epsilon) / lnCalculator.calculate(base, epsilon);
    }
}
