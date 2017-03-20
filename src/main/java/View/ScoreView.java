package View;

import Controller.Controleur;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class ScoreView extends Canvas implements ObjectSceneView {
    private final GraphicsContext gc;
    final double width;
    final double height;
    Image backgroundImage;
    Image[] poneys;
    String[] colorMap = new String[]{"blue", "green", "orange", "purple", "yellow"};
    public Vector rank;
    public Text[] text;

    public ScoreView(double h, double w) {
        super(h, w);
        width = w;
        height = h;
        this.setHeight(height);
        this.setWidth(width);
        /** permet de capturer le focus et donc les evenements clavier et souris */
        this.setFocusTraversable(true);
        gc = this.getGraphicsContext2D();
        backgroundImage = new Image("assets/Ranking.png");
        gc.setFill(Color.rgb(250, 250, 250));
        gc.fillRect(0, 0, width, height);
        gc.drawImage(backgroundImage, 0, 410);
        poneys = new Image[5];
        text = new Text[6];
        text[0] = new Text("Rank");
        text[0].setX(245);
        text[0].setY(60);
        text[0].setFill(Color.rgb(33, 33, 33));
        text[0].setFont(Font.font(50));


    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void displayPoneys() {
        Enumeration vEnum = rank.elements();
        Iterator it = rank.iterator();
        int i = 0;

        while (vEnum.hasMoreElements()) {
            String color = (String) vEnum.nextElement();
            text[i + 1] = new Text(i + 1 + ".  Poney " + color);
            text[i + 1].setX(210);
            text[i + 1].setY(i * 30 + 100);
            text[i + 1].setFill(Color.rgb(33, 33, 33));
            text[i + 1].setFont(new Font(25));

            poneys[i] = new Image("assets/pony-" + color + "-running.gif");
            i++;
        }
        gc.drawImage(poneys[0], 180, 295);
        gc.drawImage(poneys[1], 380, 350);
        gc.drawImage(poneys[2], -30, 370);
//        gc.drawImage(poneys[3], 50, 100);
//        gc.drawImage(poneys[4], 350, 100);
    }

}
