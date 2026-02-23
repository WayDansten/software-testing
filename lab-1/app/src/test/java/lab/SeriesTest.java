package lab;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import lab.series.ArccosSeriesExpander;
import lab.series.exception.ArgumentException;
import lab.series.exception.ToleranceException;

class SeriesTest {
    @Test
    void innerPointsTest() {
        double epsilon = 0.001;
        int n = 10;

        List<Double> points = List.of(-0.8, -0.7, -0.375, -0.2, 0.0, 0.021, 0.1, 0.407, 0.675, 0.8);
        ArccosSeriesExpander seriesExpander = new ArccosSeriesExpander();

        assertAll(points.stream().map(point -> () -> {
            double result = seriesExpander.expandToSeries(point, n);
            double trueValue = Math.acos(point);
            assertEquals(trueValue, result, epsilon, String.format("Failed at point %f", point));
        }));
    }

    @Test
    void middlePointsTest() {
        double epsilon = 0.01;
        int n = 40;

        List<Double> points = List.of(-0.95, -0.9, -0.827, -0.8, 0.8, 0.853, 0.92, 0.95);
        ArccosSeriesExpander seriesExpander = new ArccosSeriesExpander();

        assertAll(points.stream().map(point -> () -> {
            double result = seriesExpander.expandToSeries(point, n);
            double trueValue = Math.acos(point);
            assertEquals(trueValue, result, epsilon, String.format("Failed at x = %f", point));
        }));

    }

    @Test
    void edgePointsTest() {
        double epsilon = 0.1;
        int n = 70;

        List<Double> points = List.of(-1.0, -0.999, -0.95, 0.95, 0.987, 1.0);
        ArccosSeriesExpander seriesExpander = new ArccosSeriesExpander();

        assertAll(points.stream().map(point -> () -> {
            double result = seriesExpander.expandToSeries(point, n);
            double trueValue = Math.acos(point);
            assertEquals(trueValue, result, epsilon, String.format("Failed at x = %f", point));
        }));
    }

    @Test
    void toleranceRangeTest() {
        int n = 0;

        List<Double> points = List.of(-Double.MAX_VALUE, -235345.0, -1.001, 1.00001, 323.0, Double.MAX_VALUE);
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
}
