package Modele;

import java.util.*;

import Controller.Observer;

/**
 * Classe gerant le terrain de jeu.
 */
public class Field extends Observable {

    private Obstacle[] obstacle;
    private Poney[] poneys; //= new PoneyAmeliore[5];
    private Vector rank;
    /**
     * Couleurs possibles
     */
    String[] colorMap = new String[]{"blue", "green", "orange", "purple", "yellow"};
    /**
     * Tableau traçant les evenements
     */
    ArrayList<String> input = new ArrayList<String>();
    private ArrayList<Observer> listObserver = new ArrayList<Observer>();
    private final int width;
    private final int height;
    private int numberPoney;
    private int numberObstacles;
    private boolean finished;
    private int numberRounds;
    Random randomGenerator = new Random();
    /*final GraphicsContext gc;
    GridPane  rootJ = new GridPane ();


    int nbtour;
    Text winner = new Text();*/

    /**
     * Canvas dans lequel on va dessiner le jeu.
     * <p>
     * Scene principale du jeu a laquelle on va ajouter notre Canvas
     *
     * @param w width of field
     * @param h height of field
     * @param numberPoney number of Poney
     * @param numberRounds number of rounds
     */
    public Field(int w, int h, int numberPoney, int numberRounds) {
        this.height = h;
        this.width = w;
        ObjectsFactory objectsFactory = new ObjectsFactory();

        this.setPoney(new Poney[numberPoney]);
        this.setObstacle(new Obstacle[numberPoney]);
        if (numberPoney == 5) {
            this.getPoney()[0] = (PoneyAmeliore) objectsFactory.getObject("PoneyAmeliore", colorMap[0], 0 * 110);
            this.getPoney()[1] = (PoneyIA) objectsFactory.getObject("PoneyIA", colorMap[1], 1 * 110);
            this.getPoney()[2] = (PoneyAmeliore) objectsFactory.getObject("PoneyAmeliore", colorMap[2], 2 * 110);
            this.getPoney()[3] = (PoneyIA) objectsFactory.getObject("PoneyIA", colorMap[3], 3 * 110);
            this.getPoney()[4] = (PoneyIA) objectsFactory.getObject("PoneyIA", colorMap[4], 4 * 110);
        }
        for (int i = 0; i < numberPoney; i++) {

            int choice = 1 + (int) (Math.random() * ((2 - 1) + 1));
            System.out.println(choice);
            if (choice == 1)
                this.getObstacle()[i] = (ObstacleDeplace) objectsFactory.getObject("ObstacleDeplace", "", (i * 110) + 50);
            else
                this.getObstacle()[i] = (ObstacleStatique) objectsFactory.getObject("ObstacleStatique", "", (i * 110) + 50);

        }
        this.numberPoney = numberPoney;
        this.numberObstacles = numberPoney;
        this.numberRounds = numberRounds;
        this.finished = false;
        setRank(new Vector());
        addNeighbour();
    }

    /**
     *  Add neighbours to each Poneys
     */
    public void addNeighbour() {
        for (int i = 0; i < getNumberPoney(); i++) {
            if (i == 0) {
                System.out.println(this.getPoney()[0].getPoneyColor());
                this.getPoney()[i].addVoisin(this.getPoney()[i + 1]);
            } else if (i == getNumberPoney() - 1) {
                this.getPoney()[i].addVoisin(this.getPoney()[i - 1]);
            } else {
                this.getPoney()[i].addVoisin(this.getPoney()[i + 1]);
                this.getPoney()[i].addVoisin(this.getPoney()[i - 1]);
            }
        }
    }

    /**
     * Movement of each poneys
     */
    public void Poneymove() {
        for (int i = 0; i < this.getNumberPoney(); i++) {
            Poney P = this.getPoney()[i];
            if (P.getTour_realise() != this.getNumberRounds()) {
                P.setX(P.getX() + P.getSpeed());
                P.whileJumping();
                collision(i);
                if (P.getX() > 520) {
                    P.setX(-220.0);
                    P.setSpeed(0.3+(double)(Math.random()*(1-0.3)+1));
                    P.setSpeedDecreased(false);
                    P.setTour_realise(P.getTour_realise() + 1);
                    if (ObstacleStatique.class.isInstance(getObstacle()[i]))
                        getObstacle()[i].setX(0);
                    P.modeNianOff();
                    setChanged();
                    notifyObserver(false, i, getPoney()[i].getPoneyColor(), getPoney()[i].getX(), getPoney()[i].getY());
                }
            } else {
                if (!getRank().contains(P.getPoneyColor()))
                    getRank().add(P.getPoneyColor());
            }
            setChanged();
            notifyObserver(i, getPoney()[i].getX(), getPoney()[i].getY());
        }
    }

