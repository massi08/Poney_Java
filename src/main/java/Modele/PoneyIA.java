package Modele;

public class PoneyIA extends Poney {


    /**
     * Constructeur du Poney
     * <p>
     * @param color couleur du poney
     * @param yInit position verticale
     */
    public PoneyIA(String color, int yInit) {
        super(color, yInit);
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
