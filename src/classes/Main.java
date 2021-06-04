package classes;

import classes.Chapter1.*;
import classes.Chapter2.*;
import classes.Chapter3.Ch3;
import classes.Chapter5.Ch5;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static int WindowWidth;
    private static int WindowHeight;

    public static boolean changeScene = false;
    public static String nickname;
    private Parent root;
    private Scene scene;
    private Model model;

    @Override
    public void start(Stage primaryStage) throws Exception {
        model = new Model();
        primaryStage.setTitle("Tales of the Black Pegasus");
        WindowWidth = model.getWindowWidth();
        WindowHeight = model.getWindowHeight();

        //load Chapter 1
        FXMLLoader ch1Loader = new FXMLLoader(getClass().getResource("/fxml/Ch1.fxml"));
        Parent ch1View = ch1Loader.load();
        Scene ch1Scene = new Scene(ch1View,WindowWidth,WindowHeight);
    //Part commented below is used for testing
        /* FXMLLoader ch3Loader = new FXMLLoader(getClass().getResource("/fxml/Ch3.fxml"));
        Parent ch3Parent = ch3Loader.load();
        Scene ch3Scene = new Scene(ch3Parent,model.getWindowWidth(),model.getWindowHeight());

        Ch3 ch3Controller = (Ch3) ch3Loader.getController();
        ch3Controller.setCurrentScene(ch3Scene);
        ch3Controller.setModel(model);
        ch3Controller.setStage(primaryStage);

        FXMLLoader ch2p1Loader = new FXMLLoader(getClass().getResource("/fxml/Ch2p1.fxml"));
        Parent ch2p1View = ch2p1Loader.load();
        Scene ch2p1Scene = new Scene(ch2p1View,model.getWindowWidth(),model.getWindowHeight());

        Ch2p1 chap2p1Controller = (Ch2p1) ch2p1Loader.getController();
        chap2p1Controller.setCurrentScene(ch2p1Scene);
        chap2p1Controller.setStage(primaryStage);
        chap2p1Controller.setModel(model);

        FXMLLoader ch5Loader = new FXMLLoader(getClass().getResource("/fxml/Ch5.fxml"));
        Parent ch5View = ch5Loader.load();
        Scene ch5Scene = new Scene(ch5View,WindowWidth,WindowHeight);

        Ch5 chap5Controller = (Ch5) ch5Loader.getController();
        chap5Controller.setCurrentScene(ch5Scene);
        chap5Controller.setStage(primaryStage);
        chap5Controller.setModel(model);*/

        Ch1 chap1Controller = (Ch1) ch1Loader.getController();
        chap1Controller.setCurrentScene(ch1Scene);
        chap1Controller.setStage(primaryStage);
        chap1Controller.setModel(model);

        primaryStage.setScene(ch1Scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
