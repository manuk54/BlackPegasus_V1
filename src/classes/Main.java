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
    public static String nickname;
    private Parent root;
    private Scene scene;
    private Model model;

    @Override
    public void start(Stage primaryStage) throws Exception {
        model = new Model();
        primaryStage.setTitle("Tales of the Black Pegasus");

        //load intro
        FXMLLoader introLoader = new FXMLLoader(getClass().getResource("/fxml/introFXML.fxml"));
        Parent introView = introLoader.load();
        Scene introScene = new Scene(introView,WindowWidth,WindowHeight);

        //load 3 2 1 level starter

        //load RandomPoints
        FXMLLoader randomPointsLoader  = new FXMLLoader(getClass().getResource("/fxml/RandomPointsFXML.fxml"));
        Parent randomPointsView = randomPointsLoader.load();
        Scene randomPointsScene = new Scene(randomPointsView,WindowWidth,WindowHeight);

        //set up controllers
        IntroController introController = (IntroController)introLoader.getController();
        introController.setNextScene(randomPointsScene);
        introController.setCurrentScene(introScene);
        introController.setModel(model);
        nickname = introController.getNickname();

        RandomPointsController randomPointsController = (RandomPointsController)randomPointsLoader.getController();
        randomPointsController.setCurrentScene(primaryStage.getScene());
        randomPointsController.setModel(model);

        primaryStage.setScene(introScene);
        primaryStage.show();

    }

    public static void setNickname(String nick){nickname = nick;
        System.out.println(nickname);
    }

    public static String getNickname() { System.out.println(nickname);return nickname; }

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
