package classes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;

public class IntroController {
    private Model model;
    private Scene currentScene;
    private String nickname;
    private Scene nextScene;
    private int speechNum = 0;
    private boolean sceneIsFinished = false;
    private String[] textToShow = {"Hey landlubber! Welcome onboard Ar-gh-gh-gh",
                                    "How do we call u?"};

    private Label writeYourNickname;
    private TextField playersName;
    private Label adviceForNickname;
    private Button enterButton;

    @FXML
    private HBox upperHBox;
    @FXML
    private HBox lowerHBox;
    @FXML
    private Label introTextLabel;

    @FXML
    public void initialize(){
//        introTextLabel.setPrefWidth(Main.getWindowWidth()*0.75);
        lowerHBox.setAlignment(Pos.CENTER);
    }

    public void setCurrentScene(Scene scene){
        currentScene = scene;
        currentScene.setOnKeyPressed(e-> {
            if(speechNum < textToShow.length)
                showNextText();
            if(sceneIsFinished){
//                openNextScene(e);
            }
        });
    }

    private void showNextText(){
        introTextLabel.setText(textToShow[speechNum]);
        if(speechNum < textToShow.length-1)
            speechNum++;
        else openNicknameField();
    }

    private void openNicknameField(){
        playersName = new TextField();
        writeYourNickname = new Label("Write your name: ");
        adviceForNickname = new Label();

        lowerHBox.getChildren().add(playersName);
        lowerHBox.getChildren().add(adviceForNickname);
        playersName.setOnAction(actionEvent -> {
            nickname = playersName.getText().toString();
             model.setNickname(nickname);
            if(nicknameHasOnlyLetters()){

                showGreetings();
                model.setIntroFinished(true);
                openNextScene(actionEvent);
            }else{
                adviceForNickname.setText("!Please use letters only!");
            }
        });
    }

    private void showGreetings(){
        introTextLabel.setText("Ahoi " + nickname+ " let`s get the job done!");
        sceneIsFinished = true;
//        lowerHBox.getChildren().add(new Rectangle(0,0,lowerHBox.getWidth(),lowerHBox.getHeight()));
    }

    public void setNextScene(Scene scene){
        nextScene = scene;
    }

    private void openNextScene(ActionEvent keyEvent){
        Stage stage = (Stage) ((Node)keyEvent.getSource()).getScene().getWindow();
        stage.setScene(nextScene);
    }

    public String getNickname() {
        return nickname;
    }

    private boolean nicknameHasOnlyLetters(){
        for(int i = 0; i < playersName.getText().toString().length();i++){
            char ch = playersName.getText().toString().charAt(i);
            if(!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))) return false;
        }
        return true;
    }
    public void setModel(Model model){
        this.model = model;
    }
}
