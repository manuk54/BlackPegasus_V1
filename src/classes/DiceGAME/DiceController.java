package classes.DiceGAME;

import classes.Chapter2.Ch2p1;
import classes.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DiceController implements Initializable{
    private Model model;
    private Stage stage = null;
    private Scene scene = null;

    private DiceAnimation a = new DiceAnimation();
    private DiceModel m = new DiceModel();

    private int bet = 0;
    //Würfel von dem Spieler
    @FXML
    private Label dice1;
    @FXML
    private Label dice2;
    @FXML
    private Label dice3;
    //Ergebnisfeld von dem Spieler
    @FXML
    private Label sum;
    // Würfel von dem Gastwirt
    @FXML
    private Label wdice1;
    @FXML
    private Label wdice2;
    @FXML
    private Label wdice3;
    //Ergebnisfeld von dem Gastwirt
    @FXML
    private Label wsum;

    //Anweisungsfeld
    @FXML
    private Label info;
    //Buttons
    @FXML
    private Button play1;
    @FXML
    private Button play2 = new Button("Play Round 2");
    @FXML
    private Button play3 = new Button("Play Round 3");

    @FXML
    private VBox vb = new VBox();
    @FXML
    private HBox hb1;
    @FXML
    private HBox hb2;

    //alle Stackpane als Darstellung für Würfel und Ergebnisfelder
    @FXML
    private StackPane sp1;
    @FXML
    private StackPane sp2;
    @FXML
    private StackPane sp3;
    @FXML
    private StackPane sp4;
    @FXML
    private StackPane wsp1;
    @FXML
    private StackPane wsp2;
    @FXML
    private StackPane wsp3;
    @FXML
    private StackPane wsp4;

    //Bild als Hintergrund laden
    final String imageName = "Schiff.png";
    final String imagePath = "images/";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image(imagePath + imageName);
        vb.setBackground(new Background(new BackgroundImage(
            image,
                                           BackgroundRepeat.NO_REPEAT,
                                           BackgroundRepeat.NO_REPEAT,
                                           BackgroundPosition.CENTER,
                new BackgroundSize(vb.getWidth(),
                vb.getHeight(), false, false, true, false)
                        ))
        );
    }

    @FXML
    public void handleQuitButtonAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader ch2p1Loader = new FXMLLoader(getClass().getResource("/fxml/Ch2p1.fxml"));
        Parent ch2p1View = ch2p1Loader.load();
        Scene ch2p1Scene = new Scene(ch2p1View,model.getWindowWidth(),model.getWindowHeight());

        Ch2p1 chap2p1Controller = (Ch2p1) ch2p1Loader.getController();
        chap2p1Controller.setCurrentScene(ch2p1Scene);
        chap2p1Controller.setStage(stage);
        chap2p1Controller.setModel(model);
        chap2p1Controller.handleBackButton();
        stage.setScene(ch2p1Scene);
    }


    //Animationen zu Hbox einbinden
    public void addScene(){

        hb1.getChildren().addAll(a.rotatedDice(sp1,300,100),a.rotatedDice(sp2,300,-50),a.rotatedDice(sp3,-300,200),a.result(sp4,0,0));

        hb2.getChildren().addAll(a.rotatedDice(wsp1,300,50),a.rotatedDice(wsp2,300,-150),a.rotatedDice(wsp3,-300,-200),a.result(wsp4,0,0));
    }
    //Label für Wetten
    @FXML
    private Label betLabel;
    //Button zu nächstem Level
    @FXML
    private Button go;

    //Wetten
    public void addMoney(){
        System.out.println("my: " + model.getMyMoney());
        String result = " won ";
        if (result3==1){
            model.changeMyMoney(bet);
        }
        else{
            model.changeMyMoney(-bet);
            result = " lost ";
        }
        go.setVisible(true);
        betLabel.setText("You"+ result + bet + model.getCurrency());
        balanceLabel.setText("Balance: " + model.getMyMoney() + model.getCurrency());
    }

    @FXML
    private Label balanceLabel;

    public void setModelAndBet(Model mod,int b){
        this.model = mod;
        m.setModel(mod);
        bet = b;
        betLabel.setText("Your bet: " + bet + model.getCurrency());
        balanceLabel.setText("Balance: " + model.getMyMoney() + model.getCurrency());
    }

    //Button Action
    int result1;//speichert Ergebnis von 1. Runde
    int result2;//speichert Ergebnis von 2. Runde
    int result3;//speichert Ergebnis: gewinnt die Runde und das Spiel
    //gemeinsamer Teil bei allen Buttons
    //Spieler: Zufallszahle generieren und mit Label anbinden
    public int random1(){
        int r1=m.getRandom();
        int r2=m.getRandom();
        int r3=m.getRandom();
        dice1.setText(""+r1);
        dice2.setText(""+r2);
        dice3.setText(""+r3);
        int s = m.getSum(r1,r2,r3);
        sum.setText("ME"+"\n"+ +s);// set alignment center in Scene builder
        return s;
    }
    //Gastwirt: Zufallszahle generieren und mit Label anbinden
    public int random2(){ //Wuerfel von Computer
        int wr1=m.getRandom();
        int wr2=m.getRandom();
        int wr3=m.getRandom();
        wdice1.setText(""+wr1);
        wdice2.setText(""+wr2);
        wdice3.setText(""+wr3);
        int ws = m.getSum(wr1,wr2,wr3);
        wsum.setText("OPPONENT"+"\n"+ ws);
        return ws;
    }
    //Button Action für 1. Runde
    @FXML
    private void handlePlay1(ActionEvent event) {
        //Button verstecken
        play1.setVisible(false);
        play2.setVisible(false);
        play3.setVisible(false);
        //Summen berechnen und vergleichen
        int s=random1();
        int ws=random2();
        if (s>ws) {
            result1 = 1;
            info.setText("You won. Next round.");
        }
        else if (s==ws) {
            result1 = 0;
            info.setText("Tie. Next round");
        }
        else {
            result1 = -1;
            info.setText("You lose. Next round");
        }
        play1.setVisible(false);
        play2.setVisible(true);
        addScene();
    }
    //Button Action für 2. Runde
    @FXML
    private void handlePlay2(ActionEvent actionEvent) {
        int s = random1();
        int ws = random2();
        play2.setVisible(false);
        if (s > ws) {
            result2 = 1;
            if (result1 == 1) {
                info.setText("You won the game.");
                result3=1;
                addMoney();
            }
            else if (result1 ==0||result1==-1){
                info.setText("You won. Next round 3 will be decided.");
                play3.setVisible(true);
            }

        } else if (s == ws) {
            result2=0;
            if (result1 == 0) {
                info.setText("Tie. Next round 3 will be decided");
                play3.setVisible(true);
            } else if (result1 == 1) {
                info.setText("Tie. But you won the game because you won the last round.");
                result3=1;
                addMoney();
            } else if (result1 == -1) {
                info.setText("Tie. But you lose the game because you lose the 1. round. No question. It's rule");
                addMoney();
            }
        } else if (s<ws) {
            result2 = -1;
            if (result1 == -1) {
                info.setText("You lose the game.");
                addMoney();
            } else if (result1 ==0||result1==1){
                info.setText("You lose. Next round 3 will be decided.");
                play3.setVisible(true);
            }
        }
        addScene();
    }
    //Button Action für 3. Runde
    @FXML
    private void handlePlay3(ActionEvent actionEvent) {
        int s = random1();
        int ws = random2();
        play3.setVisible(false);
        if (s>ws) {
            info.setText("You won.");
            result3=1;
        }
        else if (s==ws) {
            if (result2 == 1) {
                info.setText("You won.");
                result3=1;
            } else if (result2 == -1) {
                info.setText("You lose.");
            } else if (result2 == 0) {
                info.setText("Tie. Play again");
                play1.setVisible(true);
            }
        } else {
            info.setText("You lose.");
        }
        addScene();
        addMoney();
    }

    public void setStage(Stage st){stage=st;}
    public void setScene(Scene sc){scene=sc;}
}