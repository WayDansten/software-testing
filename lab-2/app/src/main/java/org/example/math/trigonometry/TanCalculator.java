package org.example.math.trigonometry;

import org.example.math.common.FunctionCalculator;
import org.example.math.common.FunctionConstants;
import org.example.math.common.FunctionType;
import org.example.math.exception.ToleranceException;

public class TanCalculator extends FunctionCalculator {
    private final SinCalculator sinCalculator;
    private final CosCalculator cosCalculator;

    public TanCalculator() {
        super(FunctionType.TAN);
        this.sinCalculator = new SinCalculator();
        this.cosCalculator = new CosCalculator();
    }

    @Override
    protected boolean checkToleranceHit(double x) {
        return Double.isFinite(x) && !Double.isNaN(x)
                && (Math.abs(cosCalculator.calculate(x)) > FunctionConstants.SINGULARITY_EPSILON);
    }

    public double calculate(double x) {
        if (!checkToleranceHit(x)) {
            throw new ToleranceException(String.format(
                    "Argument x = %f is out of tolerance range for the '%s' function.",
                    x, getFunction().getName()));
        }

        return sinCalculator.calculate(x) / cosCalculator.calculate(x);
    }

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

        return sinCalculator.calculate(x) / cosCalculator.calculate(x);
    }
}
