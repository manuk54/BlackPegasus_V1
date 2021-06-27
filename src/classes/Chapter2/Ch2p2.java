package classes.Chapter2;

import classes.Chapter3.Ch3;
import classes.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Ch2p2 {


    private Stage stage  = null;
    private Model model  = null;
    private Scene currentScene = null;
    private int speechNum = 0;

    @FXML
    private Label introTextLabel;

    private void showText(){
        introTextLabel.setFont(Font.font("Bell MT",22));
        introTextLabel.setText("You decide to return to your ship and have a good sleep before the journey you want to start the next day.");
        if(speechNum > 0){
            try{
                loadNextScene();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        speechNum++;
    }

    public void setCurrentScene(Scene sc){
        this.currentScene = sc;
        currentScene.setOnKeyPressed(keyEvent -> {
            showText();
        });
    }

    private void loadNextScene() throws IOException {
        FXMLLoader ch3Loader = new FXMLLoader(getClass().getResource("/fxml/Ch3.fxml"));
        Parent ch3Parent = ch3Loader.load();
        Scene ch3Scene = new Scene(ch3Parent,model.getWindowWidth(),model.getWindowHeight());

        Ch3 ch3Controller = (Ch3) ch3Loader.getController();
        ch3Controller.setCurrentScene(ch3Scene);
        ch3Controller.setModel(model);
        ch3Controller.setStage(stage);
        stage.setScene(ch3Scene);
    }

    public void setStage(Stage st){ this.stage = st;}
    public void setModel(Model mo){ this.model = mo;}
}