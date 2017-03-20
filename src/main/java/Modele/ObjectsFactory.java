package Modele;

public class ObjectsFactory {

    /**
     * Constructor of objets
     * @param typeObjet name of the Object
     * @param color of the Poney
     * @param yInt vertical position
     * @return object constructed
     */
    public Objects getObject(String typeObjet, String color, int yInt) {
        if (typeObjet == null) {
            return null;
        }
        if (typeObjet.equalsIgnoreCase("PoneyAmeliore")) {
            return new PoneyAmeliore(color, yInt);
        } else if (typeObjet.equalsIgnoreCase("PoneyIA")) {
            return new PoneyIA(color, yInt);
        } else if (typeObjet.equalsIgnoreCase("ObstacleDeplace")) {
            return new ObstacleDeplace(yInt, 1.0);
        } else if (typeObjet.equalsIgnoreCase("ObstacleStatique")) {
            return new ObstacleStatique(550, yInt);
        }
        return null;
    }
}
