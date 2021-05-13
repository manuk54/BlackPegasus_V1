package classes;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.ActionListener;
import java.util.Random;

public class RandomPointsController {
    private Scene currentScene;
    private Scene nextScene;
    private double targetLiveTime = 2500;
    private double targetRadius = 25;
    private int score = 0;
    private long lastShotTime;
    private double reloadTime = 1000;
    private boolean targetDown = false;

    @FXML
    private Circle startCircle;
    @FXML
    private Pane pane;
    @FXML
    private Label scoreLabel;

    @FXML
    public void handleMousePressed(MouseEvent mouseEvent){
        if(targetDown){
            targetDown=false;
            Group target = createAnimatedTarget();
            pane.getChildren().add(target);
            lastShotTime = System.currentTimeMillis();
            System.out.println("new target set");
        }
    }

    @FXML
    public void handleStartCircleClicked(MouseEvent actionEvent){
        scoreLabel.setText("Your score: " + score);
        score++;
        lastShotTime = System.currentTimeMillis();
        targetDown = true;
        updateScoreLabel();
        clearPane();
    }


    @FXML
    public void initialize(){
        scoreLabel.setText("Ahoi " + Main.getNickname()+" here is our first game. You need to shoot at least 10 of these dammed Balls. Let`s go, shoot the first one!");
    }

    private void targetClicked(){
        if(isReloaded()){
            lastShotTime = System.currentTimeMillis();
            score++;
            updateScoreLabel();
            targetDown = true;
            clearPane();
            System.out.println("target down");
        }
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

    public void setCurrentScene(Scene scene){
        currentScene = scene;
    }

    public void setNextScene(Scene scene){
        nextScene = scene;
    }

    //opens nextScene
    private void openNextScene(ActionEvent actionEvent){
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(nextScene);
    }
}
