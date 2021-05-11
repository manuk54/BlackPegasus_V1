package classes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;

public class IntroController {
   // public String nickname ="";
    @FXML
    Label introTextLabel;
    @FXML
    TextField playersName;
    @FXML
    Label adviceForNickname;
    @FXML
    Button enterButton;

    public void handleButton(ActionEvent actionEvent){
//        if(hasOnlyLetters()) Main.nickname = playersName.getText().toString();
//        else Main.nickname = "Landlubber";
        if (!hasOnlyLetters()){
            adviceForNickname.setText("!Please use only letters!");
        }else{
        Main.nickname = playersName.getText().toString();
        System.out.println("Nick "+","+Main.nickname);}
    }

    public void handleTextFieldEnter(ActionEvent actionEvent){

    }

    private boolean hasOnlyLetters(){
        for(int i = 0; i < playersName.getText().toString().length();i++){
            char ch = playersName.getText().toString().charAt(i);
            if(!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))) return false;
        }
        return true;
    }
}
