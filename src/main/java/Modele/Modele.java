package Modele;

import java.util.*;
import java.util.Observable;

import Controller.Observer;

public class Modele extends Observable {
    private Field field;
    private ArrayList<Observer> listObserver = new ArrayList<Observer>();

    /**
     * Constructor of Modele
     * @param width for the field
     * @param height for the field
     * @param numberPoney number of Poneys
     * @param NumberRounds number of rounds
     */
    public Modele(int width, int height, int numberPoney, int NumberRounds) {
        super();
        this.field = new Field(width, height, numberPoney, NumberRounds);
    }

    /**
     * Get Field
     * @return field created
     */
    public Field getField() {
        return this.field;
    }

    /**
     * Add an observer to Modele
     * @param obs Observer
     */
    public void addObserver(Observer obs) {
        this.listObserver.add(obs);
    }

}