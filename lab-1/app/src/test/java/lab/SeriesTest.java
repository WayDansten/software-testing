package lab;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import lab.series.ArccosSeriesExpander;
import lab.series.FactorialCalculator;
import lab.series.exception.ArgumentException;
import lab.series.exception.ToleranceException;

class SeriesTest {
    @Test
    void innerPointsTest() {
        double epsilon = 0.001;
        int n = 10;

        List<Double> points = List.of(-0.8, -0.2, 0.0, 0.021, 0.8);
        List<Double> trueValues = List.of(2.498, 1.772, 1.571, 1.55, 0.644);
        Map<Double, Double> testPairs = new HashMap<>();

        for (int i = 0; i < points.size(); i++) {
            testPairs.put(points.get(i), trueValues.get(i));
        }

        ArccosSeriesExpander seriesExpander = new ArccosSeriesExpander();

        assertAll(testPairs.entrySet().stream().map(pair -> () -> {
            double result = seriesExpander.expandToSeries(pair.getKey(), n);
            double trueValue = pair.getValue();
            assertEquals(trueValue, result, epsilon, String.format("Failed at point %f", pair.getKey()));
        }));
    }

    @Test
    void middlePointsTest() {
        double epsilon = 0.01;
        int n = 40;

        List<Double> points = List.of(-0.95, -0.9, -0.8, 0.8, 0.853, 0.95);
        List<Double> trueValues = List.of(2.824, 2.691, 2.498, 0.644, 0.549, 0.318);
        Map<Double, Double> testPairs = new HashMap<>();

        for (int i = 0; i < points.size(); i++) {
            testPairs.put(points.get(i), trueValues.get(i));
        }

        ArccosSeriesExpander seriesExpander = new ArccosSeriesExpander();

        assertAll(testPairs.entrySet().stream().map(pair -> () -> {
            double result = seriesExpander.expandToSeries(pair.getKey(), n);
            double trueValue = pair.getValue();
            assertEquals(trueValue, result, epsilon, String.format("Failed at x = %f", pair.getKey()));
        }));

    }

    @Test
    void edgePointsTest() {
        double epsilon = 0.1;
        int n = 70;

        List<Double> points = List.of(-1.0, -0.999, -0.95, 0.95, 0.987, 1.0);
        List<Double> trueValues = List.of(3.142, 3.097, 2.824, 0.318, 0.161, 0.0);
        Map<Double, Double> testPairs = new HashMap<>();

        for (int i = 0; i < points.size(); i++) {
            testPairs.put(points.get(i), trueValues.get(i));
        }

        ArccosSeriesExpander seriesExpander = new ArccosSeriesExpander();

        assertAll(testPairs.entrySet().stream().map(pair -> () -> {
            double result = seriesExpander.expandToSeries(pair.getKey(), n);
            double trueValue = pair.getValue();
            assertEquals(trueValue, result, epsilon, String.format("Failed at x = %f", pair.getKey()));
        }));
    }

    @Test
    void toleranceRangeTest() {
        int n = 0;

        List<Double> points = List.of(-Double.MAX_VALUE, -1.001, 1.00001, Double.MAX_VALUE, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NaN);
        ArccosSeriesExpander seriesExpander = new ArccosSeriesExpander();

        assertAll(points.stream().map(point -> () -> {
            assertThrows(ToleranceException.class, () -> seriesExpander.expandToSeries(point, n),
                    String.format("Exception not thrown for x = %f", point));
        }));
    }

    @Test
    void invalidSeriesLengthTest() {
        int n = -1;
        double x = 0;

        ArccosSeriesExpander seriesExpander = new ArccosSeriesExpander();
        assertThrows(ArgumentException.class, () -> seriesExpander.expandToSeries(x, n));
    }

    @Test
    void factorialTest() {
        List<Integer> argumentValues = List.of(0, 1, 4);
        List<Double> trueValues = List.of(1.0, 1.0, 24.0);
        Map<Integer, Double> testPairs = new HashMap<>();

        for (int i = 0; i < argumentValues.size(); i++) {
            testPairs.put(argumentValues.get(i), trueValues.get(i));
        }

        FactorialCalculator factorialCalculator = new FactorialCalculator();
        
        assertAll(testPairs.entrySet().stream().map(pair -> () -> {
            double result = factorialCalculator.factorial(pair.getKey());
            double trueValue = pair.getValue();
            assertEquals(trueValue, result, 0, String.format("Failed at x = %d", pair.getKey()));
        }));
    }

    @Test
    void factorialToleranceTest() {
        List<Integer> argumentValues = List.of(-1, Integer.MIN_VALUE);

        FactorialCalculator factorialCalculator = new FactorialCalculator();
        assertAll(argumentValues.stream().map(value -> () -> {
            assertThrows(ToleranceException.class, () -> factorialCalculator.factorial(value),
                    String.format("Exception not thrown for x = %d", value));
        }));
    }
}
