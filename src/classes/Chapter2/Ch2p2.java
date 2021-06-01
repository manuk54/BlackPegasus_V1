package classes.Chapter2;

import classes.Model;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

// In the tavern
public class Ch2p2 {


    private Stage stage  = null;
    private Model model  = null;
    private Scene currentScene = null;
//    private Scene nextScene = null;
//
//    private String[] textToShow;
    private int speechNum = 0;
//
//    @FXML
//    private HBox upperHBox;
//    @FXML
//    private HBox lowerHBox;
    @FXML
    private Label introTextLabel;

    private void showText(){
        introTextLabel.setFont(Font.font("Bell MT",22));
        introTextLabel.setText("You decide to return to your ship and have a good sleep before the journey you want to start the next day.");
        if(speechNum > 0){
            loadNextScene();
        }
        speechNum++;
    }

    public void setCurrentScene(Scene sc){
        this.currentScene = sc;
        currentScene.setOnKeyPressed(keyEvent -> {
            showText();
        });
    }

    private void loadNextScene(){
        System.out.println("Next Scene is loading....");
    }

    public void setStage(Stage st){ this.stage = st;}
    public void setModel(Model mo){ this.model = mo;}
}
