package Modele;

import java.util.Random;

public class ObstacleDeplace extends Obstacle {


    Random randomGenerator = new Random();
    /**
     * Constructeur du ObstacleDeplace
     * <p>
     * @param x position horizontal
     * @param y position verticale
     */
    public ObstacleDeplace(double x, double y) {
        super(x, y);
    }

    /**
     * Movement of Obstacle
     */
    @Override
    public void move() {
        setX(getX() - getSpeed());

        if (getX() < -200) {
            setX(600);
            this.setSpeed(randomGenerator.nextFloat());
        }
    }
    /**
     * Set position horizontal of the Obstacle
     * @param x position horizontal
     */
    @Override
    public void setX(double x) {
        this.x = x;
    }
}
