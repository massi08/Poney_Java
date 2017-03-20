package View;

import Controller.*;
import Modele.Modele;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class PoneymonView implements Observer {
    private Stage stage;
    private PoneymonViewField field;
    private GameControlView GameContol;
    GridPane rootJ = new GridPane();
    public final int width = 800;
    public final int height = 600;
    private Scene scene;
    Text winner = new Text();
    public ScoreView viewscore;

    public Modele modele;
    public Group root;
    SceneFactoryView objectScene;

    public PoneymonView(Modele modele, Stage stage) {
        this.setModele(modele);
        this.stage = stage;
        modele.addObserver(this);
        try {
            this.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void start(Stage stage) throws Exception {
        objectScene = new SceneFactoryView();
        // Nom de la fenetre
        stage.setTitle("Poneymon");
        BorderPane bor = new BorderPane();
        //bor.setMinWidth(800);
        root = new Group();
        setScene(new Scene(root));
        // On cree le terrain de jeu et on l'ajoute a la racine de la scene

        setField((PoneymonViewField) objectScene.getObject("PoneymonViewField", 600, 600));
        root.getChildren().add(getField());
        stage.setWidth(830);
        setGameContol((GameControlView)objectScene.getObject("GameControlView", 600, 230));

        bor.setRight(getGameContol());
        bor.setLeft(getField());
        root.getChildren().add(bor);

        for (int i = 0; i < 5; i++) {
            root.getChildren().add(getGameContol().getText()[i]);
        }
        for (int i = 0; i < 5; i++) {
            root.getChildren().add(getGameContol().getButton()[i]);
        }
        //root.getChildren().add(toto.getButton()[0]);

        // On ajoute la scene a la fenetre et on affiche
        getScene().getStylesheets().add("style/style.css");
        stage.setScene(getScene());
        stage.show();
    }

    public void updatePoney(int i, double x, double y) {
        getField().getGc().drawImage(getField().getPoneyImage()[i], x, y);
    }

    public void updateObstacle(int i, double x, double y) {
        getField().getGc().drawImage(getField().getObstacleImage()[i], x, y);
    }

    public void updatePoney(boolean bool, int i, String color, double x, double y) {
        if (bool) {
            field.getPoneyImage()[i] = new PoneyView("assets/pony-" + color + "-rainbow.gif");
        } else {
            field.getPoneyImage()[i] = new PoneyView("assets/pony-" + color + "-running.gif");
        }

    }

    public void update(int i) {

    }


    public PoneymonViewField getField() {
        return field;
    }

    public void setField(PoneymonViewField field) {
        this.field = field;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public void viewScore() {
        root.getChildren().remove(getField());
        viewscore = (ScoreView) objectScene.getObject("ScoreView", 817, 600);
        root.getChildren().add(viewscore);
        this.stage.setWidth(817);
    }

    public GameControlView getGameContol() {
        return GameContol;
    }

    public void setGameContol(GameControlView gameContol) {
        GameContol = gameContol;
    }
}