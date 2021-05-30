package classes;

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

        //load intro
        FXMLLoader introLoader = new FXMLLoader(getClass().getResource("/fxml/introFXML.fxml"));
        Parent introView = introLoader.load();
        Scene introScene = new Scene(introView,WindowWidth,WindowHeight);

        //load RandomPoints
        FXMLLoader randomPointsLoader  = new FXMLLoader(getClass().getResource("/fxml/RandomPointsFXML.fxml"));
        Parent randomPointsView = randomPointsLoader.load();
        Scene randomPointsScene = new Scene(randomPointsView,WindowWidth,WindowHeight);

        //load Dice
        FXMLLoader diceLoader = new FXMLLoader(getClass().getResource("/fxml/diceFXML.fxml"));
        Parent diceView = diceLoader.load();
        Scene diceScene = new Scene(diceView, WindowWidth, WindowHeight);

        //set up controllers
        IntroController introController = (IntroController)introLoader.getController();
        introController.setStage(primaryStage);
        introController.setNextScene(randomPointsScene);
        introController.setCurrentScene(introScene);
        introController.setModel(model);
        nickname = introController.getNickname();

        RandomPointsController randomPointsController = (RandomPointsController)randomPointsLoader.getController();
        randomPointsController.setStage(primaryStage);
        randomPointsController.setCurrentScene(randomPointsScene);
        randomPointsController.setModel(model);
        randomPointsController.setNextScene(diceScene);

        DiceController diceController = (DiceController) diceLoader.getController();

        primaryStage.setScene(randomPointsScene);
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
