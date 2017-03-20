package View;

public class FieldFactoryView {
    public ObjectFieldView getObject(String typeObjet, String url) {
        if (typeObjet == null) {
            return null;
        }
        if (typeObjet.equalsIgnoreCase("PoneyView")) {
            return new PoneyView(url);
        } else if (typeObjet.equalsIgnoreCase("ObstacleView")) {
            return new ObstacleView(url);
        }
        return null;
    }
}
