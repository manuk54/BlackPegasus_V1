package classes;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
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
    private int scoreToWin = 5; //How many points to pass the lvl
    private long lastShotTime;  //time when last shot was shoot
    private boolean targetDown = false;
    private boolean firstClick = true;

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
//        scoreLabel.setText("Hey " + model.getNickname());
    }

    @FXML
    public void handleStartCircleClicked(MouseEvent actionEvent){
        if(firstClick){
            startCircle.setTranslateX(Math.random()*(300-100)+50);
            startCircle.setTranslateY(Math.random()*(300-100)+50);
            scoreLabel.setText("Ahoi " + model.getNickname() + "! Let`s get the job done! U need to shoot at list 10 goddamn bubbles. Fire in the hole!");
            firstClick = false;
        }
        else{
        score++;
        lastShotTime = System.currentTimeMillis();
        targetDown = true;
        updateScoreLabel();
        clearPane();
            Group target = createAnimatedTarget();
            pane.getChildren().add(target);
            lastShotTime = System.currentTimeMillis();
            System.out.println("new target set");
        }
    }

    private void targetClicked(){
        if(isReloaded()){
            if(score < scoreToWin-1){
                lastShotTime = System.currentTimeMillis();
                score++;
                if(score >= 10) motivation.setText("C`mon half of them is down!");
                if(score >= 15) motivation.setText("Last push!");
                if(score == 19) motivation.setText("Last one!");
                updateScoreLabel();
                targetDown = true;
                clearPane();
                System.out.println("target down");
                targetDown=false;
                Group target = createAnimatedTarget();
                pane.getChildren().add(target);
                lastShotTime = System.currentTimeMillis();
                System.out.println("new target set");
            }
            else{
                score++;
                updateScoreLabel();
                clearPane();
                motivation.setText("Good job!");
                Label finish = new Label("Congratulations! U`ve killed 20 bastards! \n Press any button to continue");
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
            openNextScene(keyEvent);
        });
    }

    private boolean isReloaded(){
        if(lastShotTime < 0) return true;
        return System.currentTimeMillis() - lastShotTime >= reloadTime;
    }

    public Circle createCircleAtRandomPosition(double radius){
        Circle circle = new Circle(radius);
        Random rdm = new Random();
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
    private void openNextScene(KeyEvent keyEvent){
//        Stage stage = (Stage) ((Node)keyEvent.getSource()).getScene().getWindow();
//        stage.setScene(nextScene);
        stage.setScene(nextScene);
    }
    public void setModel(Model model){
        this.model = model;
    }
}
