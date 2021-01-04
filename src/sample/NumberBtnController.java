package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;


public class NumberBtnController implements EventHandler<ActionEvent> {
    private String number;
    private Label display;
    private boolean operaterRegistered;


    public NumberBtnController() {
        this.number = null;
        this.display = null;
        this.display = null;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setOperaterRegistered(boolean operaterRegistered) {
        this.operaterRegistered = operaterRegistered;
    }

    public void setDisplay(Label display) {
        this.display = display;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("INPUT STATUS: " + Boolean.toString(operaterRegistered));
        if (operaterRegistered) {
            if (display.getText().equals("")) {
                System.out.println(1);
                display.setText(display.getText() + this.number);
            } else {
                System.out.println(2);
                display.setText("");
                //display.setText(display.getText() + this.number);
            }
        } else {
            if (display.getText().equals("")) {
                System.out.println(3);
                display.setText(display.getText() + this.number);
            } else {
                System.out.println(4);
                display.setText(display.getText() + this.number);
            }
        }
    }
}
