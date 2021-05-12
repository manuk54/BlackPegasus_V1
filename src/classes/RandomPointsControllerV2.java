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
    private boolean targetMissed = false;
    private long targetStartTime = Long.MAX_VALUE;
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
            targetStartTime = System.currentTimeMillis();
            firstClick=false;
        }
        if(targetShot) {
            updateScore();
//            circle.setFill(pane.getScene().getFill());
            circle = createAnimatedTarget();
            pane.getChildren().add(circle);
            targetStartTime = System.currentTimeMillis();
            targetShot = false;
            if(targetLiveTime >= 1000)
                targetLiveTime *=0.95;
            System.out.println(targetLiveTime);
        }
        if(targetMissed){
            circle = createAnimatedTarget();
            pane.getChildren().add(circle);
            targetStartTime = System.currentTimeMillis();
            targetMissed = false;
            if(targetLiveTime < 2500)
                targetLiveTime *=1.05;
            System.out.println("MISSED");
        }
    }

    // clears Pane if Target lives more then targetLiveTime
    // when mouse moved
    // TODO always check this method
    @FXML
    public void handleTargetLiveTime(MouseEvent mouseEvent){
        if(System.currentTimeMillis() - targetStartTime >= targetLiveTime){
            clearPane();
            score--;
            updateScore();
            targetMissed = true;
            if(targetLiveTime < 2500)
                targetLiveTime *=1.05;
            System.out.println(targetLiveTime);
        }
        if(targetMissed){
            Circle circle = createAnimatedTarget();
            pane.getChildren().add(circle);
            targetStartTime = System.currentTimeMillis();
            targetMissed = false;
        }
    }

    private boolean isTargetMissed(){
        boolean targetMissed = false;
        return  targetMissed;
    }


    private void clearPane(){
        Rectangle rec = new Rectangle(0,scoreLabel.getHeight(),pane.getWidth(),pane.getHeight());
        rec.setFill(Color.WHITESMOKE);
        pane.getChildren().add(rec);
        //pane.getChildren().add(scoreLabel);
    }

    private void updateScore(){
        scoreLabel.setText("Your score: " + score);
    }

    public Circle createCircleAtRandomPosition(int radius){
        Circle circle = new Circle(radius);
        circle.setCenterX(Math.random()*pane.getWidth()+radius);
        circle.setCenterY(Math.random()*pane.getHeight()+radius*1.5+scoreLabel.getHeight()); // hBox.getHeight() = 100
//        System.out.println("x: " + circle.getCenterX()+" y: " + circle.getCenterY());
        circle.setFill(Color.RED);
        circle.setOnMouseClicked(mouseEvent1 -> {
//            circle.setFill(pane.getScene().getFill());
            circle.setFill(Color.YELLOW);
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
