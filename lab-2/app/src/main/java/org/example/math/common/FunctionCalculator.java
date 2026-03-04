package org.example.math.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class FunctionCalculator {
    @Getter
    private final FunctionType function;
    
    protected abstract boolean checkToleranceHit(double x);
}
