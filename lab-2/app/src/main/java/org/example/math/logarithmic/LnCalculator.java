package org.example.math.logarithmic;

import org.example.math.common.FunctionCalculator;
import org.example.math.common.FunctionType;
import org.example.math.exception.ToleranceException;
import org.example.math.series.LnSeriesExpander;

public class LnCalculator extends FunctionCalculator {
    private final LnSeriesExpander seriesExpander;

    public LnCalculator() {
        super(FunctionType.LN);
        this.seriesExpander = new LnSeriesExpander();
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

        return seriesExpander.calculate(x, 0.001);
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

        return seriesExpander.calculate(x, epsilon);
    }
}
