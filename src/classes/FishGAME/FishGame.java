package classes.FishGAME;

import classes.Chapter4.Ch4;
import classes.Model;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;


public class FishGame extends Application {
    // to get the total Fish and click count
    private int ClickCount = 0;
    private int totalFishCount = 0;

    Stage primaryStage = new Stage();


    //the Game method
    public void StartGame(Stage window) {
// All the differents element/ images used in my Game
        AnchorPane anchorPane = new AnchorPane();
        Label label = new Label("You stand with your fishing Rod over the Shore.During about a minuit Time you have to catch some fishes for you and your Team.If your are ready to fish");
        Image image = new Image(("images/sea.jpg"));
        ImageView imageView = new ImageView(image);
        ImageView fish = new ImageView("images/fish.png");
        ImageView bluefish = new ImageView("images/bluefish.png");
        ImageView orangefish = new ImageView("images/orangefish.png");
        ImageView yellowfish = new ImageView("images/yellowfish.png");
        ImageView blue2fish = new ImageView("images/blue2.png");
        ImageView orange2fish = new ImageView("images/orange2.png");

//defining the parameter and Transition of each Fish

        fish.setFitWidth(124);
        fish.setFitHeight(72);
        fish.setLayoutX(34);
        fish.setLayoutY(98);


        TranslateTransition translate = new TranslateTransition();
        translate.setNode(fish);
        translate.setDuration(Duration.seconds(1.5)); //how fast the node(fish) is suppose to move
        translate.setCycleCount(TranslateTransition.INDEFINITE); //the number of time the fish is going to do the translate transition
        translate.setByX(500);
        translate.setAutoReverse(true);
        translate.play();


        bluefish.setFitWidth(124);
        bluefish.setFitHeight(47);
        bluefish.setLayoutX(1);
        bluefish.setLayoutY(1);

        TranslateTransition front = new TranslateTransition();
        front.setNode(bluefish);
        front.setDuration(Duration.seconds(1));
        front.setCycleCount(TranslateTransition.INDEFINITE);
        front.setByX(600);
        front.setAutoReverse(true);
        front.play();


        orangefish.setFitWidth(140);
        orangefish.setFitHeight(47);
        orangefish.setLayoutX(34);
        orangefish.setLayoutY(183);

        TranslateTransition back = new TranslateTransition();
        back.setNode(orangefish);
        back.setDuration(Duration.seconds(1.5));
        back.setCycleCount(TranslateTransition.INDEFINITE);
        back.setByX(400);
        back.setAutoReverse(true);
        back.play();


        yellowfish.setFitWidth(124);
        yellowfish.setFitHeight(47);
        yellowfish.setLayoutX(14);
        yellowfish.setLayoutY(183);

        TranslateTransition middle = new TranslateTransition();
        middle.setNode(yellowfish);
        middle.setDuration(Duration.seconds(1));
        middle.setCycleCount(TranslateTransition.INDEFINITE);
        middle.setByX(400);
        middle.setAutoReverse(true);
        middle.play();

        blue2fish.setFitWidth(124);
        blue2fish.setFitHeight(47);
        blue2fish.setLayoutX(149);
        blue2fish.setLayoutY(26);

        TranslateTransition up = new TranslateTransition();
        up.setNode(blue2fish);
        up.setDuration(Duration.seconds(1));
        up.setCycleCount(TranslateTransition.INDEFINITE);
        up.setByX(400);
        up.setAutoReverse(true);
        up.play();

        orange2fish.setFitWidth(140);
        orange2fish.setFitHeight(47);
        orange2fish.setLayoutX(36);
        orange2fish.setLayoutY(99);


        TranslateTransition down = new TranslateTransition();
        down.setNode(orange2fish);
        down.setDuration(Duration.seconds(1.5));
        down.setCycleCount(TranslateTransition.INDEFINITE);
        down.setByY(-200);
        down.setByX(400);
        down.setAutoReverse(true);
        down.play();

        //To catch a fish, you click on it with your Mouse. some fishes need to be click 4/5 or just 1 time befor its disapear from the scene
//so this is how different fishes are going to react when clicked with a Mouse

        fish.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                totalFishCount++;
                ClickCount ++;
                System.out.println("you catched :" + totalFishCount + "fish");
                if (ClickCount == 5) {
                    fish.imageProperty().set(null);
                }

            }
        });


        bluefish.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                totalFishCount++;
                ClickCount ++;
                if(ClickCount == 4 ){
                    bluefish.imageProperty().set(null);}
                System.out.println("you catched :" + totalFishCount + "fish");

            }
        });

        orangefish.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                totalFishCount++;
                ClickCount ++;
                System.out.println("you catched :" + totalFishCount + "fish");

                if(ClickCount == 5){
                    orangefish.imageProperty().set(null);}


            }
        });

        yellowfish.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                totalFishCount++;
                ClickCount ++;
                System.out.println("you catched :" + totalFishCount + "fish");

                if(ClickCount == 2){
                    yellowfish.imageProperty().set(null);}



            }
        });

        blue2fish.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                totalFishCount++;
                ClickCount ++;
                System.out.println("you catched :" + totalFishCount + "fish");
                if(ClickCount == 4){
                    blue2fish.imageProperty().set(null);}


            }
        });

        orange2fish.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                totalFishCount++;
                ClickCount ++;
                System.out.println("you catched :" + totalFishCount + "fish");

                if(ClickCount ==3){
                    orange2fish.imageProperty().set(null);}


            }
        });

        // here i am setting the time line need to catch your maximum fishes. depending on the number of fish you catched, a message is going to appear.
//after the message appeared, the Window is going to completely close meaning that the game is Over
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(60), event -> {

            if (totalFishCount == 0) {
                System.out.println("you catched no fish, learn how to fish ");
            } else if (totalFishCount < 5) {
                System.out.println(" the fishing is bad. you go back with only one fish");
            } else if (totalFishCount >= 5) {
                System.out.println("CONGRATULATION, you Won and go back with the fish");
            }
            //closing the scene
            ((Stage) anchorPane.getScene().getWindow()).close();
            ((Stage) primaryStage.getScene().getWindow()).close();
        }));
        timeline.play();



// setting my Game Scene

        Group root = new Group();
        root.getChildren().addAll(imageView, fish, bluefish, orangefish, yellowfish, blue2fish, orange2fish, anchorPane);
        imageView.setFitWidth(492);
        imageView.setFitHeight(265);
        Scene scene2 = new Scene(root, 492, 265);
        primaryStage.setScene(scene2);
        primaryStage.setResizable(false);
        primaryStage.setTitle("    FISHING   GAME     ");
        primaryStage.show();


    }

// in my start method, i set my Game window in which and explanation text is set and a startGame button (Start)

    @Override
    public void start(Stage window) throws Exception {

        Parent root1 = FXMLLoader.load(getClass().getResource("/fxml/Fish.fxml"));
        Scene scene1 = new Scene(root1);
        window.setScene(scene1);
        window.setTitle("                FISHING   GAME     ");
        window.show();
    }
    public static void main(String... args)
    {launch(args);}
}