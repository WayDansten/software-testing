package org.example.unit.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.example.math.exception.ToleranceException;
import org.example.math.logarithmic.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

class LogarithmTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/test-data/logarithmic/ln_values.csv", numLinesToSkip = 1)
    void lnCalculatorTest(double x, double expected, double resultEpsilon, double convergenceEpsilon) {
        LnCalculator calculator = new LnCalculator();
        double actual = calculator.calculate(x, convergenceEpsilon);
        
        assertEquals(expected, actual, resultEpsilon);
    }

    @ParameterizedTest
    @CsvSource({
        "NaN",
        "Infinity",
        "-Infinity",
        "-1.0"
    })
    void lnCalculatorToleranceTest(double x) {
        LnCalculator calculator = new LnCalculator();
        assertThrows(ToleranceException.class, () -> calculator.calculate(x));
    }
    
    @ParameterizedTest
    @CsvFileSource(resources = "/test-data/logarithmic/log_values.csv", numLinesToSkip = 1)
    void logCalculatorTest(double x, double base, double expected, double resultEpsilon, double convergenceEpsilon) {
        LogCalculator calculator = new LogCalculator();
        double actual = calculator.calculate(x, base, convergenceEpsilon);
        
        assertEquals(expected, actual, resultEpsilon);
    }

    @ParameterizedTest
    @CsvSource({
        "NaN, 2.0",
        "Infinity, 2.0",
        "-Infinity, 2.0",
        "-1.0, 2.0",
    })
    void logCalculatorToleranceTest(double x, double base) {
        LogCalculator calculator = new LogCalculator();
        assertThrows(ToleranceException.class, () -> calculator.calculate(x, base));
    }

    @ParameterizedTest
    @CsvSource({
        "NaN",
        "Infinity",
        "-Infinity",
        "1.0",
        "-1.0"
    })
    void logCalculatorInvalidBaseTest(double base) {
        LogCalculator calculator = new LogCalculator();
        double x = 2.0;
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(x, base));
    }
}
