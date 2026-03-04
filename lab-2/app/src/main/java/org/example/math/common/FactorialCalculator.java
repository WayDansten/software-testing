package org.example.math.common;

import org.example.math.exception.ToleranceException;

public class FactorialCalculator extends FunctionCalculator {
    public FactorialCalculator() {
        super(FunctionType.FACTORIAL);
    }

    @Override
    protected boolean checkToleranceHit(double x) {
        return Double.isFinite(x) && !Double.isNaN(x) && (x >= 0);
    }
    
    public double factorial(int x) {
        if (!checkToleranceHit(x)) {
            throw new ToleranceException(String.format("Cannot take factorial of a negative integer: %d", x));
        }
        return x == 0 ? 1.0 : x * factorial(x - 1);
    }
}
