package classes;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.awt.event.MouseEvent;

public class DiceAnimation {// kann nicht FXML in 2 Controller und nur 1 File FXML
    // nicht noetig wenn schon in scene builder gemacht ist

    public Group rotatedDice(StackPane sp, double x, double y) {//x, y fuer coordinate
        Group ro = new Group();
        // Rectangle body = new Rectangle(0,0,50,50);
        // body.setFill(Color.BROWN);

        // ro.getChildren().add(body);//kann nur einzeln Wuerfel drin
        ro.getChildren().add(sp);
        //ro.setTranslateX(-400);//bei Anbindung mit VBox in diesem Fall ist der Ursprung ganz unten -400
        // vorsich mit Alignment bei HBox, schwierig zu koordinieren wenn unterschiedlich ist
        //ro.setTranslateY(30);//als Sprung -10
        RotateTransition rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS); // Pivot (Trong tam) kann nicht gewaehlt werden
        rotate.setByAngle(360);//recursive???
        rotate.setCycleCount(2);//Geschwindigkeit mit translate transition anpassen
        rotate.setDuration(Duration.millis(2000));
        rotate.setAutoReverse(true);
        rotate.setNode(ro); //die Rotation rotate auf krone Objekt anwenden/anbinden
        rotate.play();

        TranslateTransition translate = new TranslateTransition(Duration.millis(2000));
        //setBy: wo Transtion stops
        translate.setByX(x);//shifting X coordinate of the centre of the.. by value 800
        translate.setByY(y);//shift diagonal 50
        // translate.setByZ(50);
        translate.setCycleCount(2);// nur hin und rueckfliegen
        translate.setAutoReverse(true);
        translate.setNode(ro);
        translate.play();

        ParallelTransition parallelTransition = new ParallelTransition(ro, translate, rotate);
        parallelTransition.play();

        return ro;
    }
    public Group createDice(StackPane sp, double x, double y){
        //muss Group sein, nur Rectangle geht nicht mit Animation
        //muss ganze StackPane in Group sein, Nope von StackPane in Group zu addieren --> Animation funktioniert nicht
        //Rectangle body = new Rectangle(0,0,50,20);
        Group gr1 = new Group(sp);

        //gr.getChildren().addAll(sp1,sp2,sp3,sp4,wsp1,wsp2,wsp3,wsp4);//so werden Wuerfeln aufeinander liegen
        //gr1.getChildren().addAll(sp);

        gr1.setTranslateX(x);//move the point(0,0) to the point (x,y)
        gr1.setTranslateY(y);//Ursprung von HBox sind unterschiedlich wegen Alignment

        DropShadow shadow = new DropShadow();
        gr1.setEffect(shadow);

        ScaleTransition scaleAnimation = new ScaleTransition(Duration.millis(1000),gr1);
        scaleAnimation.setCycleCount(Timeline.INDEFINITE);
        double scale = 0.3;
        scaleAnimation.setByX(scale);
        scaleAnimation.setByY(scale);
        scaleAnimation.play();
        return gr1;
    }
}

