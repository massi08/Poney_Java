package Modele;

import java.util.Random;

public class ObstacleStatique extends Obstacle {
    /**
     * Constructeur du ObstacleStatique
     * <p>
     * @param x position horizontal
     * @param y position verticale
     */
    public ObstacleStatique(double x, double y) {
        super(x, y);
        setX(x);
        setY(y);
        setSpeed(0);
    }

    /**
     * Set position horizontal of the Obstacle
     * @param x position horizontal
     */
    @Override
    public void setX(double x) {
        super.x = 200+ Math.random()*((550-200)+1);
    }

    /**
     * Movement of Obstacle
     */
    @Override
    public void move() {

    }

}
