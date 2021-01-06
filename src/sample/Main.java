package sample;

import Can.NumberBtnController;
import javafx.application.Application;
import javafx.scene.Scene;
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


