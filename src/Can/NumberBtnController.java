package Can;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

import javax.swing.*;


public class NumberBtnController implements EventHandler<ActionEvent> {
    private String number;
    private Label display;
    private boolean operaterRegistered;
    private boolean initialClick = true;


    public NumberBtnController() {
        this.number = null;
        this.display = null;
        this.display = null;
    }

    public EventHandler<ActionEvent> setNumber(String number) {
        this.number = number;
        System.out.println("Set Number method: " + number);
        return this::handle;
    }

    public void setOperaterRegistered(boolean operaterRegistered) {
        this.operaterRegistered = operaterRegistered;
    }

    public void setDisplay(Label display) {
        this.display = display;
    }

    public void setInitialClick() {
        this.initialClick = true;
    }

    public String getNumber() {
        return this.number;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("INPUT STATUS: " + Boolean.toString(operaterRegistered));
        System.out.println(number);
        if (operaterRegistered) {
            if (initialClick) {
                display.setText(this.number);
                initialClick = false;
            } else {
                display.setText(display.getText() + this.number);
            }
            /*
            if (display.getText().equals("")) {
                System.out.println(1);
                display.setText(display.getText() + this.number);
            } else {
                System.out.println(2);
                display.setText("");
                //display.setText(display.getText() + this.number);
            }
             */
        } else {
            if (initialClick) {
                display.setText(this.number);
                initialClick = false;
            } else {
                display.setText(display.getText() + this.number);
            }
            /*
            if (display.getText().equals("")) {
                System.out.println(3);
                display.setText(display.getText() + this.number);
            } else {
                System.out.println(4);
                display.setText(display.getText() + this.number);
            }
             */
        }
    }
}
