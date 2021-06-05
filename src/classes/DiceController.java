package classes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;


import java.net.URL;
import java.util.ResourceBundle;

public class DiceController implements Initializable{//muss mit implements... wenn css und public void ini..

    private DiceAnimation a = new DiceAnimation();
    @FXML
    private Label dice1;
    @FXML
    private Label dice2;
    @FXML
    private Label dice3;

    @FXML
    private Label sum;
    // Wirt
    @FXML
    private Label wdice1;
    @FXML
    private Label wdice2;
    @FXML
    private Label wdice3;
    @FXML
    private Label wsum;

    //Info
    @FXML
    private Label info;

    @FXML
    private Button play1;
    @FXML
    private Button play2 = new Button("Play Round 2");

    @FXML
    private Button play3 = new Button("Play Round 3");
   /* @FXML
    private Button wellcome = new Button("Wellcome to Game of Dice");  */
    @FXML
    private VBox vb = new VBox();
    private DiceModel m = new DiceModel();//damit BlackModel hier benutzen koennen
    //muss hier ein Objekt der Modellklasse als Instanzvariable erzeugt werden

    //Bild als Hintergrund
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
                        )));}

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
    @FXML
    private HBox hb1;
    @FXML
    private HBox hb2;

    //Animation
    public void addScene(){

        hb1.getChildren().addAll(a.rotatedDice(sp1,300,100),a.rotatedDice(sp2,300,-50),a.rotatedDice(sp3,-300,200),a.createDice(sp4,0,0));//unterschiedlich y damit die chaos in allen Richtungen

        hb2.getChildren().addAll(a.rotatedDice(wsp1,300,50),a.rotatedDice(wsp2,300,-150),a.rotatedDice(wsp3,-300,-200),a.createDice(wsp4,0,0));
    }

    @FXML
    private Label bet;
    //Button Action
    int result1;//speichert result von play 1, hat 3 Werte
    int result2;//speichert result von play 2 fuer action play 3
    int result3;//speichert result: whenn won the round and the game
    int mo = m.getMoney();
    public void addMoney(){
        if (result3==1){
            mo=mo*2;
        }
        else{
            mo=0;
        }
        bet.setText("You get " + mo + " Reals");
    }
    //gemeinsamer Teil bei allen Buttons
    public int random1(){//Wuerfel von Spieler
        int r1=m.getRandom();
        int r2=m.getRandom();
        int r3=m.getRandom();
        dice1.setText(""+r1);
        dice2.setText(""+r2);
        dice3.setText(""+r3);
        int s = m.getSum(r1,r2,r3);
        sum.setText("Your score"+"\n"+ +s);// set alignment center in Scene builder
        return s;
    }
    public int random2(){ //Wuerfel von Computer
        int wr1=m.getRandom();
        int wr2=m.getRandom();
        int wr3=m.getRandom();
        wdice1.setText(""+wr1);
        wdice2.setText(""+wr2);
        wdice3.setText(""+wr3);
        int ws = m.getSum(wr1,wr2,wr3);
        wsum.setText("My score"+"\n"+ ws);
        return ws;
    }

    @FXML
    private void handlePlay1(ActionEvent event) {
        play1.setVisible(false);  //play2 und 3 muessen unsichtbar am Anfang, wie?
        play2.setVisible(false);
        play3.setVisible(false);

        // mit setOnAction hier braucht 2 Click, braucht keine setOnAction
        // play1.setOnAction((event1 -> {
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
        play1.setVisible(false); //play1 invisible after click
        play2.setVisible(true);
        addScene();
    }
    @FXML
    private void handlePlay2(ActionEvent actionEvent) {
        int s = random1();
        int ws = random2();
        play2.setVisible(false);
        if (s > ws) {//gewinnt 2.Round
            result2 = 1;
            if (result1 == 1) {
                info.setText("You won the game.");//play3 bleibt unsichtbar
                result3=1;
                addMoney();
                // v.play3.setDisable(true);
            }
            else if (result1 ==0||result1==-1){
                info.setText("You won. Next round 3 will be decided.");
                play3.setVisible(true);
            }

        } else if (s == ws) { //tie 2.Round
            result2=0;
            if (result1 == 0) {// result von 1.Round
                info.setText("Tie. Next round 3 will be decided");
                play3.setVisible(true);
            } else if (result1 == 1) {
                info.setText("Tie. But you won the game because you won the last round.");
                result3=1;
                addMoney();
                // v.play3.setDisable(true);
            } else if (result1 == -1) {
                info.setText("Tie. But you lose the game because you lose the 1. round. No question. It's rule");
                //v.play3.setDisable(true);
                addMoney();
            }
        } else if (s<ws) { //verliert 2. ROund
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
    @FXML
    private void handlePlay3(ActionEvent actionEvent) {
        //aehnlich wie play1, gibt Zusammenhang mit play2
        //play3.setDisable(true);//nach Click wird grau(disable)
        int s = random1();
        int ws = random2();
        play3.setVisible(false);
        if (s>ws) { //gewinn 3.Round
            info.setText("You won.");
            result3=1;
        }
        else if (s==ws) {//tie 3. round
            if (result2 == 1) {
                info.setText("You won.");
                result3=1;
            } else if (result2 == -1) {
                info.setText("You lose.");
            } else if (result2 == 0) {
                info.setText("Tie. Play again");// muss noch ein Button oder nutzt play1 visible
                play1.setVisible(true);
            }
        } else {//verliert 3.round
            info.setText("You lose.");
        }
        addScene();
        addMoney();// kann fuer alle Faelle gelten
    }
}



