package classes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;

public class IntroController {
    private String nickname;
    private Scene nextScene;

    @FXML
    private Label introTextLabel;
    @FXML
    private TextField playersName;
    @FXML
    private Label adviceForNickname;
    @FXML
    private Button enterButton;

    @FXML
    public void handleButton(ActionEvent actionEvent){
        if (!nicknameHasOnlyLetters()){
            adviceForNickname.setText("!Please use only letters!");
        }else if(playersName.getText().toString().length()>0){
        nickname = playersName.getText().toString();
        System.out.println("Nickname: "+nickname);
        openNextScene(actionEvent);
            System.out.println("done");
        }
    }

    public void handleTextFieldEnter(ActionEvent actionEvent){

    }

    private void openNextScene(ActionEvent actionEvent){
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(nextScene);
    }

    private boolean nicknameHasOnlyLetters(){
        for(int i = 0; i < playersName.getText().toString().length();i++){
            char ch = playersName.getText().toString().charAt(i);
            if(!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))) return false;
        }
        return true;
    }
    public void setNextScene(Scene scene){
        nextScene = scene;
    }

    public String getNickname() {
        return nickname;
    }
}
