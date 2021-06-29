package classes.Chapter2;

import classes.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Ch2 {

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


    private void showText(){
        if(speechNum == 0)
            introTextLabel.setFont(model.getDefaultFont()); // set default font
        if(speechNum < textToShow.length) {
            introTextLabel.setText(textToShow[speechNum]);
            speechNum ++;
        }else{
            addChoice();
            speechNum++;
        }
    }

    // activate buttons
    private void addChoice(){
        choice0.setText(model.getChoiceCh2()[0]);
        choice1.setText(model.getChoiceCh2()[1]);

        choice0.setText("Go to tavern");
        choice1.setText("Return to the ship and sleep");

        choice0.autosize();
        choice1.autosize();

        choice0.setVisible(true);
        choice1.setVisible(true);
        choice0.setOnMouseClicked(mouseEvent -> {
            introTextLabel.setText("You go to tavern");
            choice0.setVisible(false);
            choice1.setVisible(false);
            try {
                System.out.println("loadP1");
                loadP1Scene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        choice1.setOnMouseClicked(mouseEvent -> {
            choice0.setVisible(false);
            choice1.setVisible(false);
//            introTextLabel.setText("You decide to return to your ship and have a good sleep before the journey you want to start the next day.");
            try {
                loadP2Scene();
            }catch (IOException e){
                e.printStackTrace();
            }
        });
    }

    public void setCurrentScene(Scene sc){
        this.currentScene = sc;
        currentScene.setOnKeyPressed(keyEvent -> {
            showText();
        });
    }

    private void loadP1Scene() throws IOException {
        FXMLLoader ch2p1Loader = new FXMLLoader(getClass().getResource("/fxml/Ch2p1.fxml"));
        Parent ch2p1View = ch2p1Loader.load();
        Scene ch2p1Scene = new Scene(ch2p1View,model.getWindowWidth(),model.getWindowHeight());

        Ch2p1 chap2p1Controller = (Ch2p1) ch2p1Loader.getController();
        chap2p1Controller.setCurrentScene(ch2p1Scene);
        chap2p1Controller.setStage(stage);
        chap2p1Controller.setModel(model);
        System.out.println("p1 loaded");
        stage.setScene(ch2p1Scene);
    }

    private void loadP2Scene() throws IOException{
        FXMLLoader ch2p2Loader = new FXMLLoader(getClass().getResource("/fxml/Ch2p2.fxml"));
        Parent ch2p2View = ch2p2Loader.load();
        Scene ch2p2Scene = new Scene(ch2p2View,model.getWindowWidth(),model.getWindowHeight());

        Ch2p2 chap2p2Controller = (Ch2p2) ch2p2Loader.getController();
        chap2p2Controller.setCurrentScene(ch2p2Scene);
        chap2p2Controller.setStage(stage);
        chap2p2Controller.setModel(model);

        stage.setScene(ch2p2Scene);
    }

    public void setStage(Stage st){ this.stage = st;}
    public void setModel(Model mo){ this.model = mo; textToShow = model.getTextCh2();}
}