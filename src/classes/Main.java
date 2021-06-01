package classes;

import classes.Chapter1.*;
import classes.Chapter2.*;
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

        //load intro
        FXMLLoader introLoader = new FXMLLoader(getClass().getResource("/fxml/introFXML.fxml"));
        Parent introView = introLoader.load();
        Scene introScene = new Scene(introView,WindowWidth,WindowHeight);

        //load RandomPoints


        //load Chapter 2




        //set up controllers
        IntroController introController = (IntroController)introLoader.getController();
        introController.setStage(primaryStage);
//        introController.setNextScene(randomPointsScene);
        introController.setCurrentScene(introScene);
        introController.setModel(model);
        nickname = introController.getNickname();

        Ch1 chap1Controller = (Ch1) ch1Loader.getController();
        chap1Controller.setCurrentScene(ch1Scene);
        chap1Controller.setStage(primaryStage);
        chap1Controller.setModel(model);
//        chap1Controller.setNextScene(randomPointsScene);


//        chap2Controller.setNextScene(randomPointsScene);


        primaryStage.setScene(ch1Scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
