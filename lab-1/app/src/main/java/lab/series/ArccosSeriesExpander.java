package lab.series;

import lab.series.exception.ArgumentException;
import lab.series.exception.ToleranceException;

public class ArccosSeriesExpander {
    private double factorial(int x) {
        if (x < 0) {
            throw new ToleranceException(String.format("Cannot take factorial of a negative integer: %d", x));
        }
        return x == 0 ? 1 : x * factorial(x - 1);
    }

    private boolean checkToleranceHit(double x) {
        return !Double.isNaN(x) && Double.isFinite(x) && (x >= -1.0 && x <= 1.0);
    }

    private double calculateNthTerm(double x, int n) {
        return (factorial(2 * n) * Math.pow(x, 2 * n + 1.0))
                / (Math.pow(4, n) * Math.pow(factorial(n), 2) * (2 * n + 1));
    }

    public double expandToSeries(double x, int termCount) {
        if (!checkToleranceHit(x)) {
            throw new ToleranceException(String.format(
                    "Argument x = %f is out of tolerance range for 'arccos' function. Argument should be in range: -1 <= x <= 1",
                    x));
        }

        if (termCount < 0) {
            throw new ArgumentException(
                    String.format("Cannot calculate a series expansion for n = %d of first terms", termCount));
        }

        double result = Math.PI / 2;
        for (int i = 0; i <= termCount; i++) {
            result = result - calculateNthTerm(x, i);
        }

        return result;
    }
}
