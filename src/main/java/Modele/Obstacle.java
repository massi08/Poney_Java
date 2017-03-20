package Modele;


public abstract class Obstacle implements Objects {

    protected double x;
    protected double y;
    protected double speed;

    /**
     * Constructeur du ObstacleDeplace
     * <p>
     * @param speed of Obstacle
     * @param y position verticale
     */
    public Obstacle(double y, double speed) {
        this.setX(600);
        this.setY(y);
        this.setSpeed(speed);
    }

    /**
     * Position Obstacle
     * @return the position horizontal of the Obstacle
     */
    @Override
    public double getX() {
        return x;
    }

    /**
     * Position Obstacle
     * @return the position vertical of the Obstacle
     */
    @Override
    public double getY() {
        return y;
    }

    /**
     * Set position vertical of the Obstacle
     * @param y position vertical
     */
    @Override
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Speed Obstacle
     * @return speed of the Obstacle
     */
    @Override
    public double getSpeed() {
        return speed;
    }

    /**
     * Set speed of the Obstacle
     * @param speed for the Obstacle
     */
    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

}
