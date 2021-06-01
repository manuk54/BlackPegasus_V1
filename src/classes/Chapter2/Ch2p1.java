package classes.Chapter2;

import classes.DiceController;
import classes.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import java.io.IOException;

// In the tavern
public class Ch2p1 {


    private Stage stage  = null;
    private Model model  = null;
    private Scene currentScene = null;
    private Scene nextScene = null;

    private String[] textToShow;
    private int speechNum = 0;

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
    Button choice2;
    @FXML
    Button choice3;

    private void showText(){
        if(speechNum == 0) introTextLabel.setFont(model.getDefaultFont());
        if(speechNum < textToShow.length) {
            introTextLabel.setText(textToShow[speechNum]);
            speechNum ++;
        }else{
            addChoice();
        }
//        else{
//            //stage.setScene(nextScene);
//        }
    }


    //activate all buttons
    private void addChoice(){
        turnAllButtons(true);

        choice0.setOnMouseClicked(mouseEvent -> {
            introTextLabel.setText("You go to the barmaid...");

            turnAllButtons(false);
        });
        choice1.setOnMouseClicked(mouseEvent -> {
            introTextLabel.setText("Old man talks...");
            turnAllButtons(false);
        });
        choice2.setOnMouseClicked(mouseEvent -> {
           // openChoice2();
            try {
                loadDice();
            } catch (IOException e) {
                e.printStackTrace();
            }
            turnAllButtons(false);
        });
        choice3.setOnMouseClicked(mouseEvent -> {
            introTextLabel.setText("You go sleep");
            turnAllButtons(false);
        });
    }

    private void openChoice2(){
        introTextLabel.setText("- Hey, cap. Want to roll dice with us? We throw the three dice up to three times. The one who won the round before a tie wins the tie and, as a result, wins the game.");

        choice0.setText("No, thanks. ");
        choice1.setText("Yes. (Bet 25 Reals)");
        choice2.setText("Yes. (Bet 50 Reals)");
        choice3.setText("Yes. (Bet all)");

        choice0.setOnMouseClicked(mouseEvent -> {
            introTextLabel.setText("Need more time?");
            addChoice();
        });

        choice1.setOnMouseClicked(mouseEvent -> {
            try {
                loadDice();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        turnAllButtons(true);

    }

    private void loadDice() throws IOException {
        //load Dice
        FXMLLoader diceLoader = new FXMLLoader(getClass().getResource("/fxml/diceFXML.fxml"));
        Parent diceView = diceLoader.load();
        Scene diceScene = new Scene(diceView, model.getWindowWidth(), model.getWindowHeight());
        DiceController diceController = (DiceController) diceLoader.getController();
        stage.setScene(diceScene);
    }

    public void setCurrentScene(Scene sc){
        this.currentScene = sc;
        currentScene.setOnKeyPressed(keyEvent -> {
            showText();
        });
    }

    //true -> turn on   false -> turn off
    private void turnAllButtons(boolean bool){
        if(bool){
            choice0.setDisable(false);
            choice0.setVisible(true);
            choice1.setVisible(true);
            choice2.setVisible(true);
            choice3.setVisible(true);
        }else{
            choice0.setDisable(true);
            choice0.setVisible(false);
            choice1.setVisible(false);
            choice2.setVisible(false);
            choice3.setVisible(false);
        }
    }

    public void setStage(Stage st){ this.stage = st;}
    public void setModel(Model mo){ this.model = mo; textToShow = model.getTextCh2();}
    public void setNextScene(Scene sc){ this.nextScene = sc;}
}