    /**
     * Movement of each Obstacle
     */
    public void Obstaclemove() {
        for (int i = 0; i < getNumberObstacles(); i++) {
            if (ObstacleDeplace.class.isInstance(getObstacle()[i])) {
                getObstacle()[i].setX(getObstacle()[i].getX() - getObstacle()[i].getSpeed());

                if (getObstacle()[i].getX() < -200) {
                    getObstacle()[i].setX(600);
                    getObstacle()[i].setSpeed(randomGenerator.nextFloat());
                }
            }
            setChanged();
            notifyObstacle(i, getObstacle()[i].getX(), getObstacle()[i].getY());
        }
    }

    /**
     *  Get Obstacle
     * @return an Obstacle
     */
    public Obstacle[] getObstacle() {
        return obstacle;
    }

    /**
     * Set Obstacle
     * @param obstacle an Obstacle
     */
    public void setObstacle(Obstacle[] obstacle) {
        this.obstacle = obstacle;
    }

    /**
     *  Get Poney
     * @return an Poney
     */
    public Poney[] getPoney() {
        return poneys;
    }

    /**
     * Field Dimension
     * @return width of Field
     */
    public int getWidth() {
        return width;
    }

    /**
     * Field Dimension
     * @return height of Field
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set Poney
     * @param poneys a Poney
     */
    public void setPoney(Poney[] poneys) {
        this.poneys = poneys;
    }

    /**
     * Get number of Poneys in the Field
     * @return number of Poneys
     */
    public int getNumberPoney() {
        return numberPoney;
    }

    /**
     * Set number of Poneys in the Field
     * @param numberPoney number of Poneys
     */
    public void setNumberPoney(int numberPoney) {
        this.numberPoney = numberPoney;
    }

    /**
     * Get number of Obstacle in the Field
     * @return number of Obstacle
     */
    public int getNumberObstacles() {
        return numberObstacles;
    }

    /**
     * Set number of Obstacles in the Field
     * @param numberObstacles number of Obstacle
     */
    public void setNumberObstacles(int numberObstacles) {
        this.numberObstacles = numberObstacles;
    }

    /**
     * Get game Status
     * @return status of the game
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Set game status
     * @param finished is the game's status
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    /**
     * Distance
     * @param P is a Poney
     * @param O is an Obstacle
     * @return distance between Poney and Obstacle
     */
    public double distancePoneyObstacle(Poney P, Obstacle O) {
        return Math.sqrt(Math.pow(P.getX() - (O.getX() - 155), 2) + Math.pow(P.getY() - O.getY(), 2));
    }

    /**
     * Number of Rounds
     * @return the number of rounds
     */
    public int getNumberRounds() {
        return numberRounds;
    }

    /**
     * Check game status
     * @return the game's status
     */
    public boolean endGame() {
        if (getRank().size() == getNumberPoney())
            setFinished(true);
        return this.isFinished();
    }

    /**
     * Choose Artificial Intelligence method
     * @param choix number of choice
     */
    public void addSpeed(int choix) {
        for (int i = 0; i < this.getNumberPoney(); i++) {
            if (PoneyIA.class.isInstance(getPoney()[i])) {
                switch (choix){
                    case 1:
                        for (int j = 0; j < this.getPoney()[i].getVoisin().size(); j++) {
                            Poney temp = (Poney) this.getPoney()[i].getVoisin().get(j);
                            if (this.getPoney()[i].distance(temp) > 200) {
                                if (temp.getTour_realise() == this.getPoney()[i].getTour_realise() + 1) {
                                    this.getPoney()[i].modeNianOn();
                                    setChanged();
                                    notifyObserver(true, i, getPoney()[i].getPoneyColor(), getPoney()[i].getX(), getPoney()[i].getY());
                                }
                            }
                        }
                    case 2:
                        if (this.getPoney()[i].getSpeed()<0.3) {this.getPoney()[i].modeNianOn();
                            this.getPoney()[i].modeNianOn();
                            setChanged();
                            notifyObserver(true, i, getPoney()[i].getPoneyColor(), getPoney()[i].getX(), getPoney()[i].getY());
                        }
                    case 3:
                        for (int j = 0; j < this.getPoney()[i].getVoisin().size(); j++) {
                            Poney temp = (Poney) this.getPoney()[i].getVoisin().get(j);
                            if (this.getPoney()[i].distance(temp) > 200) {
                                this.getPoney()[i].modeNianOn();
                                setChanged();
                                notifyObserver(true, i, getPoney()[i].getPoneyColor(), getPoney()[i].getX(), getPoney()[i].getY());
                            }
                        }
                }
            }
        }
    }

