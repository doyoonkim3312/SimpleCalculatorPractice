package doyoonkim;

import javafx.scene.control.Label;

/**
 * <h1>ScientificCalculate</h1>
 * <h3>This class handles all calculations that occurs.</h3>
 */
public class ScientificCalculate {
    private Operator operatorType;
    private Operator operatorAssigned;
    private Operator operatorBfPow;
    private Label mainLabel;
    private double[] numberStored = new double[3];
    private boolean isNumberInLabel = false;

    /**
     * Default constructor for instantiating.
     * @param mainLabel: Label object that shows calculation result.
     */
    public ScientificCalculate(Label mainLabel) {
        operatorType = Operator.NON;
        operatorAssigned = Operator.NON;
        this.mainLabel = mainLabel;
    }

    /**
     * Performs division.
     * @param numberBtnInitClickStat: Trigger value for preventing unnecessary calculation.
     */
    public void divide(boolean numberBtnInitClickStat) {
        //System.out.println("OP type: " + operatorType.name() + ", numberBtnInitClickStat: " + numberBtnInitClickStat);
        if (!numberBtnInitClickStat) {
            arithmeticMultiplication(operatorType, Operator.DIVIDE);
        }
        operatorType = Operator.DIVIDE;
    }

    /**
     * Performs multiplication.
     * @param numberBtnInitClickStat: Trigger value for preventing unnecessary calculation.
     */
    public void multiply(boolean numberBtnInitClickStat) {
        //System.out.println("OP type: " + operatorType.name() + ", numberBtnInitClickStat: " + numberBtnInitClickStat);
        if (!numberBtnInitClickStat) {
            arithmeticMultiplication(operatorType, Operator.MULTIPLY);
        }
        operatorType = Operator.MULTIPLY;
    }

    /**
     * Performs subtraction
     * @param numberBtnInitClickStat: Trigger value for preventing unnecessary calculation.
     */
    public void subtract(boolean numberBtnInitClickStat) {
        //System.out.println("OP type: " + operatorType.name() + ", numberBtnInitClickStat: " + numberBtnInitClickStat);
        if (!numberBtnInitClickStat) {
            arithmeticAddition(operatorType, Operator.SUBTRACT);
        }
        operatorType = Operator.SUBTRACT;
    }

    /**
     * Performs addition.
     * @param numberBtnInitClickStat: Trigger value for preventing unnecessary calculation.
     */
    public void addition(boolean numberBtnInitClickStat) {
        //System.out.println("OP type: " + operatorType.name() + ", numberBtnInitClickStat: " + numberBtnInitClickStat);
        if (!numberBtnInitClickStat) {
            arithmeticAddition(operatorType, Operator.ADDITION);
        }
        operatorType = Operator.ADDITION;
    }

    /**
     * Calculate result based on calculation history.
     * This method also follows mathematical order of operator.
     */
    public void equal() {
        if (operatorAssigned != Operator.NON || operatorType != Operator.NON) {
            double subResult;
            if (numberStored[1] != 0) {
                subResult = basicCalculation(numberStored[1], Double.parseDouble(mainLabel.getText()), operatorType);
                numberStored[0] = basicCalculation(numberStored[0], subResult, operatorAssigned);
                numberStored[1] = 0;
                mainLabel.setText(Double.toString(numberStored[0]));
            } else {
                numberStored[0] = basicCalculation(numberStored[0], Double.parseDouble(mainLabel.getText()), operatorType);
                mainLabel.setText(Double.toString(numberStored[0]));
            }
        }
        operatorAssigned = Operator.NON;
        operatorType = Operator.NON;
    }

    /**
     * Emptying label. Also has an option of Clear and All Clear.
     * @param isNumberInLabel: Triggering value for Clear / All Clear option condition.
     * @return Always return 'false', and this value will be the param value of this method.
     */
    public boolean clear(boolean isNumberInLabel) {
        if (isNumberInLabel) {
            mainLabel.setText("0");
        } else {
            mainLabel.setText("0");
            operatorType = Operator.NON;
            numberStored[0] = 0;
            numberStored[1] = 0;
        }
        return false;
    }

