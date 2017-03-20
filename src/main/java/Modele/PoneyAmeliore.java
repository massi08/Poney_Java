/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;


public class PoneyAmeliore extends Poney {

    /**
     * Constructeur du Poney
     * <p>
     * @param color couleur du poney
     * @param yInit position verticale
     */
    public PoneyAmeliore(String color, int yInit) {
        super(color, yInit);
        used = false;
        nion = false;
    }


    /**
     * Movement of poney
     */
    @Override
    public void move() {
        setX(getX() + getSpeed());

        if (getX() > 520) {
            setX(-220.0);
            this.setSpeed(randomGenerator.nextFloat());
            this.setSpeedDecreased(false);
            setTour_realise(getTour_realise() + 1);
            modeNianOff();
        }
    }


}
