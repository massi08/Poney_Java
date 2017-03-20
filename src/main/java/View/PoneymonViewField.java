package View;

import Controller.Controleur;
import Modele.Modele;
import Modele.Obstacle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class PoneymonViewField extends Canvas implements ObjectSceneView {
    private final GraphicsContext gc;
    final double width;
    final double height;
    private Image[] poneyImage;
    private Image[] obstacleImage;
    String[] colorMap = new String[]{"blue", "green", "orange", "purple", "yellow"};

    public PoneymonViewField(double h, double w) {
        super(h, w);
        FieldFactoryView objetsFactory = new FieldFactoryView();
        width = w;
        height = h;
//        Color backgroundColor = Color.rgb(230,231,232);
        this.setHeight(height);
        this.setWidth(width);
        /** permet de capturer le focus et donc les evenements clavier et souris */
        this.setFocusTraversable(true);

        gc = this.getGraphicsContext2D();
        setPoneyImage(new PoneyView[5]);
        setObstacleImage(new ObstacleView[5]);
        for (int i = 0; i < 5; i++) {
            getPoneyImage()[i] = (PoneyView)objetsFactory.getObject("PoneyView","assets/pony-" + colorMap[i] + "-running.gif");
            getObstacleImage()[i] = (ObstacleView)objetsFactory.getObject("ObstacleView","assets/obstacle-blue.png");

        }
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public Image[] getObstacleImage() {
        return obstacleImage;
    }

    public void setObstacleImage(Image[] obstacleImage) {
        this.obstacleImage = obstacleImage;
    }

    public Image[] getPoneyImage() {
        return poneyImage;
    }

    public void setPoneyImage(Image[] poneyImage) {
        this.poneyImage = poneyImage;
    }
}
