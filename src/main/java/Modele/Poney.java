package Modele;

import Controller.Observer;

import java.util.*;

/**
 * Classe gerant un Poney
 */
public abstract class Poney implements Objects {
    private double speedY;
    private double x;
    private double y;
    private double speed;
    private double gravity;
    private boolean jumped;
    private String poneyColor;
    private int tour_realise;
    private List voisin;
    private boolean speedDecreased;
    protected boolean used;
    protected boolean nion;
    final double initialPosY;
    private ArrayList<Observer> listObserver = new ArrayList<Observer>();
    Random randomGenerator = new Random();

    /**
     * Constructeur du Poney
     * <p>
     * @param color couleur du poney
     * @param yInit position verticale
     */
    Poney(String color, int yInit) {
        // Tous les poneys commencent a gauche du canvas, 
        // on commence a -100 pour les faire apparaitre progressivement
        setX(-100.0);
        setY(yInit);
        initialPosY = yInit;
        setGravity(0.2);
        setSpeedY(0);
        setJumped(false);
        setPoneyColor(color);

        // Tous les poneys ont une vitesse aleatoire entre 0.0 et 1.0
        randomGenerator = new Random();
        setSpeed(0.3+(double)(Math.random()*(1-0.3)+1));
        System.out.println("speed" + this.getSpeed());
        setTour_realise(0);
        voisin = new ArrayList();
    }

    /**
     *  Position Poney
     * @return the position horizontal of the Poney
     */
    @Override
    public double getX() {
        return x;
    }

    /**
     * Set position horizontal of the Poney
     * @param x position horizontal
     */
    @Override
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Position Poney
     * @return the position vertical of the Poney
     */
    @Override
    public double getY() {
        return y;
    }

    /**
     * Set position vertical of the Poney
     * @param y position vertical
     */
    @Override
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Speed Poney
     * @return speed of the Poney
     */
    @Override
    public double getSpeed() {
        return speed;
    }

    /**
     * Set speed of the Poney
     * @param speed for the Poney
     */
    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Activate mode Nian once
     */
    public void modeNianOn() {
        if (!isUsed()) {
            this.setSpeed(this.getSpeed() * 2);
            setUsed(true);
            setNion(true);
        }
    }

    /**
     * Deactivate mode Nian
     */
    public void modeNianOff() {
        setNion(false);
    }

    /**
     * Start the jump of the Poney
     */
    public void startJump() {
        if (!hasJumped()) {
            setSpeedY(-12.0);
            setJumped(true);
        }
    }

    /**
     * End the jump of the Poney
     */
    public void endJump() {
        if (getSpeedY() < -6.0) {
            setSpeedY(-6.0);
        }
    }

    /**
     * Path of the Poney while jumping
     */
    public void whileJumping() {
        setSpeedY(getSpeedY() + getGravity());
        setY(getY() + getSpeedY());
        setX(getX() + getSpeed());

        if (getY() > initialPosY) {
            setY(initialPosY);
            setSpeedY(0.0);
            setJumped(false);
        }
    }

    /**
     * Get color of the Poney
     * @return color of the Poney
     */
    public String getPoneyColor() {
        return poneyColor;
    }

    /**
     * Set color for the Poney
     * @param poneyColor color for the Poney
     */
    public void setPoneyColor(String poneyColor) {
        this.poneyColor = poneyColor;
    }

    /**
     * Get number of rounds accomplished
     * @return number of rounds accomplished
     */
    public int getTour_realise() {
        return tour_realise;
    }

    /**
     * Set the number of rounds accomplished
     * @param tour_realise number of rounds accomplished
     */
    public void setTour_realise(int tour_realise) {
        this.tour_realise = tour_realise;
    }

    /**
     * Retrieve distance
     * @param P is a Poney
     * @return distance between this Poney and P
     */
    public double distance(Poney P) {
        return Math.sqrt(Math.pow(this.getX() - P.getX(), 2) + Math.pow(this.getY() - P.getY(), 2));
    }

    /**
     * Retrieve distance
     * @param P1 is a Poney
     * @param P2 is a Poney
     * @return distance between P1 and P2
     */
    public double distance(Poney P1, Poney P2) {
        return Math.sqrt(Math.pow(P1.getX() - P2.getX(), 2) + Math.pow(P1.getY() - P2.getY(), 2));
    }

    /**
     *  Add Poney to his neighbour list
     * @param p is a Poney
     */
    public void addVoisin(Poney p) {
        if (this != p)
            this.voisin.add(p);
    }

    /**
     * Display his neighbours
     */
    public void afficheVoisin() {
        Iterator it = this.voisin.listIterator();

        for (int i = 0; i < this.voisin.size(); i++) {
            Poney p2 = (Poney) this.voisin.get(i);
            System.out.println(p2.getPoneyColor());
        }
    }

    /**
     * Remove a Poney from his neighbours
     * @param p is a Poney
     */
    public void removeVoisin(Poney p) {
        this.voisin.remove(p);
    }

    /**
     * @return his neighbours list
     */
    public List getVoisin() {
        return voisin;
    }

    /**
     * Get speed
     * @return vertical speed
     */
    public double getSpeedY() {
        return speedY;
    }

    /**
     * Set vertical speed of the Poney
     * @param speedY speed for the Poney
     */
    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    /**
     * Get gravity
     * @return gravity for the Poney
     */
    public double getGravity() {
        return gravity;
    }

    /**
     * Set gravity for the Poney
     * @param gravity for the Poney
     */
    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    /**
     * Check if Poney has jumped
     * @return status of Poney's jump
     */
    public boolean hasJumped() {
        return jumped;
    }

    /**
     * Set his jump status
     * @param jumped status oh his jump
     */
    public void setJumped(boolean jumped) {
        this.jumped = jumped;
    }

    /**
     * Check if Poney speed has decreased
     * @return status of Poney decreased speed
     */
    public boolean isSpeedDecreased() {
        return speedDecreased;
    }

    /**
     * Set his speed decreased
     * @param speedDecreased status of his decreased speed
     */
    public void setSpeedDecreased(boolean speedDecreased) {
        this.speedDecreased = speedDecreased;
    }

    /**
     * Check if Poney has used his nion
     * @return the availability status of his nian
     */
    public boolean isUsed() {
        return this.used;
    }

    /**
     * Set if Poney has used his nian
     * @param used his nian mode
     */
    public void setUsed(boolean used) {
        this.used = used;
    }

    /**
     * Get Nian mode
     * @return nian mode of the Poney
     */
    public boolean isNion() {
        return this.nion;
    }

    /**
     * Set Nian mode
     * @param nion mode of the Poney
     */
    public void setNion(boolean nion) {
        this.nion = nion;
    }

}