    /**
     * <h1>sqRoot</h1>
     * <p>This method calculates squared root of input value, using Babylonian method.</p>
     * <p>A sequence's term is calculated until its 10th term. Calculate lim is way more precise, but</p>
     * <p>since this is a simple calculator, 10th term of sequence ensures enough decimal value.</p>
     * @param num target number.
     * @return squared root of num.
     */
    public double sqRoot(double num) {
        double base = 0;
        while (base < num) {
            base++;
            if ((base * base) == num) {
                return base;
            }
        }

        double termInSequence = ((base * base) + num) / (2 * base);
        for (int i = 0; i < 10; i++) {
            termInSequence = ((termInSequence * termInSequence) + num) / (2 * termInSequence);
        }
        return Double.parseDouble(String.format("%.9f", termInSequence));
    }


    public double squared(double num) {
        return num * num;
    }

    /**
     * <h1>powE</h1>
     * <p>This method calculates power of e, using parameter value as a exponent</p>
     * @param exp exponent value.
     * @return power of e, using param value as a exponent.
     */
    public double powE(double exp) {
        return Double.parseDouble(String.format("%.8f", Math.pow(StrictMath.E, exp)));
    }

    /**
     * <h1>getPi</h1>
     * <p>This method returns PI value until 9th decimal point.</p>
     * @return PI value until 9th decimal point.
     */
    public double getPi() {
        return Double.parseDouble(String.format("%.9f", StrictMath.PI));
    }

    /**
     * <h1>Percentage</h1>
     * <p>Return percentage value of parameter value</p>
     * @param value
     * @return percentage value of param value.
     */
    public double percentage(double value) {
        return basicCalculation(value, 100, Operator.DIVIDE);
    }


    // this method need to be finalized later.
    public void pow(boolean isNumberInLabel) {
        if (!isNumberInLabel) {
            numberStored[2] = Double.parseDouble(mainLabel.getText());
            operatorBfPow = operatorType;
            operatorType = Operator.POW;
        }
    }

    /**
     * This method calculates factorial value of proper param value.
     * @param value: value used to calculate factorial value.
     * @return <lo>
     *     <li><b>factorial value</b> if <u>param value is valid</u> for calculating factorial</li>
     *     <li><b>NaN</b> if <u>param value is invalid</u> for calculating factorial</li>
     * </lo>
     */
    public double factorial(double value) {
        if (value - (int) value == 0.0) {
            int result = 1;
            for (int i = 0; i < value; i++) {
                result *= (i+1);
            }
            return result;
        } else {
            return Double.NaN;
        }
    }

    /**
     * This method performs trigonometric calculation.
     * @param value: value in degree.
     * @param trigType: Trigonometric operator type for proper calculation.
     * @return proper result of trigonometric calculation.
     */
    public double trigonometric(double value, Operator trigType) {
        switch (trigType) {
            case SIN -> { return Math.sin(StrictMath.toRadians(value)); }
            case COS -> { return Math.cos(StrictMath.toRadians(value)); }
            case TAN -> { return Math.tan(StrictMath.toRadians(value)); }
            default -> { return Double.NaN; }
        }
    }

    /**
     * Checking there's a neg sign before numbers. (For -/+ button.)
     * @param value: Numeric value on label.
     * @return value after performing -/+ button function.
     */
    public double hasNegSign(double value) {
        if (value < 0) {
            return value * -1;
        } else {
            return value;
        }
    }

    /**
     * <h1>basicCalculation</h1>
     * <p>This method performs fundamental arithmetic Calculation</p>
     * <ul>
     *     <li>
     *         <h3>For division</h3>
     *         <p><b>num1</b>: numerator</p>
     *         <p><b>num2</b>: denominator</p>
     *         <p>returns num1 / num2</p>
     *     </li>
     * </ul>
     * @param num1: Assigned Value. (Value that saved from last calculation.)
     * @param num2: Newly typed value.
     * @param operator: operator for calculation.
     * @return value after calculation.
     */
    public double basicCalculation(double num1, double num2, Operator operator) {
        double value = 0;
        switch (operator) {
            case DIVIDE -> { value = num1 / num2;}
            case ADDITION -> {value = num1 + num2; }
            case MULTIPLY -> { value = num1 * num2; }
            case SUBTRACT -> { value = num1 - num2; }
        }
        return value;
    }

