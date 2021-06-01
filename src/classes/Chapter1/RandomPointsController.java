package classes.Chapter1;

import classes.Chapter2.Ch2;
import classes.Model;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class RandomPointsController {
    private Model model;
    private Stage stage = null;
    private Scene currentScene = null;
    private Scene nextScene = null;

    private double targetLiveTime = 2500;
    private double targetRadius = 25;   //starting size of the target
    private double reloadTime = 1000; // ms time for weapon to reload
    private int score = 0;
    private int intermediateScore = 12; // points to pass half of the level (side level)
    private int scoreToWin = 20; //How many points to pass the lvl
    private long lastShotTime;  //time when last shot was shoot


    @FXML
    private Circle startCircle;
    @FXML
    private Pane pane;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label motivation;

    @FXML
    public void initialize(){

    }

    //this method actually starts a mini game
    @FXML
    public void handleStartCircleClicked(MouseEvent actionEvent){
        score++;
        lastShotTime = System.currentTimeMillis();
        updateScoreLabel();
        clearPane();
            Group target = createAnimatedTarget();
            pane.getChildren().add(target);
            lastShotTime = System.currentTimeMillis();
            System.out.println("new target set");

    }

    //called when player clicked on the target
    private void targetClicked(){
        if(isReloaded()){
            if(score < scoreToWin-1){
                lastShotTime = System.currentTimeMillis();
                score++;
                if(score >= 10) motivation.setText("C`mon half of them is down!");
                if(score >= 15) motivation.setText("Last push!");
                if(score == 19) motivation.setText("Last one!");
                updateScoreLabel();
                clearPane();
                System.out.println("target down");
                Group target = createAnimatedTarget();
                pane.getChildren().add(target);
                lastShotTime = System.currentTimeMillis();
                System.out.println("new target set");
            }
            else{ // last shot was made
                score++;
                updateScoreLabel();
                clearPane();
                motivation.setText("Good job!");
                Label finish = new Label("Congratulations! U`ve killed "+ scoreToWin +" bastards! \n Press any button to continue");
                finish.setFont(Font.font("Bell MT",30));
                finish.setTranslateX(model.getWindowWidth()/2 - 5*(finish.getText().length())); // positioning label in the middle
                finish.setTranslateY(pane.getHeight()/2-finish.getHeight()/2);
                pane.getChildren().add(finish);
                goToNextLvl();
            }
        }
    }

    private void goToNextLvl() {
        currentScene.setOnKeyPressed(keyEvent -> {
            try {
                openNextScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private boolean isReloaded(){
        if(lastShotTime < 0) return true;
        return System.currentTimeMillis() - lastShotTime >= reloadTime;
    }

    public Circle createCircleAtRandomPosition(double radius){
        Circle circle = new Circle(radius);
        circle.setCenterX(Math.random()*(pane.getWidth()-radius*2)+radius*2);
        circle.setCenterY(Math.random()*(pane.getHeight()-radius*2)+radius*2);
        circle.setFill(Color.RED);
        return circle;
    }

    public Group createAnimatedTarget(){
        Circle animatedTarget = createCircleAtRandomPosition(targetRadius);
        Group res = new Group();
        res.getChildren().add(animatedTarget);

        ScaleTransition scaleAnimation = new ScaleTransition(Duration.millis(targetLiveTime),animatedTarget);
        scaleAnimation.setCycleCount(1);
        double scale = 0.50;
        scaleAnimation.setByX(scale);
        scaleAnimation.setByY(scale);
        scaleAnimation.play();
        System.out.println("new target created");
        res.setOnMouseClicked(mouseEvent -> {
            targetClicked();
        });
        return res;
    }

    private void updateScoreLabel(){
        scoreLabel.setText("Your score: " + score);
    }

    private void clearPane(){
        Rectangle rec = new Rectangle(0,0,pane.getWidth(),pane.getHeight());
        rec.setFill(Color.WHITESMOKE);
        pane.getChildren().add(rec);
        System.out.println("pane cleared");
    }

    public void setStage(Stage st) { this.stage = st;}

    public void setCurrentScene(Scene scene){
        currentScene = scene;
    }

    public void setNextScene(Scene scene){
        nextScene = scene;
    }

    //opens nextScene
    private void openNextScene() throws IOException {

        FXMLLoader ch2Loader = new FXMLLoader(getClass().getResource("/fxml/Ch2.fxml"));
        Parent ch2View = ch2Loader.load();
        Scene ch2Scene = new Scene(ch2View,model.getWindowWidth(),model.getWindowHeight());

        Ch2 chap2Controller = (Ch2) ch2Loader.getController();
        chap2Controller.setCurrentScene(ch2Scene);
        chap2Controller.setStage(stage);
        chap2Controller.setModel(model);
        stage.setScene(ch2Scene);
    }
    public void setModel(Model model){
        this.model = model;
    }
}


//Here old and unused code
/*

private boolean targetDown = false;
    private boolean firstClick = false;

if(firstClick){
        startCircle.setTranslateX(Math.random()*(300-100)+50);
        startCircle.setTranslateY(Math.random()*(300-100)+50);
        scoreLabel.setText("Ahoi " + model.getNickname() + "! Let`s get the job done! U need to shoot at list 10 goddamn bubbles. Fire in the hole!");
        firstClick = false;
        }
        else{


 */