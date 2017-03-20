package Controller;

import Modele.Field;
import Modele.Modele;
import View.PoneymonView;

public abstract class Controleur {
    private Modele m;
    private PoneymonView view;

    public Controleur(Modele m, PoneymonView view) {
        this.setM(m);
        this.setView(view);
    }



    public abstract void onKeyTouche(int i);

    public abstract void onKeyTouche(int i, boolean b);


    public PoneymonView getView() {
        return view;
    }

    public void setView(PoneymonView view) {
        this.view = view;
    }

    public Modele getM() {
        return m;
    }

    public void setM(Modele m) {
        this.m = m;
    }
}
