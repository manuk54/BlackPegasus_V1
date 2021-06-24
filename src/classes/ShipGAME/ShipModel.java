package classes.ShipGAME;

public class ShipModel {
    public int sailsDirection = 0;  //the direction of the sails
    public int windVelocity = 0;    //the velocity of the wind, used in the controller class
    public int change;
    public int damage = 0;  //the damage taken by the ship

    //changing direction to the left, against the direction of the wind
    public int turnLeft() {
        sailsDirection = -1;
        return sailsDirection;
    }

    //changing direction to the right, against the direction of the wind
    public int turnRight() {
        sailsDirection = 1;
        return sailsDirection;
    }

    //randomly changing the velocity of the wind by 1
    public void wind() {
        change = (int)(Math.random()*2);
        if ((change == 0) && (windVelocity < 4)) {
            windVelocity++;
        } else if ((change == 1) && (windVelocity > -4)) {
            windVelocity--;
        }
    }

    //checking if the ship takes damage at this moment
    public void checkDamage() {
        if ((windVelocity > 2) || (windVelocity < -2)) {
            /*
            if the wind velocity and the direction of the sails is the same, increase damage
            if two numbers are of the same sign their product gives a positive number
            if the sails direction has not yet been chosen, the damage will still be taken
             */
            if (windVelocity * sailsDirection >= 0) {
                damage++;
            }
        }
    }
}
