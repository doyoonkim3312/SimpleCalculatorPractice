package doyoonkim;

import javafx.scene.control.Label;

/**
 * <h1>ScientificCalculate</h1>
 * <h3>This class handles all calculations that occurs.</h3>
 */
public class ScientificCalculate {
    private Operator operatorType;
    //private Operator currentOperator;
    private Operator operatorAssigned;
    private Label mainLabel;
    private double[] numberStored = new double[2];
    private boolean isNumberInLabel = false;
    // private boolean isNumberClicked = false;

    /**
     * Default constructor for instantiating.
     * @param mainLabel: Label object that shows calculation result.
     */
    public ScientificCalculate(Label mainLabel) {
        operatorType = Operator.NON;
        //currentOperator = Operator.NON;
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
            switch (operatorType) {
                case ADDITION, SUBTRACT -> {
                    if (numberStored[1] == 0) {
                        operatorAssigned = operatorType;
                        numberStored[1] = Double.parseDouble(mainLabel.getText());
                    } else {
                        numberStored[1] = numberStored[1] / Double.parseDouble(mainLabel.getText());
                        mainLabel.setText(Double.toString(hasNegSign(numberStored[1])));
                    }
                }
                case MULTIPLY, DIVIDE -> {
                    if (numberStored[1] != 0) {
                        numberStored[1] = numberStored[1] / Double.parseDouble(mainLabel.getText());
                        mainLabel.setText(Double.toString(numberStored[1]));
                    } else {
                        numberStored[0] = getSubResult(numberStored[0], Double.parseDouble(mainLabel.getText()), operatorType);
                        mainLabel.setText(Double.toString(numberStored[0]));
                    }
                }
                default -> numberStored[0] = Double.parseDouble(mainLabel.getText());
            }
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
            switch (operatorType) {
                case ADDITION, SUBTRACT -> {
                    if (numberStored[1] == 0) {
                        operatorAssigned = operatorType;
                        numberStored[1] = Double.parseDouble(mainLabel.getText());
                    } else {
                        numberStored[1] = numberStored[1] * Double.parseDouble(mainLabel.getText());
                        mainLabel.setText(Double.toString(hasNegSign(numberStored[1])));
                    }
                }
                case MULTIPLY, DIVIDE -> {
                    if (numberStored[1] != 0) {
                        numberStored[1] = numberStored[1] * Double.parseDouble(mainLabel.getText());
                        mainLabel.setText(Double.toString(numberStored[1]));
                    } else {
                        numberStored[0] = getSubResult(numberStored[0], Double.parseDouble(mainLabel.getText()), operatorType);
                        mainLabel.setText(Double.toString(numberStored[0]));
                    }
                }
                default -> numberStored[0] = Double.parseDouble(mainLabel.getText());
            }
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


}
