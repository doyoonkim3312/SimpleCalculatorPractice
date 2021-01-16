package doyoonkim;

import javafx.scene.control.Label;

/**
 * <h1>ScientificCalculate</h1>
 * <h3>This class handles all calculations that occurs.</h3>
 */
public class ScientificCalculate {
    private Operator operatorType;
    private Operator operatorAssigned;
    private Label mainLabel;
    private double[] numberStored = new double[2];
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
        //System.out.println("CURRENT OPERATOR REGISTERED: " + operatorType.name());
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
        //System.out.println("CURRENT OPERATOR REGISTERED: " + operatorType.name());
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
        //System.out.println("CURRENT OPERATOR REGISTERED: " + operatorType.name());
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
        //System.out.println("CURRENT OPERATOR REGISTERED: " + operatorType.name());
        //System.out.println("NUM STORED 1: " + numberStored[0] + "\nNUM STORED 2: " + numberStored[1]);
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
                subResult = getSubResult(numberStored[1], Double.parseDouble(mainLabel.getText()), operatorType);
                numberStored[0] = getSubResult(numberStored[0], subResult, operatorAssigned);
                numberStored[1] = 0;
                mainLabel.setText(Double.toString(numberStored[0]));
            } else {
                numberStored[0] = getSubResult(numberStored[0], Double.parseDouble(mainLabel.getText()), operatorType);
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

    public String sqRoot(double num) {
        double base = 0;
        while (base < num) {
            base++;
            if ((base * base) == num) {
                return Double.toString(base);
            }
        }

        double termInSequence = ((base * base) + num) / (2 * base);
        for (int i = 0; i < 10; i++) {
            termInSequence = ((termInSequence * termInSequence) + num) / (2 * termInSequence);
        }
        return String.format("%.9f", termInSequence);
    }

    public double squared(double num) {
        return num * num;
    }

    // this method need to be finalized later.
    // public double pow(double num, double targetPow);

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
     * Calculating sub result before calculating actual result.
     * @param num1: Assigned Value. (Value that saved from last calculation.)
     * @param num2: Newly typed value.
     * @param operator: operator for calculation.
     * @return value after calculation.
     */
    public double getSubResult(double num1, double num2, Operator operator) {
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
                    numberStored[1] = getSubResult(numberStored[1], Double.parseDouble(mainLabel.getText()), clickedOperator);
                    mainLabel.setText(Double.toString(hasNegSign(numberStored[1])));
                }
            }
            case MULTIPLY, DIVIDE -> {
                if (numberStored[1] != 0) {
                    //numberStored[1] = numberStored[1] / Double.parseDouble(mainLabel.getText());
                    numberStored[1] = getSubResult(numberStored[1], Double.parseDouble(mainLabel.getText()), clickedOperator);
                    mainLabel.setText(Double.toString(numberStored[1]));
                } else {
                    numberStored[0] = getSubResult(numberStored[0], Double.parseDouble(mainLabel.getText()), operatorType);
                    mainLabel.setText(Double.toString(numberStored[0]));
                }
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
                numberStored[0] = getSubResult(numberStored[0], Double.parseDouble(mainLabel.getText()), operatorType);
                mainLabel.setText(Double.toString(numberStored[0]));
            }
            case MULTIPLY, DIVIDE -> {
                if (numberStored[1] != 0) {
                    double subResult = getSubResult(numberStored[1], Double.parseDouble(mainLabel.getText()), operatorType);
                    numberStored[0] = getSubResult(numberStored[0], subResult, operatorAssigned);
                    numberStored[1] = 0;
                    operatorAssigned = Operator.NON;
                    mainLabel.setText(Double.toString(numberStored[0]));
                } else {
                    numberStored[0] = getSubResult(numberStored[0], Double.parseDouble(mainLabel.getText()), operatorType);
                    mainLabel.setText(Double.toString(numberStored[0]));
                }
            }
            default -> numberStored[0] = Double.parseDouble(mainLabel.getText());
        }
    }

}
