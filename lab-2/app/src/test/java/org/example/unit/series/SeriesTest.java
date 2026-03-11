package org.example.unit.series;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.example.math.exception.ToleranceException;
import org.example.math.series.LnSeriesExpander;
import org.example.math.series.CosSeriesExpander;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

class SeriesTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/test-data/trigonometry/cos_values.csv", numLinesToSkip = 1)
    void cosSeriesTest(double x, double expected, double resultEpsilon, double convergenceEpsilon) {
        CosSeriesExpander expander = new CosSeriesExpander();
        double actual = expander.calculate(x, convergenceEpsilon);
        
        assertEquals(expected, actual, resultEpsilon);
    }

    @ParameterizedTest
    @CsvSource({
        "NaN",
        "Infinity",
        "-Infinity"
    })
    void cosSeriesToleranceTest(double x) {
        CosSeriesExpander expander = new CosSeriesExpander();
        assertThrows(ToleranceException.class, () -> expander.calculate(x));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test-data/logarithmic/ln_values.csv", numLinesToSkip = 1)
    void lnSeriesTest(double x, double expected, double resultEpsilon, double convergenceEpsilon) {
        LnSeriesExpander expander = new LnSeriesExpander();
        double actual = expander.calculate(x, convergenceEpsilon);
        
        assertEquals(expected, actual, resultEpsilon);
    }

    @ParameterizedTest
    @CsvSource({
        "NaN",
        "Infinity",
        "-Infinity",
        "-1.0"
    })
    void lnSeriesToleranceTest(double x) {
        LnSeriesExpander expander = new LnSeriesExpander();
        assertThrows(ToleranceException.class, () -> expander.calculate(x));
    }
}
