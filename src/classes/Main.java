package classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static String nickname;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tales of the Black Pegasus");

        Parent introParent = FXMLLoader.load(getClass().getResource("/fxml/introFXML.fxml"));
        Scene introScene = new Scene(introParent, 800, 400);
        primaryStage.setScene(introScene);


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
