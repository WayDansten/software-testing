package org.example.math.trigonometry;

import java.math.BigDecimal;

import org.example.math.common.FunctionCalculator;
import org.example.math.common.FunctionType;
import org.example.math.exception.ToleranceException;

public class SinCalculator extends FunctionCalculator {
    private final CosCalculator cosCalculator;

    public SinCalculator() {
        super(FunctionType.SIN);
        this.cosCalculator = new CosCalculator();
    }

    public SinCalculator(CosCalculator cosCalculator) {
        super(FunctionType.SIN);
        this.cosCalculator = cosCalculator;
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
        double radical = 1 - Math.pow(cosCalculator.calculate(x), 2);
        double result;
        if (remainder >= 0 && remainder <= Math.PI
            || remainder >= -Math.PI * 2 && remainder <= -Math.PI) {
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
        double radical = 1 - Math.pow(cosCalculator.calculate(x, epsilon), 2);
        double result;
        if (remainder >= 0 && remainder <= Math.PI
            || remainder >= -Math.PI * 2 && remainder <= -Math.PI) {
            result = Math.sqrt(radical);
        } else {
            result = -Math.sqrt(radical);
        }
        writeCalculationResult(x, result);
        return result;
    }
}
