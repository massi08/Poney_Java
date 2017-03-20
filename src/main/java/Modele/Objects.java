package Modele;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Random;

public interface Objects {

    /**
     * Deplacement des éléments
     */
    void move();

    /**
     * Position
     * @return position horizontal
     */
    double getX();

    /**
     * Set position horizontal
     * @param x position horizontal
     */
    void setX(double x);

    /**
     * Position
     * @return the position vertical
     */
    double getY();

    /**
     * Set position vertical
     * @param y position vertical
     */
    void setY(double y);

    /**
     * Speed
     * @return speed of the Object
     */
    double getSpeed();

    /**
     * Set speed of the Object
     * @param speed for the Object
     */
    void setSpeed(double speed);
}
