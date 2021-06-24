package classes.Chapter3;

import classes.Model;
import classes.ShipGAME.ShipController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Ch3 {
    private Stage stage  = null;
    private Model model  = null;
    private Scene currentScene = null;

    private String[][] textToShow;
    //model.getTextCh3[textNum][speechNum]
    private int textNum = 0;
    private int speechNum = 0;
    private int keyPressedNum = 0;
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
        introTextLabel.setText(textToShow[textNum][0]);
        model.setRiotWilliam(1);
        enableAllButtons(false);
       // handleBackButton();
//        loadShipGame();
    }

    @FXML
    public void handleChoice1ButtonAction(ActionEvent actionEvent){
        choice1NumPressed++;
        enableAllButtons(false);
        textNum = choice1NumPressed + 1;
        speechNum = 1;
        introTextLabel.setText(textToShow[textNum][0]);
    }

    // show or hide buttons
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
            if (speechNum < textToShow[textNum].length) {
                introTextLabel.setText(textToShow[textNum][speechNum]);
                speechNum++;
            } else if (textNum == 0) {
                enableAllButtons(true);
            } else if (textNum == 1 || textNum == 3) {
                try {
                    loadShipGame();
                }catch (IOException e){e.printStackTrace();}
            } else if(textNum == 2) {
                enableAllButtons(true);
                choice1.setText("Agree to him");
            }
        }
    }

    //creates a txtfield to give a name for the ship
    private void nameTheShip(){
        enableAllButtons(false);
        //Label TextField Label
        Label callShipLabel = new Label("Enter a name for your ship: ");
        TextField enterName = new TextField();
        Label adviceForNameLabel = new Label("");
        lowerHBox.getChildren().addAll(callShipLabel,enterName,adviceForNameLabel);
        enterName.setOnAction(actionEvent -> {
            String name = enterName.getText().toString();
            if(model.stringHasOnlyLetters(name)){
                model.setShipName(name);
                introTextLabel.setText(textToShow[textNum][speechNum]);
                speechNum++;
                enableAllButtons(false);
            }else{
                adviceForNameLabel.setText("!Please use only latin letters!");
                adviceForNameLabel.setTextFill(Color.color(1, 0, 0));
            }
        });
    }

    private void loadShipGame() throws IOException {
        //TODO
        FXMLLoader shipLoader = new FXMLLoader(getClass().getResource("/fxml/Ship.fxml"));
        Parent shipParent = shipLoader.load();
        Scene shipScene = new Scene(shipParent, model.getWindowWidth(),model.getWindowHeight());
        ShipController shipController = (ShipController) shipLoader.getController();
        shipController.setCurrentScene(shipScene);
        shipController.setCurrentStage(stage);
        shipController.setModel(model);
        stage.setScene(shipScene);
    }

    public void setCurrentScene(Scene sc){
        this.currentScene = sc;
        currentScene.setOnKeyPressed(keyEvent -> {
            keyPressedNum++;
            System.out.println("keyPressedNum: "+keyPressedNum);
            if (keyPressedNum == 2) nameTheShip();
            else showText();
        });
    }

    public void setStage(Stage st){ this.stage = st;}
    public void setModel(Model mo){ this.model = mo; textToShow = model.getTextCh3();}
}