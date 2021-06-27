package classes.Chapter4;

import classes.Chapter5.Ch5;
import classes.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;

public class Ch4 {
    private Stage stage  = null;
    private Model model  = null;
    private Scene currentScene = null;

    private String[][] textToShow = {};
    private int textNum = 0;
    private int speechNum = 0;
    @FXML
    private HBox upperHBox;
    @FXML
    private HBox lowerHBox;
    @FXML
    private Label textLabel;

    private void showText(){
        if(speechNum == 0) textLabel.setFont(model.getDefaultFont());
        if(speechNum < textToShow[textNum].length){
            textLabel.setText(textToShow[textNum][speechNum]);
            speechNum++;
        } else if(textNum == 0){
            speechNum = 0;
            if(model.getRiotWilliam() == 1) textNum = 1;     //riotCrew TODO
            else textNum = 4;
            textLabel.setText(textToShow[textNum][speechNum]);
            speechNum++;
        } else if(textNum == 1){
            speechNum = 0;
            if(model.getMyMoney() < 150) {
                textNum = 2;
                model.setMyMoney(0);
            }
            else textNum = 3;
            textLabel.setText(textToShow[textNum][speechNum]);
            speechNum++;
        } else if(textNum == 4){
            try {
                loadNextLevel();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void loadNextLevel() throws IOException {
        FXMLLoader ch5Loader = new FXMLLoader(getClass().getResource("/fxml/Ch5.fxml"));
        Parent ch5View = ch5Loader.load();
        Scene ch5Scene = new Scene(ch5View,model.getWindowWidth(),model.getWindowHeight());

        Ch5 chap5Controller = (Ch5) ch5Loader.getController();
        chap5Controller.setCurrentScene(ch5Scene);
        chap5Controller.setStage(stage);
        chap5Controller.setModel(model);
        stage.setScene(ch5Scene);
    }

    public void setTextNum(int num){textNum = num;}

    public void setCurrentScene(Scene sc){
        this.currentScene = sc;
        currentScene.setOnKeyPressed(keyEvent -> {
            showText();
        });
    }

    public void setStage(Stage st){ this.stage = st;}
    public void setModel(Model mo){ this.model = mo; textToShow = model.getTextCh4();}
}