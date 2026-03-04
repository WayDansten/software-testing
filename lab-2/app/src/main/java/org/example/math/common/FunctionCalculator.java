package org.example.math.common;

import org.example.math.exception.ToleranceException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class FunctionCalculator {
    @Getter
    private final FunctionType function;

    protected abstract boolean checkToleranceHit(double x);

    public abstract double calculate(double x);

    public double calculate(double x, double epsilon) {
        if (!checkToleranceHit(x)) {
            throw new ToleranceException(String
                    .format("Argument x = %f is out of tolerance range for the '%s' function.", x, function.getName()));
        }

        return calculate(x);
    }
}
