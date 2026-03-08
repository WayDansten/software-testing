package org.example.unit.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.example.math.exception.ToleranceException;
import org.example.math.trigonometry.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

class TrigonometryTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/test-data/trigonometry/sin_values.csv", numLinesToSkip = 1)
    void sinCalculatorTest(double x, double expected, double resultEpsilon, double convergenceEpsilon) {
        SinCalculator calculator = new SinCalculator();
        double actual = calculator.calculate(x, convergenceEpsilon);
        
        assertEquals(expected, actual, resultEpsilon);
    }

    @ParameterizedTest
    @CsvSource({
        "NaN",
        "Infinity",
        "-Infinity"
    })
    void sinCalculatorToleranceTest(double x) {
        SinCalculator calculator = new SinCalculator();
        assertThrows(ToleranceException.class, () -> calculator.calculate(x));
    }
    
    @ParameterizedTest
    @CsvFileSource(resources = "/test-data/trigonometry/cos_values.csv", numLinesToSkip = 1)
    void cosCalculatorTest(double x, double expected, double resultEpsilon, double convergenceEpsilon) {
        CosCalculator calculator = new CosCalculator();
        double actual = calculator.calculate(x, convergenceEpsilon);
        
        assertEquals(expected, actual, resultEpsilon);
    }

    @ParameterizedTest
    @CsvSource({
        "NaN",
        "Infinity",
        "-Infinity"
    })
    void cosCalculatorToleranceTest(double x) {
        CosCalculator calculator = new CosCalculator();
        assertThrows(ToleranceException.class, () -> calculator.calculate(x));
    }
    
    @ParameterizedTest
    @CsvFileSource(resources = "/test-data/trigonometry/tan_values.csv", numLinesToSkip = 1)
    void tanCalculatorTest(double x, double expected, double resultEpsilon, double convergenceEpsilon) {
        TanCalculator calculator = new TanCalculator();
        double actual = calculator.calculate(x, convergenceEpsilon);
        
        assertEquals(expected, actual, resultEpsilon);
    }

    @ParameterizedTest
    @CsvSource({
        "NaN",
        "Infinity",
        "-Infinity",
        "1.5708",
        "-1.5708"
    })
    void tanCalculatorToleranceTest(double x) {
        TanCalculator calculator = new TanCalculator();
        assertThrows(ToleranceException.class, () -> calculator.calculate(x));
    }
    
    @ParameterizedTest
    @CsvFileSource(resources = "/test-data/trigonometry/cot_values.csv", numLinesToSkip = 1)
    void cotCalculatorTest(double x, double expected, double resultEpsilon, double convergenceEpsilon) {
        CotCalculator calculator = new CotCalculator();
        double actual = calculator.calculate(x, convergenceEpsilon);
        
        assertEquals(expected, actual, resultEpsilon);
    }

    @ParameterizedTest
    @CsvSource({
        "NaN",
        "Infinity",
        "-Infinity",
        "0.0",
        "3.1416"
    })
    void cotCalculatorToleranceTest(double x) {
        CotCalculator calculator = new CotCalculator();
        assertThrows(ToleranceException.class, () -> calculator.calculate(x));
    }
    
    @ParameterizedTest
    @CsvFileSource(resources = "/test-data/trigonometry/sec_values.csv", numLinesToSkip = 1)
    void secCalculatorTest(double x, double expected, double resultEpsilon, double convergenceEpsilon) {
        SecCalculator calculator = new SecCalculator();
        double actual = calculator.calculate(x, convergenceEpsilon);
        
        assertEquals(expected, actual, resultEpsilon);
    }

    @ParameterizedTest
    @CsvSource({
        "NaN",
        "Infinity",
        "-Infinity",
        "1.5708",
        "-1.5708"
    })
    void secCalculatorToleranceTest(double x) {
        SecCalculator calculator = new SecCalculator();
        assertThrows(ToleranceException.class, () -> calculator.calculate(x));
    }
    
    @ParameterizedTest
    @CsvFileSource(resources = "/test-data/trigonometry/csc_values.csv", numLinesToSkip = 1)
    void cscCalculatorTest(double x, double expected, double resultEpsilon, double convergenceEpsilon) {
        CscCalculator calculator = new CscCalculator();
        double actual = calculator.calculate(x, convergenceEpsilon);
        
        assertEquals(expected, actual, resultEpsilon);
    }

    @ParameterizedTest
    @CsvSource({
        "NaN",
        "Infinity",
        "-Infinity",
        "0.0",
        "3.1416"
    })
    void cscCalculatorToleranceTest(double x) {
        CscCalculator calculator = new CscCalculator();
        assertThrows(ToleranceException.class, () -> calculator.calculate(x));
    }
}
