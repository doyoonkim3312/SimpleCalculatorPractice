package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {
    private static double numberStored = 0;
    private static String operationType;
    private static boolean operatorRegistered = false;

    // Main number button controller
    private static NumberBtnController MainController = new NumberBtnController();

    @Override
    public void start(Stage primaryStage) throws Exception{

        UiContainer mainGui = new UiContainer();

        Scene scene = new Scene(mainGui.getLayoutContainer());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

}


