package lab.series;

import lab.series.exception.ToleranceException;

public class FactorialCalculator {
    public double factorial(int x) {
        if (x < 0) {
            throw new ToleranceException(String.format("Cannot take factorial of a negative integer: %d", x));
        }
        return x == 0 ? 1.0 : x * factorial(x - 1);
    }
}