    /**
     * Consequence of a collision
     * @param i position of Obstacle and Poney
     */
    public void collision(int i) {
        Poney p = this.getPoney()[i];
        if (this.distancePoneyObstacle(p, this.getObstacle()[i]) < 52.0) {
            if (ObstacleStatique.class.isInstance(this.getObstacle()[i])) {
                System.out.println(this.distancePoneyObstacle(p, this.getObstacle()[i]));
            }
            if (!p.isSpeedDecreased()) {
                p.setSpeed(p.getSpeed() / 2);
                p.setSpeedDecreased(true);
                p.modeNianOff();
                setChanged();
                notifyObserver(false, i, getPoney()[i].getPoneyColor(), getPoney()[i].getX(), getPoney()[i].getY());
            }
            System.out.println("speed decreased for " + p.getPoneyColor());
        }
    }

    /**
     * Jumps PoneyIA
     */
    public void jumpPoneyIA() {
        for (int i = 0; i < this.getNumberPoney(); i++) {
            if (PoneyIA.class.isInstance(this.getPoney()[i])) {
                if (this.distancePoneyObstacle(this.getPoney()[i], this.getObstacle()[i]) < 55.0) {
                    this.getPoney()[i].startJump();
                    this.getPoney()[i].endJump();
                }
            }
        }
    }

    /**
     * Activate Nian of Poney
     * @param i position of Poney
     */
    public void setNionPoney(int i) {
        if (!getPoney()[i].isUsed()) {
            System.out.println("set");
            getPoney()[i].modeNianOn();
            getPoney()[i].setUsed(true);
            setChanged();
            notifyObserver(true, i, getPoney()[i].getPoneyColor(), getPoney()[i].getX(), getPoney()[i].getY());
        }
    }

    /**
     * Jumps Poney
     * @param i position of Poney
     * @param b status of jump
     */
    public void jumpPoney(int i, boolean b) {
        if (PoneyAmeliore.class.isInstance(this.getPoney()[i])) {
            this.getPoney()[i].startJump();
            this.getPoney()[i].endJump();
            setChanged();
            notifyObserver(i, getPoney()[i].getX(), getPoney()[i].getY());
        }
    }

    /**
     * Notify the Poney's observer
     * @param position of Poney
     * @param x position horizontal
     * @param y position vertical
     */
    public void notifyObserver(int position, double x, double y) {
        for (Observer obs : listObserver) {
            obs.updatePoney(position, x, y);
        }
    }

    /**
     * Notify the Obstacle's observer
     * @param position of Obstacle
     * @param x position horizontal
     * @param y position vertical
     */
    public void notifyObstacle(int position, double x, double y) {
        for (Observer obs : listObserver) {
            obs.updateObstacle(position, x, y);
        }
    }

    /**
     * Notify the Poney's observer for nian mode
     * @param bool status whether to turn on or off nian
     * @param position of the Poney
     * @param str color of Poney
     * @param x position horizontal
     * @param y position vertical
     */
    public void notifyObserver(Boolean bool, int position, String str, double x, double y) {
        for (Observer obs : listObserver)
            obs.updatePoney(bool, position, str, x, y);
    }

    /**
     * Add an observer to Modele
     * @param obs Observer
     */
    public void addObserver(Observer obs) {
        this.listObserver.add(obs);
    }

    /**
     * Set the number of rounds
     * @param numberRounds number of rounds
     */
    public void setNumberRounds(int numberRounds) {
        this.numberRounds = numberRounds;
    }

    /**
     * Display ranks of poneys
     * @param v contains color of Poneys
     * @return final rank of poneys
     */
    public String displayRank(Vector v) {
        String results = "";
        Enumeration vEnum = v.elements();
        Iterator it = v.iterator();
        while (vEnum.hasMoreElements()) {
            //Poney p = (Poney) it;
            results += " " + vEnum.nextElement();
        }
        return results;
    }

    /**
     * Get rank of poneys
     * @return rank of each Poneys
     */
    public Vector getRank() {
        return rank;
    }

    /**
     * Set rank of Poneys
     * @param rank contains ranks of each Poneys
     */
    public void setRank(Vector rank) {
        this.rank = rank;
    }
}