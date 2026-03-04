package org.example.math;

import org.example.math.common.FunctionCalculator;
import org.example.math.common.FunctionConstants;
import org.example.math.common.FunctionType;
import org.example.math.exception.ToleranceException;

public class FunctionSystemCalculator extends FunctionCalculator {
    public FunctionSystemCalculator() {
        super(FunctionType.SYSTEM);
    }

    @Override
    protected boolean checkToleranceHit(double x) {
        return Double.isFinite(x) && !Double.isNaN(x) && (Math.abs(MyMath.sin(x)) > FunctionConstants.SINGULARITY_EPSILON);
    }

    @Override
    public double calculate(double x) {
        if (!checkToleranceHit(x)) {
            throw new ToleranceException(String.format(
                    "Argument x = %f is out of tolerance range for the '%s' function.",
                    x, getFunction().getName()));
        }

        if (x > 0) {
            return (((((MyMath.log(x, 10) + MyMath.log(x, 2)) + MyMath.log(x, 3))
            - ((MyMath.log(x, 5) + MyMath.log(x, 10)) / (MyMath.log(x, 10) + MyMath.log(x, 2))))
            - (((MyMath.log(x, 2) / MyMath.log(x, 5)) / MyMath.log(x, 5)) + MyMath.ln(x)))
            + MyMath.ln(x));
        }

        return ((Math.pow(Math.pow(Math.pow((((((((MyMath.cos(x) / MyMath.csc(x)) / MyMath.csc(x)) / (MyMath.cot(x) / MyMath.cot(x)))
        + ((MyMath.sec(x) - MyMath.csc(x)) * MyMath.cos(x)))
        + MyMath.cos(x)) / (MyMath.tan(x) / MyMath.cos(x))) * MyMath.sec(x)), 2), 3), 2))
        + (((MyMath.sin(x) * Math.pow(MyMath.csc(x), 2)) * MyMath.cot(x)) + MyMath.cot(x)));
    }
}
