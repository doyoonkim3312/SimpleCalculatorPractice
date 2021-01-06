package sample;

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
import javafx.scene.text.TextAlignment;

public class UiContainer {
    private Label mainLabel;
    private HBox topArea;
    private GridPane numberPane;
    private HBox bottomArea;
    private VBox layoutContainer;

    private static double numberStored = 0;
    private static boolean operationRegistered = false;
    private static Operator operatorType;
    private static boolean initialClicked = true;

    public UiContainer() {
        // Label
        mainLabel = new Label();
        mainLabel.setAlignment(Pos.BASELINE_CENTER);
        mainLabel.setUnderline(true);

        // Buttons
        Button sign =  buttonFactory("-/+");
        Button percentage = buttonFactory("%");
        Button divide = buttonFactory("/");
        divide.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Current OP Register status: " + operationRegistered);
                operatorType = Operator.DIVIDE;
                if(operationRegistered) {
                    System.out.println("line 1");
                    double subResult = numberStored / Double.parseDouble(mainLabel.getText());
                    System.out.println(subResult);
                    mainLabel.setText(Double.toString(subResult));
                    numberStored = subResult;

                    operationRegistered = false;
                } else {
                    System.out.println("line 2");
                    numberStored = Double.parseDouble(mainLabel.getText());

                    operationRegistered = true;
                }
                initialClicked = true;
            }
        }));
        Button multiply = buttonFactory("*");
        Button subtract = buttonFactory("-");
        Button addition = buttonFactory("+");
        Button equals = buttonFactory("=");
        equals.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                switch (operatorType.name()) {
                    case "DIVIDE":
                        double subResult = numberStored / Double.parseDouble(mainLabel.getText());
                        mainLabel.setText(Double.toString(subResult));
                        numberStored = subResult;
                        initialClicked = true;


                }
            }
        });

        Button decimal = buttonFactory(".");

        Button number0 = buttonFactory("0");
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


        Button clear = buttonFactory("AC");
        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainLabel.setText("0");
                operatorType = null;
                operationRegistered = false;
                //mainController.setInitialClick();
            }
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

        layoutContainer = new VBox(10.0, mainLabel, topArea, numberPane, bottomArea);
        layoutContainer.setAlignment(Pos.CENTER);
        layoutContainer.setPadding(new Insets(5.0));

    }

    public Label getMainLabel() {
        return mainLabel;
    }

    public VBox getLayoutContainer() {
        return layoutContainer;
    }

    private static Button buttonFactory(String tag) {
        Button btn = new Button(tag);
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
            System.out.println("INPUT STATUS: " + operationRegistered);
            System.out.println("Button " + this.number);

            if (operationRegistered) {
                if (initialClicked) {
                    mainLabel.setText(this.number);
                    initialClicked = false;
                } else {
                    mainLabel.setText(mainLabel.getText() + this.number);
                }
            } else {
                if (initialClicked) {
                    mainLabel.setText(this.number);
                    initialClicked = false;
                } else {
                    mainLabel.setText(mainLabel.getText() + this.number);
                }
            }
        }
    }



}
