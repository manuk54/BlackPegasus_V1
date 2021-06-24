package classes.DiceGAME;

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

public class DiceAnimation {
    //Animation für Würfel
    public Group rotatedDice(StackPane sp, double x, double y) {
        //Gruppe von Stackpane erstellen
        Group ro = new Group();
        ro.getChildren().add(sp);
        //Wurfel dreht sich
        RotateTransition rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360);
        rotate.setCycleCount(2);//Geschwindigkeit mit translate transition anpassen
        rotate.setDuration(Duration.millis(2000));
        rotate.setAutoReverse(true);
        rotate.setNode(ro); //die Rotation rotate auf ro Objekt anwenden/anbinden
        rotate.play();
        //Wurfel geworfen
        TranslateTransition translate = new TranslateTransition(Duration.millis(2000));
        translate.setByX(x);
        translate.setByY(y);
        translate.setCycleCount(2);// nur hin und zurueckfliegen
        translate.setAutoReverse(true);
        translate.setNode(ro);
        translate.play();
        //Drehen und Fliegen gleichzeitig
        ParallelTransition parallelTransition = new ParallelTransition(ro, translate, rotate);
        parallelTransition.play();

        return ro;
    }
    //Animation für die Ergebnisfelder
    public Group result(StackPane sp, double x, double y){
        Group gr1 = new Group(sp);
        gr1.setTranslateX(x);
        gr1.setTranslateY(y);
        //Schatten
        DropShadow shadow = new DropShadow();
        gr1.setEffect(shadow);
        //Vergrößern
        ScaleTransition scaleAnimation = new ScaleTransition(Duration.millis(1000),gr1);
        scaleAnimation.setCycleCount(Timeline.INDEFINITE);
        double scale = 0.3;
        scaleAnimation.setByX(scale);
        scaleAnimation.setByY(scale);
        scaleAnimation.play();
        return gr1;
    }
}

