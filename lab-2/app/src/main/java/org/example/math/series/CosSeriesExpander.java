package org.example.math.series;

import org.example.math.common.FunctionType;

public class CosSeriesExpander extends SeriesExpander {
    public CosSeriesExpander() {
        super(FunctionType.COS);
    }

    @Override
    protected boolean checkToleranceHit(double x) {
        return Double.isFinite(x) && !Double.isNaN(x);
    }

    @Override
    protected double calculateNthTerm(double x, int n) {
        return (Math.pow(-1, n) * Math.pow(x, 2.0 * n)) / factorialCalculator.calculate(2.0 * n);
    }
}
