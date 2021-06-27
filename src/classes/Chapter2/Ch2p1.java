package classes.Chapter2;

import classes.Chapter3.Ch3;
import classes.DiceGAME.DiceController;
import classes.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.event.*;
import javafx.stage.Stage;

import java.io.IOException;

// At the tavern
public class Ch2p1 {


    private Stage stage  = null;
    private Model model  = null;
    private Scene currentScene = null;

    private String[][] textToShow;
    //model.getTextCh2p1[textNum][speechNum]
    private int textNum = 0;
    private int speechNum = 0;

    boolean waitForButtonPressed = false;

    @FXML
    private HBox upperHBox;
    @FXML
    private HBox lowerHBox1;
    @FXML
    private HBox lowerHBox2;
    @FXML
    private Label introTextLabel;
    @FXML
    Button choice0;
    @FXML
    Button choice1;
    @FXML
    Button choice2;
    @FXML
    Button choice3;

    //barmaid
    public void handleChoice0ButtonAction(ActionEvent actionEvent){
        waitForButtonPressed = true;
        textNum = 1;
        speechNum = 1;
        introTextLabel.setText(model.getTextCh2p1()[textNum][0]);
        enableAllButtons(false);
        Button drink = new Button("Yes, please (5 " + model.getCurrency() + ")");
        drink.setOnAction(actionEvent1 -> {
            model.changeMyMoney(-5); //paid 5
            waitForButtonPressed = false;
            enableAllButtons(false);
            showText();
        });
        Button back = new Button("No, thank you");
        back.setOnAction(actionEvent1 -> {
            handleBackButton();
            enableAllButtons(true);
        });

        lowerHBox1.getChildren().addAll(drink,back);
    }

    //old man
    public void handleChoice1ButtonAction(ActionEvent actionEvent){
        enableAllButtons(false);
//        waitForButtonPressed = true;
        textNum = 2;
        speechNum = 1;
        introTextLabel.setText(model.getTextCh2p1()[textNum][0]);
        Button leave = new Button("Leave");
        leave.setOnAction(actionEvent1 -> {
            handleBackButton();
        });
    }

    //dice
    public void handleChoice2ButtonAction(ActionEvent actionEvent){
        textNum = 3;
        speechNum = 1;
        introTextLabel.setText(model.getTextCh2p1()[textNum][0]);
        enableAllButtons(false);

        //define 4 buttons with bets
        Button leave = new Button("No, thanks. ");
        leave.setOnAction(actionEvent1 -> {handleBackButton();});
        leave.setOnAction(actionEvent1 -> {
            handleBackButton();
        });
        Button bet25 = new Button("Yes. (Bet 25 Reals)");
        bet25.setOnAction(actionEvent1 -> {
            try {
                loadDice(25);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Button bet50 = new Button("Yes. (Bet 50 Reals)");
        bet50.setOnAction(actionEvent1 -> {
            try {
                loadDice(50);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Button betAll = new Button("Yes. (All in)");
        betAll.setOnAction(actionEvent1 -> {
            try {
                loadDice(model.getMyMoney());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        lowerHBox1.getChildren().addAll(leave,bet25);
        lowerHBox2.getChildren().addAll(bet50,betAll);
    }

    //go sleep
    public void handleChoice3ButtonAction(ActionEvent actionEvent){
        textNum = 4;
        introTextLabel.setText("You decide to return to your ship and have a good sleep before the journey you want to start the next day.");
        enableAllButtons(false);
        Button goBack = new Button("Go back to tavern");
        goBack.setOnAction(actionEvent1 -> {
            handleBackButton();
        });
        Button moveToTheNextChapter = new Button("Next chapter");
        moveToTheNextChapter.setOnAction(actionEvent1 -> {
            try {
                loadCh3();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        lowerHBox1.getChildren().addAll(goBack,moveToTheNextChapter);
    }




    private void loadDice(int bet) throws IOException {
        //load Dice
        FXMLLoader diceLoader = new FXMLLoader(getClass().getResource("/fxml/diceFXML.fxml"));
        Parent diceView = diceLoader.load();
        Scene diceScene = new Scene(diceView, model.getWindowWidth(), model.getWindowHeight());
        DiceController diceController = (DiceController) diceLoader.getController();
        diceController.setModelAndBet(model,bet);
        diceController.setScene(diceScene);
        diceController.setStage(stage);
        stage.setScene(diceScene);
    }

    //used for 4 default choice buttons
    //true -> turn on   false -> turn off
    private void turnAllButtons(boolean bool){
        if(bool){
            choice0.setVisible(true);
            choice1.setVisible(true);
            choice2.setVisible(true);
            choice3.setVisible(true);
        }else{
            choice0.setVisible(false);
            choice1.setVisible(false);
            choice2.setVisible(false);
            choice3.setVisible(false);
        }
    }

    //used for 4 default choice buttons
    //true -> add all buttons
    private void enableAllButtons(boolean bool){
        if(bool){
            lowerHBox1.getChildren().clear();
            lowerHBox2.getChildren().clear(); // no need of false-true, as before
            lowerHBox1.getChildren().add(choice0);
            lowerHBox1.getChildren().add(choice1);
            lowerHBox2.getChildren().add(choice2);
            lowerHBox2.getChildren().add(choice3);
            turnAllButtons(true);
        } else {
            lowerHBox1.getChildren().clear();
            lowerHBox2.getChildren().clear();
        }
    }

    //opens default tavern moment with 4 choices
    public void handleBackButton(){
        introTextLabel.setFont(model.getDefaultFont());
        introTextLabel.setText("You are at the tavern...");
        enableAllButtons(false);
        enableAllButtons(true);
        turnAllButtons(true);
    }

    private void showText(){
        if(speechNum == 0) introTextLabel.setFont(model.getDefaultFont());
        if(speechNum < model.getTextCh2p1()[textNum].length && !waitForButtonPressed) {
            introTextLabel.setText(textToShow[textNum][speechNum]);
            speechNum ++;
        }else if(textNum == 0){
            turnAllButtons(true);
        }else if((textNum == 1 || textNum == 2) && !waitForButtonPressed){
            handleBackButton();
        }
    }

    private void loadCh3() throws IOException{
        FXMLLoader ch3Loader = new FXMLLoader(getClass().getResource("/fxml/Ch3.fxml"));
        Parent ch3Parent = ch3Loader.load();
        Scene ch3Scene = new Scene(ch3Parent,model.getWindowWidth(),model.getWindowHeight());

        Ch3 ch3Controller = (Ch3) ch3Loader.getController();
        ch3Controller.setCurrentScene(ch3Scene);
        ch3Controller.setModel(model);
        ch3Controller.setStage(stage);

        stage.setScene(ch3Scene);
    }

    public void setCurrentScene(Scene sc){
        this.currentScene = sc;
        currentScene.setOnKeyPressed(keyEvent -> {
            showText();
        });
    }

    public void setStage(Stage st){ this.stage = st;}
    public void setModel(Model mo){ this.model = mo; textToShow = model.getTextCh2p1();}
}