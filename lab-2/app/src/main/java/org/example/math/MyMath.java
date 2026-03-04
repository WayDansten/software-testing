package org.example.math;

import org.example.math.logarithmic.LnCalculator;
import org.example.math.logarithmic.LogCalculator;
import org.example.math.trigonometry.CosCalculator;
import org.example.math.trigonometry.CotCalculator;
import org.example.math.trigonometry.CscCalculator;
import org.example.math.trigonometry.SecCalculator;
import org.example.math.trigonometry.SinCalculator;
import org.example.math.trigonometry.TanCalculator;

public class MyMath {
    private static final SinCalculator sinCalculator = new SinCalculator();
    private static final CosCalculator cosCalculator = new CosCalculator();

    private static final TanCalculator tanCalculator = new TanCalculator();
    private static final CotCalculator cotCalculator = new CotCalculator();

    private static final SecCalculator secCalculator = new SecCalculator();
    private static final CscCalculator cscCalculator = new CscCalculator();

    private static final LnCalculator lnCalculator = new LnCalculator();
    private static final LogCalculator logCalculator = new LogCalculator();

    private MyMath() {}

    public static double sin(double x) {
        return sinCalculator.calculate(x);
    }

    public static double sin(double x, double epsilon) {
        return sinCalculator.calculate(x, epsilon);
    }

    public static double cos(double x) {
        return cosCalculator.calculate(x);
    }

    public static double cos(double x, double epsilon) {
        return cosCalculator.calculate(x, epsilon);
    }

    public static double tan(double x) {
        return tanCalculator.calculate(x);
    }

    public static double tan(double x, double epsilon) {
        return tanCalculator.calculate(x, epsilon);
    }

    public static double cot(double x) {
        return cotCalculator.calculate(x);
    }

    public static double cot(double x, double epsilon) {
        return cotCalculator.calculate(x, epsilon);
    }

    public static double sec(double x) {
        return secCalculator.calculate(x);
    }

    public static double sec(double x, double epsilon) {
        return secCalculator.calculate(x, epsilon);
    }

    public static double csc(double x) {
        return cscCalculator.calculate(x);
    }

    public static double csc(double x, double epsilon) {
        return cscCalculator.calculate(x, epsilon);
    }

    public static double ln(double x) {
        return lnCalculator.calculate(x);
    }

    public static double ln(double x, double epsilon) {
        return lnCalculator.calculate(x, epsilon);
    }

    public static double log(double x, int base) {
        return logCalculator.calculate(x, base);
    }

    public static double log(double x, int base, double epsilon) {
        return logCalculator.calculate(x, base, epsilon);
    }
}
