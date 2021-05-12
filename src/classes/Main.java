package classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static final int WindowWidth = 1000;
    private static final int WindowHeight = 540;


    public static String nickname;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tales of the Black Pegasus");

        Parent introParent = FXMLLoader.load(getClass().getResource("/fxml/introFXML.fxml"));
        Scene introScene = new Scene(introParent, WindowWidth, WindowHeight);
//        primaryStage.setScene(introScene);

        Parent randomPointsParent = FXMLLoader.load(getClass().getResource("/fxml/RandomPointsFXML.fxml"));
        Scene randomPointsScene = new Scene(randomPointsParent,WindowWidth,WindowHeight);
//        RandomPoints randomPointsObject = new RandomPoints(randomPointsParent.getco);
        primaryStage.setScene(randomPointsScene);

        primaryStage.show();
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
