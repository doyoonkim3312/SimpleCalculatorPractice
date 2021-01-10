package doyoonkim;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class UiContainer {
    private Label mainLabel;
    private Label operatorStatus;
    private HBox topArea;
    private GridPane numberPane;
    private HBox bottomArea;
    private VBox layoutContainer;

    private boolean operatorBtnStatus = false;
    private boolean initialClicked = true;
    private boolean decimlaExists = false;
    private boolean isNumberInLabel = false;
    private Button clear;

    private static ScientificCalculate mainCalculator;


    public UiContainer() {
        // Label
        mainLabel = new Label("0");
        mainLabel.setPrefHeight(70.0);
        mainLabel.setFont(new Font(45.0));
        mainLabel.setAlignment(Pos.BASELINE_CENTER);
        mainLabel.setUnderline(true);

        operatorStatus = new Label("");
        operatorStatus.setPrefHeight(11.0);
        operatorStatus.setFont(new Font(10.0));
        operatorStatus.setAlignment(Pos.TOP_LEFT);
        operatorStatus.setTextAlignment(TextAlignment.LEFT);

        mainCalculator = new ScientificCalculate(mainLabel);

        // Buttons
        Button sign =  buttonFactory("-/+");
        sign.addEventHandler(ActionEvent.ACTION, (e) -> {
            if (!mainLabel.getText().equals("0")) {
                try {
                    String[] numArray = mainLabel.getText().split("");
                    if (numArray[0].equals("-")) {
                        mainLabel.setText("");
                        for (int index = 1; index < numArray.length; index++) {
                            mainLabel.setText(mainLabel.getText() + numArray[index]);
                        }
                    } else {
                        mainLabel.setText("-" + mainLabel.getText());
                    }
                } catch (IndexOutOfBoundsException iobe) {
                    System.out.println(iobe.toString());
                }
            }
        });
        /*
        sign.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String[] numArray = mainLabel.getText().split("");
                    if (numArray[0].equals("-")) {
                        mainLabel.setText("");
                        for (int i = 1; i < numArray.length; i++) {
                            mainLabel.setText(mainLabel.getText() + numArray[i]);
                        }
                    } else {
                        String sign = "-";
                        mainLabel.setText(sign + mainLabel.getText());
                    }
                } catch (IndexOutOfBoundsException iob) {
                    System.out.println(iob.toString());
                }
            }
        });
         */

        Button percentage = buttonFactory("%");
        percentage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                double subResult = Double.parseDouble(mainLabel.getText()) / 100;
                mainLabel.setText(Double.toString(subResult));
            }
        });

        Button divide = buttonFactory("/");
        divide.addEventHandler(ActionEvent.ACTION, (e) -> {
            operatorStatus.setText("DIVIDE");
            mainCalculator.divide(initialClicked);
            initialClicked = true;
        });

        Button multiply = buttonFactory("*");
        multiply.addEventHandler(ActionEvent.ACTION, (e) -> {
            operatorStatus.setText("MULTIPLY");
            mainCalculator.multiply(initialClicked);
            initialClicked = true;
        });

        Button subtract = buttonFactory("-");
        subtract.addEventHandler(ActionEvent.ACTION, (e) -> {
            operatorStatus.setText("SUBTRACT");
            mainCalculator.subtract(initialClicked);
            initialClicked = true;
        });

        Button addition = buttonFactory("+");
        addition.addEventHandler(ActionEvent.ACTION, (e) -> {
            operatorStatus.setText("ADD");
            mainCalculator.addition(initialClicked);
            initialClicked = true;
        });

        Button equals = buttonFactory("=");
        equals.addEventHandler(ActionEvent.ACTION, (e) -> {
            operatorStatus.setText("");
            mainCalculator.equal();
            initialClicked = true;
        });



        Button decimal = buttonFactory(".");
        decimal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!decimlaExists) {
                    mainLabel.setText(mainLabel.getText() + ".");
                }
            }
        });

        Button number0 = buttonFactory("0");
        number0.setPrefSize(150,70);
        number0.setOnAction(new NumBtnControl("0"));

        Button number1 = buttonFactory("1");
        number1.setOnAction(new NumBtnControl("1"));

        Button number2 = buttonFactory("2");
        number2.setOnAction(new NumBtnControl("2"));

        Button number3 = buttonFactory("3");
        number3.setOnAction(new NumBtnControl("3"));

        Button number4 = buttonFactory("4");
        number4.setOnAction(new NumBtnControl("4"));

        Button number5 = buttonFactory("5");
        number5.setOnAction(new NumBtnControl("5"));

        Button number6 = buttonFactory("6");
        number6.setOnAction(new NumBtnControl("6"));

        Button number7 = buttonFactory("7");
        number7.setOnAction(new NumBtnControl("7"));

        Button number8 = buttonFactory("8");
        number8.setOnAction(new NumBtnControl("8"));

        Button number9 = buttonFactory("9");
        number9.setOnAction(new NumBtnControl("9"));


        clear = buttonFactory("AC");
        clear.addEventHandler(ActionEvent.ACTION, (e) -> {
            if (isNumberInLabel) {
                clear.setText("AC");
                isNumberInLabel = mainCalculator.clear(isNumberInLabel);
            } else {
                operatorStatus.setText("");
                isNumberInLabel = mainCalculator.clear(isNumberInLabel);
            }
            initialClicked = true;
        });

        topArea = new HBox(10.0, clear, sign, percentage, divide);
        topArea.setAlignment(Pos.CENTER);

        numberPane = new GridPane();

        numberPane.add(number7, 0,0);
        numberPane.add(number8, 1,0);
        numberPane.add(number9, 2,0);
        numberPane.add(multiply, 3,0);

        numberPane.add(number4, 0,1);
        numberPane.add(number5, 1,1);
        numberPane.add(number6, 2,1);
        numberPane.add(subtract,3,1);

        numberPane.add(number1, 0,2);
        numberPane.add(number2, 1, 2);
        numberPane.add(number3, 2, 2);
        numberPane.add(addition, 3, 2);

        numberPane.setHgap(10.0);
        numberPane.setVgap(10.0);
        numberPane.setAlignment(Pos.CENTER);

        bottomArea = new HBox(10.0, number0, decimal, equals);
        bottomArea.setAlignment(Pos.BOTTOM_CENTER);

        layoutContainer = new VBox(10.0,operatorStatus, mainLabel, topArea, numberPane, bottomArea);
        layoutContainer.setAlignment(Pos.CENTER);
        layoutContainer.setPadding(new Insets(5.0));

    }


    public VBox getLayoutContainer() {
        return layoutContainer;
    }


    private static Button buttonFactory(String tag) {
        Button btn = new Button(tag);
        btn.setPrefSize(70.0, 70.0);
        btn.setFont(new Font(20.0));
        btn.setAlignment(Pos.CENTER);
        btn.setTextAlignment(TextAlignment.CENTER);

        return btn;
    }

    class NumBtnControl implements EventHandler {
        private String number;

        public NumBtnControl(String number) {
            this.number = number;
        }

        @Override
        public void handle(Event event) {
            if (!isNumberInLabel) {
                clear.setText("C");
                isNumberInLabel = true;
            }

            //System.out.println("Button " + this.number);
            if (initialClicked) {
                mainLabel.setText(this.number);
                initialClicked = false;
            } else {
                mainLabel.setText(mainLabel.getText() + this.number);
            }
        }
    }

}
