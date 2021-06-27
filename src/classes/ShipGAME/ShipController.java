
package classes.ShipGAME;

import classes.Chapter4.Ch4;
import classes.Chapter5.Ch5;
import classes.Model;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ShipController {
    private Stage currentStage = null;
    private Scene currentScene = null;
    private Model model = null;
    //private Model model = null;

    public void setCurrentStage(Stage st) {
        this.currentStage = st;
    }

    public void setCurrentScene(Scene sc) {
        this.currentScene = sc;
    }

    public void setModel(Model mo) { this.model = mo; }

    /*public void setModel(Model mo) {
        this.model = mo;
        textToShow = model.getTextCh4();
    }*/

    //Two buttons for changing the direction of the ship
    @FXML
    private Button Left = new Button();
    @FXML
    private Button Right = new Button();

    //The anchor pane that is containing the elements of the game: circles and marks of the wind's velocity, the central line
    @FXML
    AnchorPane Game = new AnchorPane();

    //Nine circles for each wind velocity given
    @FXML
    private Polygon windStrength0 = new Polygon();
    @FXML
    private Polygon windStrength_1 = new Polygon();
    @FXML
    private Polygon windStrength_2 = new Polygon();
    @FXML
    private Polygon windStrength_3 = new Polygon();
    @FXML
    private Polygon windStrength_4 = new Polygon();
    @FXML
    private Polygon windStrength1 = new Polygon();
    @FXML
    private Polygon windStrength2 = new Polygon();
    @FXML
    private Polygon windStrength3 = new Polygon();
    @FXML
    private Polygon windStrength4 = new Polygon();

    //Labels and button for starting the game
    @FXML
    public Label Starter = new Label();
    @FXML
    public Label Explanation = new Label();
    @FXML
    private Button StartGame = new Button();

    //Label to show damage taken
    @FXML
    private Label Damage = new Label();

    private ShipModel values = new ShipModel();
    public boolean end;

    @FXML //Initial view of the field, setting all wind strength circles besides 0 to invisible
    public void initialize(){
        Explanation.setText("Your ship is going through a storm. You have to direct it against the wind in order to not get into the eye of the tornado. Press the corresponding buttons before the wind is bigger than 2 in each direction in order to avoid taking damage. Good luck, captain!");
        windStrength_1.setVisible(false);
        windStrength_2.setVisible(false);
        windStrength_3.setVisible(false);
        windStrength_4.setVisible(false);
        windStrength1.setVisible(false);
        windStrength2.setVisible(false);
        windStrength3.setVisible(false);
        windStrength4.setVisible(false);
        Game.setVisible(false);
        Damage.setText("Damage taken: " + values.damage);
    }

    @FXML //Turning the ship to the left
    public void turnLeft(ActionEvent event) {
        Damage.setText("Damage taken: " + values.damage);
        values.turnLeft();
    }

    @FXML //Turning the ship to the right
    public void turnRight(ActionEvent event){
        Damage.setText("Damage taken: " + values.damage);
        values.turnRight();
    }

    @FXML //Start the game after pressing the "Start" button
    public void startGame(ActionEvent event) {
        Game.setVisible(true);
        Starter.setVisible(false);
        StartGame.setVisible(false);
        Explanation.setVisible(false);
        timer.schedule(task, 0, 1000); //Starting timer to do task every second
    }

    Timer timer = new Timer();

    TimerTask task = new TimerTask() {
        int i = 0;
        @Override
        public void run() {
            if (i <=60) {
                System.out.println("It works " + i);
                values.wind();
                /*
                Depending on the changes in ShipModel, the switch-case statement changes the visible circle.
                According to it, the player will know what direction to take in order to avoid taking damage.
                 */
                switch (values.windVelocity) {
                    case -4 : {
                        windStrength_4.setVisible(true);
                        windStrength_3.setVisible(false);
                        break;
                    }
                    case -3 : {
                        windStrength_3.setVisible(true);
                        windStrength_2.setVisible(false);
                        windStrength_4.setVisible(false);
                        break;
                    }
                    case -2 : {
                        windStrength_2.setVisible(true);
                        windStrength_1.setVisible(false);
                        windStrength_3.setVisible(false);
                        break;
                    }
                    case -1 : {
                        windStrength_1.setVisible(true);
                        windStrength0.setVisible(false);
                        windStrength_2.setVisible(false);
                        break;
                    }
                    case 0 : {
                        windStrength0.setVisible(true);
                        windStrength_1.setVisible(false);
                        windStrength1.setVisible(false);
                        break;
                    }
                    case 1 : {
                        windStrength1.setVisible(true);
                        windStrength0.setVisible(false);
                        windStrength2.setVisible(false);
                        break;
                    }
                    case 2 : {
                        windStrength2.setVisible(true);
                        windStrength1.setVisible(false);
                        windStrength3.setVisible(false);
                        break;
                    }
                    case 3 : {
                        windStrength3.setVisible(true);
                        windStrength2.setVisible(false);
                        windStrength4.setVisible(false);
                        break;
                    }
                    case 4 : {
                        windStrength4.setVisible(true);
                        windStrength3.setVisible(false);
                        break;
                    }
                }
            } else {
                try {
                    loadNextChapter();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            values.checkDamage();   //Recalculating damage after each repetition of the task (each second)
            System.out.println(values.damage);
            i++;
        }
    };

    private void loadNextChapter() throws IOException {
        FXMLLoader ch4Loader = new FXMLLoader(getClass().getResource("/fxml/Ch4.fxml"));
        Parent ch4Parent = ch4Loader.load();
        Scene ch4Scene = new Scene(ch4Parent,model.getWindowWidth(),model.getWindowHeight());

        Ch4 ch4Controller = (Ch4) ch4Loader.getController();
        ch4Controller.setCurrentScene(ch4Scene);
        ch4Controller.setModel(model);
        ch4Controller.setStage(currentStage);
    }
}