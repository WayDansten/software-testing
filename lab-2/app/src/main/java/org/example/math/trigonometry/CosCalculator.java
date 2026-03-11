package org.example.math.trigonometry;

import org.example.math.common.FunctionCalculator;
import org.example.math.common.FunctionType;
import org.example.math.exception.ToleranceException;
import org.example.math.series.CosSeriesExpander;

public class CosCalculator extends FunctionCalculator {
    private final CosSeriesExpander seriesExpander;

    public CosCalculator() {
        super(FunctionType.COS);
        this.seriesExpander = new CosSeriesExpander();
    }

    public CosCalculator(CosSeriesExpander seriesExpander) {
        super(FunctionType.COS);
        this.seriesExpander = seriesExpander;
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

        double result = seriesExpander.calculate(x);
        result = result > 1.0 ? 1.0 : result;
        result = result < -1.0 ? -1.0 : result;
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

        double result = seriesExpander.calculate(x, epsilon);
        result = result > 1.0 ? 1.0 : result;
        result = result < -1.0 ? -1.0 : result;
        writeCalculationResult(x, result);
        return result;
    }
}
