package classes.Chapter3;

import classes.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Ch3 {
    private Stage stage  = null;
    private Model model  = null;
    private Scene currentScene = null;

    private String[][] textToShow;
    //model.getTextCh3[textNum][speechNum]
    private int textNum = 0;
    private int speechNum = 0;
    private int choice1NumPressed = 0;

    boolean waitForButtonPressed = false;

    @FXML
    private HBox upperHBox;
    @FXML
    private HBox lowerHBox;
    @FXML
    private Label introTextLabel;
    @FXML
    Button choice0;
    @FXML
    Button choice1;
    @FXML
    public void handleChoice0ButtonAction(ActionEvent actionEvent){
        //imprison William
        textNum = 1;
        speechNum = 1;
        introTextLabel.setText(model.getTextCh3()[textNum][0]);
        model.setRiotWilliam(1);
        enableAllButtons(false);
       // handleBackButton();
        //TODO move to the next scene
    }

    @FXML
    public void handleChoice1ButtonAction(ActionEvent actionEvent){
        choice1NumPressed++;
        enableAllButtons(false);
        textNum = choice1NumPressed + 1;
        speechNum = 1;
        introTextLabel.setText(model.getTextCh3()[textNum][0]);


    }

    //used for 4 default choice buttons
    //true -> turn on   false -> turn off
    private void turnAllButtons(boolean bool){
        if(bool){
            choice0.setVisible(true);
            choice1.setVisible(true);
        }else{
            choice0.setVisible(false);
            choice1.setVisible(false);
        }
    }

    //used for 4 default choice buttons
    //true -> add all buttons
    private void enableAllButtons(boolean bool){
        if(bool){
            lowerHBox.getChildren().clear(); // no need of false-true, as before
            lowerHBox.getChildren().add(choice0);
            lowerHBox.getChildren().add(choice1);
            turnAllButtons(true);
        } else {
            lowerHBox.getChildren().clear();
        }
    }

    private void showText(){
        if(speechNum == 0) introTextLabel.setFont(model.getDefaultFont());
        if(!waitForButtonPressed) {
            if (speechNum < model.getTextCh3()[textNum].length) {
                introTextLabel.setText(textToShow[textNum][speechNum]);
                speechNum++;
            } else if (textNum == 0) {
                turnAllButtons(true);
            } else if (textNum == 1 ) {
                //handleBackButton();
            } else if(textNum == 2) {
                enableAllButtons(true);
                choice1.setText("Agree to him");
            } else if(textNum == 3){
                //TODO load ship minigame
            }
        }
    }

    public void setCurrentScene(Scene sc){
        this.currentScene = sc;
        currentScene.setOnKeyPressed(keyEvent -> {
            showText();
        });
    }

    public void setStage(Stage st){ this.stage = st;}
    public void setModel(Model mo){ this.model = mo; textToShow = model.getTextCh3();}
}
