package org.example.integrational;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.math.FunctionSystemCalculator;
import org.example.math.MyMath;
import org.example.stub.logarithmic.LnCalculatorStub;
import org.example.stub.logarithmic.LogCalculatorStub;
import org.example.stub.trigonometry.CosCalculatorStub;
import org.example.stub.trigonometry.CotCalculatorStub;
import org.example.stub.trigonometry.CscCalculatorStub;
import org.example.stub.trigonometry.SecCalculatorStub;
import org.example.stub.trigonometry.SinCalculatorStub;
import org.example.stub.trigonometry.TanCalculatorStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class FullMockTest {
    private FunctionSystemCalculator calculator;

    @BeforeEach
    void setUp() {
        this.calculator = new FunctionSystemCalculator(new MyMath(
            new SinCalculatorStub(),
            new CosCalculatorStub(),
            new TanCalculatorStub(),
            new CotCalculatorStub(),
            new SecCalculatorStub(),
            new CscCalculatorStub(),
            new LnCalculatorStub(),
            new LogCalculatorStub()
        ));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test-data/system/positive_values.csv", numLinesToSkip = 1)
    void positiveValuesTest(double x, double expected, double resultEpsilon, double convergenceEpsilon) {
        assertEquals(expected, calculator.calculate(x, convergenceEpsilon), resultEpsilon);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test-data/system/negative_values.csv", numLinesToSkip = 1)
    void negativeValuesTest(double x, double expected, double resultEpsilon, double convergenceEpsilon) {
        assertEquals(expected, calculator.calculate(x, convergenceEpsilon), resultEpsilon);
    }
}
