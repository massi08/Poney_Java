package View;

import Modele.Modele;

public class SceneFactoryView {

    public ObjectSceneView getObject(String typeObjet, double height, double width) {
        if (typeObjet == null) {
            return null;
        }
        if (typeObjet.equalsIgnoreCase("PoneymonViewField")) {
            return new PoneymonViewField(height, width);
        } else if (typeObjet.equalsIgnoreCase("ScoreView")) {
            return new ScoreView(height, width);
        } else if (typeObjet.equalsIgnoreCase("GameControlView")) {
            return new GameControlView(height, width);
        }
        return null;
    }
}
