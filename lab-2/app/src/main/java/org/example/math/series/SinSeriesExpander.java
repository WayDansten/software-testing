package org.example.math.series;

import org.example.math.common.FunctionType;

public class SinSeriesExpander extends SeriesExpander {
    public SinSeriesExpander() {
        super(FunctionType.SIN);
    }

    @Override
    protected boolean checkToleranceHit(double x) {
        return Double.isFinite(x) && !Double.isNaN(x);
    }

    @Override
    protected double calculateNthTerm(double x, int n) {
        return (Math.pow(-1, n) * Math.pow (x, 2 * n + 1.0)) / factorialCalculator.calculate(2 * n + 1);
    }
}
