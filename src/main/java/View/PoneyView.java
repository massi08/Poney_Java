package View;

import Modele.Modele;
import javafx.scene.image.Image;

public class PoneyView extends Image implements ObjectFieldView {
    private String color;

    public PoneyView(String color) {
        super(color);
    }



}
