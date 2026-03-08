package org.example.integrational;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.math.FunctionSystemCalculator;
import org.example.math.MyMath;
import org.example.math.logarithmic.LnCalculator;
import org.example.math.logarithmic.LogCalculator;
import org.example.math.trigonometry.CosCalculator;
import org.example.math.trigonometry.CotCalculator;
import org.example.math.trigonometry.CscCalculator;
import org.example.math.trigonometry.SecCalculator;
import org.example.math.trigonometry.SinCalculator;
import org.example.math.trigonometry.TanCalculator;
import org.example.stub.series.LnSeriesExpanderStub;
import org.example.stub.series.SinSeriesExpanderStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class SeriesMockTest {
    private FunctionSystemCalculator calculator;

    @BeforeEach
    void setUp() {
        SinCalculator mockedSinCalculator = new SinCalculator(new SinSeriesExpanderStub());
        CosCalculator mockedCosCalculator = new CosCalculator(mockedSinCalculator);
        LnCalculator mockedLnCalculator = new LnCalculator(new LnSeriesExpanderStub());
        this.calculator = new FunctionSystemCalculator(new MyMath(
            mockedSinCalculator,
            mockedCosCalculator,
            new TanCalculator(mockedSinCalculator, mockedCosCalculator),
            new CotCalculator(mockedSinCalculator, mockedCosCalculator),
            new SecCalculator(mockedCosCalculator),
            new CscCalculator(mockedSinCalculator),
            mockedLnCalculator,
            new LogCalculator(mockedLnCalculator)
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
