package classes;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class RandomPointsControllerV2 {
    private boolean firstClick = true;
    private boolean targetShot = false;
    private static final int targetRadius = 20;
    private double targetLiveTime = 2500; //in ms
    private int score = 0;

    @FXML
    private HBox hBox;
    @FXML
    private Pane pane;
    @FXML
    private Label scoreLabel;

    @FXML
    public void handleMousePressed(MouseEvent mouseEvent){
        System.out.println("Mouse pressed!");
        Circle circle = createAnimatedTarget();
        if(firstClick){
            pane.getChildren().add(circle);
            firstClick=false;
        }
        if(targetShot) {
            scoreLabel.setText("Your score: " + score);
            circle.setFill(pane.getScene().getFill());
            circle = createAnimatedTarget();
            pane.getChildren().add(circle);
            targetShot = false;
        }
    }


    private void clearPane(){
        Rectangle rec = new Rectangle(0,scoreLabel.getHeight(),pane.getWidth(),pane.getHeight());
        rec.setFill(Color.WHITESMOKE);
        pane.getChildren().add(rec);
        //pane.getChildren().add(scoreLabel);
    }

    public Circle createCircleAtRandomPosition(int radius){
        Circle circle = new Circle(radius);
        circle.setCenterX(Math.random()*Main.getWindowWidth()+radius);
        circle.setCenterY(Math.random()*(Main.getWindowHeight()-100)+radius); // hBox.getHeight() = 100
        System.out.println("x: " + circle.getCenterX()+" y: " + circle.getCenterY());
        circle.setFill(Color.RED);
        circle.setOnMouseClicked(mouseEvent1 -> {
//            circle.setFill(pane.getScene().getFill());
            circle.setFill(Color.WHITESMOKE);
            score++;
            targetShot = true;
            clearPane();
        });
        return circle;
    }

    public Circle createAnimatedTarget(){
        Circle animatedTarget = createCircleAtRandomPosition(targetRadius);

        ScaleTransition scaleAnimation = new ScaleTransition(Duration.millis(targetLiveTime),animatedTarget);
        scaleAnimation.setCycleCount(1);
        double scale = 0.50;
        scaleAnimation.setByX(scale);
        scaleAnimation.setByY(scale);
        scaleAnimation.play();

        return animatedTarget;
        //return new Group(animatedTarget);
    }
}
