package classes.FishGAME;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FishController {

    // button to start the Game
    @FXML
    private Button startGame =  new Button();


    FishGame screen1 = new FishGame();
    Stage primaryStage;


    //calling the Game Method . so that when pressed on the button "Start" it plays the Game
    public void StartGame(ActionEvent event) throws Exception {
        startGame.setOnMouseClicked(value -> {
            try {
                screen1.StartGame(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}