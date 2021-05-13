package classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static final int WindowWidth = 1000;
    private static final int WindowHeight = 540;

    public static boolean changeScene = false;
    private String nickname;
    private Parent root;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tales of the Black Pegasus");

        //load intro
        FXMLLoader introLoader = new FXMLLoader(getClass().getResource("/fxml/introFXML.fxml"));
        Parent introView = introLoader.load();
        Scene introScene = new Scene(introView,WindowWidth,WindowHeight);

        //load RandomPoints
        FXMLLoader randomPointsLoader  = new FXMLLoader(getClass().getResource("/fxml/RandomPointsFXML.fxml"));
        Parent randomPointsView = randomPointsLoader.load();
        Scene randomPointsScene = new Scene(randomPointsView,WindowWidth,WindowHeight);

        //set up controllers
        IntroController introController = (IntroController)introLoader.getController();
        introController.setNextScene(randomPointsScene);
        nickname = introController.getNickname();

        primaryStage.setScene(introScene);
        primaryStage.show();

    }

    public void switchScene(String fxml){

    }

    public static int getWindowWidth() {
        return WindowWidth;
    }

    public static int getWindowHeight() {
        return WindowHeight;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