    /**
     * <h1>arithmeticMultiplication</h1>
     * <p>This method performs calculation and save result when user clicks either multiply, or divide.</p>
     * @param operatorType: operator that clicked before.
     * @param clickedOperator: operator that user clicked.
     */
    public void arithmeticMultiplication(Operator operatorType, Operator clickedOperator) {
        switch (operatorType) {
            case ADDITION, SUBTRACT -> {
                if (numberStored[1] == 0) {
                    operatorAssigned = operatorType;
                    numberStored[1] = Double.parseDouble(mainLabel.getText());
                } else {
                    //numberStored[1] = numberStored[1] / Double.parseDouble(mainLabel.getText());
                    numberStored[1] = basicCalculation(numberStored[1], Double.parseDouble(mainLabel.getText()), clickedOperator);
                    mainLabel.setText(Double.toString(hasNegSign(numberStored[1])));
                }
            }
            case MULTIPLY, DIVIDE -> {
                if (numberStored[1] != 0) {
                    //numberStored[1] = numberStored[1] / Double.parseDouble(mainLabel.getText());
                    numberStored[1] = basicCalculation(numberStored[1], Double.parseDouble(mainLabel.getText()), operatorType);
                    mainLabel.setText(Double.toString(numberStored[1]));
                } else {
                    numberStored[0] = basicCalculation(numberStored[0], Double.parseDouble(mainLabel.getText()), operatorType);
                    mainLabel.setText(Double.toString(numberStored[0]));
                }
            }
            case POW -> {
                double exp = Double.parseDouble(mainLabel.getText());
                double result = Math.pow(numberStored[2], exp);
                numberStored[2] = 0;

                if (numberStored[1] != 0) {
                    numberStored[1] = basicCalculation(numberStored[1], result, operatorBfPow);
                    mainLabel.setText(Double.toString(numberStored[1]));
                } else {
                    numberStored[0] = basicCalculation(numberStored[0], result, operatorBfPow);
                    mainLabel.setText(Double.toString(numberStored[0]));
                }
                operatorBfPow = Operator.NON;
            }
            default -> numberStored[0] = Double.parseDouble(mainLabel.getText());
        }
    }
    
    /**
     * <h1>arithmeticAddition</h1>
     * <p>This method performs calculation and save result when user clicks either addition or subtract button.</p>
     * @param operatorType: Operator that clicked before.
     * @param clickedOperator: Operator that user clicked.
     */
    public void arithmeticAddition(Operator operatorType, Operator clickedOperator) {
        switch (operatorType) {
            case ADDITION, SUBTRACT -> {
                numberStored[0] = basicCalculation(numberStored[0], Double.parseDouble(mainLabel.getText()), operatorType);
                mainLabel.setText(Double.toString(numberStored[0]));
            }
            case MULTIPLY, DIVIDE -> {
                if (numberStored[1] != 0) {
                    double subResult = basicCalculation(numberStored[1], Double.parseDouble(mainLabel.getText()), operatorType);
                    numberStored[0] = basicCalculation(numberStored[0], subResult, operatorAssigned);
                    numberStored[1] = 0;
                    operatorAssigned = Operator.NON;
                    mainLabel.setText(Double.toString(numberStored[0]));
                } else {
                    numberStored[0] = basicCalculation(numberStored[0], Double.parseDouble(mainLabel.getText()), operatorType);
                    mainLabel.setText(Double.toString(numberStored[0]));
                }
            }
            case POW -> {
                double exp = Double.parseDouble(mainLabel.getText());
                double result = Math.pow(numberStored[2], exp);
                numberStored[2] = 0;

                if (numberStored[1] != 0) {
                    // This code below should be finalized.
                    double subResult = basicCalculation(numberStored[1], result, operatorBfPow);
                    numberStored[0] = basicCalculation(numberStored[0], subResult, operatorAssigned);
                    numberStored[1] = 0;
                    operatorAssigned = Operator.NON;
                    mainLabel.setText(Double.toString(numberStored[0]));
                } else {
                    numberStored[0] = basicCalculation(numberStored[0], result, operatorBfPow);
                    mainLabel.setText(Double.toString(numberStored[0]));
                }
                operatorBfPow = Operator.NON;
            }
            default -> numberStored[0] = Double.parseDouble(mainLabel.getText());
        }
    }

}
