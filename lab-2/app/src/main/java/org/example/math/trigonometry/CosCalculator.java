package org.example.math.trigonometry;

import java.math.BigDecimal;

import org.example.math.common.FunctionCalculator;
import org.example.math.common.FunctionType;
import org.example.math.exception.ToleranceException;

public class CosCalculator extends FunctionCalculator {
    private final SinCalculator sinCalculator;

    public CosCalculator() {
        super(FunctionType.COS);
        this.sinCalculator = new SinCalculator();
    }

    public CosCalculator(SinCalculator sinCalculator) {
        super(FunctionType.COS);
        this.sinCalculator = sinCalculator;
    }

    @Override
    protected boolean checkToleranceHit(double x) {
        return Double.isFinite(x) && !Double.isNaN(x);
    }

    @Override
    public double calculate(double x) {
        if (!checkToleranceHit(x)) {
            throw new ToleranceException(String.format(
                    "Argument x = %f is out of tolerance range for the '%s' function.",
                    x, getFunction().getName()));
        }

        BigDecimal preciseX = BigDecimal.valueOf(x);
        double remainder = preciseX.remainder(BigDecimal.valueOf(Math.PI * 2)).doubleValue();
        double radical = Math.max(1 - Math.pow(sinCalculator.calculate(x), 2), 0);
        double result;
        if (remainder >= -Math.PI / 2 && remainder <= Math.PI / 2
            || remainder >= Math.PI * 1.5 && remainder <= Math.PI * 2
            || remainder >= -Math.PI * 2 && remainder <= -Math.PI * 1.5) {
            result = Math.sqrt(radical);
        } else {
            result = -Math.sqrt(radical);
        }
        writeCalculationResult(x, result);
        return result;
    }

    @Override
    public double calculate(double x, double epsilon) {
        if (!checkToleranceHit(x)) {
            throw new ToleranceException(String.format(
                    "Argument x = %f is out of tolerance range for the '%s' function.",
                    x, getFunction().getName()));
        }

        if (epsilon < 0) {
            throw new IllegalArgumentException(
                    String.format("Cannot calculate a function with negative accuracy: epsilon = %f", epsilon));
        }

        BigDecimal preciseX = BigDecimal.valueOf(x);
        double remainder = preciseX.remainder(BigDecimal.valueOf(Math.PI * 2)).doubleValue();
        double radical = Math.max(1 - Math.pow(sinCalculator.calculate(x, epsilon), 2), 0);
        double result;
        if (remainder >= -Math.PI / 2 && remainder <= Math.PI / 2
            || remainder >= Math.PI * 1.5 && remainder <= Math.PI * 2
            || remainder >= -Math.PI * 2 && remainder <= -Math.PI * 1.5) {
            result = Math.sqrt(radical);
        } else {
            result = -Math.sqrt(radical);
        }
        writeCalculationResult(x, result);
        return result;
    }
}
