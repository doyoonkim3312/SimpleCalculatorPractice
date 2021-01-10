package doyoonkim;

import javafx.scene.control.Label;

public class ScientificCalculate {
    private Operator operatorType;
    //private Operator currentOperator;
    private Operator operatorAssigned;
    private Label mainLabel;
    private double[] numberStored = new double[2];
    private boolean isNumberInLabel = false;
    // private boolean isNumberClicked = false;

    public ScientificCalculate(Label mainLabel) {
        operatorType = Operator.NON;
        //currentOperator = Operator.NON;
        operatorAssigned = Operator.NON;
        this.mainLabel = mainLabel;
    }

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

    public void equal() {
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
        operatorAssigned = Operator.NON;
        operatorType = Operator.NON;
    }

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


    public double hasNegSign(double value) {
        if (value < 0) {
            return value * -1;
        } else {
            return value;
        }
    }

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
