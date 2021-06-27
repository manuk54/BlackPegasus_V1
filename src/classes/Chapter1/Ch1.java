package classes.Chapter1;

import classes.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;


public class Ch1 {

    private Stage stage  = null;
    private Model model  = null;
    private Scene currentScene = null;
    private Scene nextScene = null;

    private String[] textToShow;
    private int speechNum = 0;

    @FXML
    private HBox upperHBox;
    @FXML
    private VBox lowerHBox;
    @FXML
    private Label introTextLabel;

    //Method to show text when keyEvent
    private void showText(){
        if(speechNum == 0){
            introTextLabel.setFont(Font.font("Bell MT Bold",50));
        }
        if(speechNum == 1)
            introTextLabel.setFont(model.getDefaultFont());// set default font
        if(speechNum < textToShow.length) {
            introTextLabel.setText(textToShow[speechNum]); // show speeches in order
            speechNum ++;
        }else{
            try {
                openNextScene(); // load RandomPoints
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void openNextScene() throws IOException {
        FXMLLoader randomPointsLoader  = new FXMLLoader(getClass().getResource("/fxml/RandomPointsFXML.fxml"));
        Parent randomPointsView = randomPointsLoader.load();
        Scene randomPointsScene = new Scene(randomPointsView,model.getWindowWidth(),model.getWindowHeight());
        RandomPointsController randomPointsController = (RandomPointsController)randomPointsLoader.getController();
        randomPointsController.setStage(stage);
        randomPointsController.setCurrentScene(randomPointsScene);
        randomPointsController.setModel(model);
        stage.setScene(randomPointsScene);
    }

    public void setCurrentScene(Scene sc){
        this.currentScene = sc;
        currentScene.setOnKeyPressed(keyEvent -> {
            showText();
        });
    }

    public void setStage(Stage st){ this.stage = st;}
    public void setModel(Model mo){ this.model = mo; textToShow = model.getTextCh1();}
    public void setNextScene(Scene sc){ this.nextScene = sc;}
}