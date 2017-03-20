package View;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameControlView extends Canvas implements ObjectSceneView {
    private GraphicsContext gc;
    private Text []text;
    private Button []button;
    double width;
    double height;


    public GameControlView(double height, double width) {
        super(height, width);
        width = width;
        height = height;
        this.setHeight(height);
        this.setWidth(width);
        this.setFocusTraversable(true);
        setGc(this.getGraphicsContext2D());
        getGc().setFill(Color.rgb(33,33,33));
        getGc().fillRect(0, 0, width, height);

        setText(new Text[5]);
        setButton(new Button[5]);
        getText()[0] = new Text("Game");
        getText()[0].setX(670);
        getText()[0].setY(40);
        getText()[0].setFill(Color.rgb(255, 255, 255));
        getText()[0].setFont(Font.font(25));

        getText()[1] = new Text("IA Tactics");
        getText()[1].setX(650);
        getText()[1].setY(180);
        getText()[1].setFill(Color.rgb(255, 255, 255));
        getText()[1].setFont(Font.font(25));


        getButton()[0] = new Button("Start");
        getButton()[0].setLayoutX(640);
        getButton()[0].setLayoutY(60);

        getButton()[1] = new Button("Pause");
        getButton()[1].setLayoutX(710);
        getButton()[1].setLayoutY(60);

        getButton()[2] = new Button("Tactic 1");
        getButton()[2].setLayoutX(660);
        getButton()[2].setLayoutY(200);

        getText()[2] = new Text("When a poney starts getting \n far and he's on the next\n round, Nian mode is activated.");
        getText()[2].setX(610);
        getText()[2].setY(250);
        getText()[2].setFill(Color.rgb(242, 248, 251));
        getText()[2].setFont(Font.font(14));

        getButton()[3] = new Button("Tactic 2");
        getButton()[3].setLayoutX(660);
        getButton()[3].setLayoutY(300);

        getText()[3] = new Text("When a poney is too slow, \n Nian mode is activated.");
        getText()[3].setX(610);
        getText()[3].setY(350);
        getText()[3].setFill(Color.rgb(242, 248, 251));
        getText()[3].setFont(Font.font(14));

        getButton()[4] = new Button("Tactic 3");
        getButton()[4].setLayoutX(660);
        getButton()[4].setLayoutY(400);

        getText()[4] = new Text("When a poney starts getting \n far, Nian mode is activated.");
        getText()[4].setX(610);
        getText()[4].setY(450);
        getText()[4].setFill(Color.rgb(242, 248, 251));
        getText()[4].setFont(Font.font(14));

        for(int i = 0; i<5; i++) {
            getButton()[i].getStyleClass().add("btn");
        }

    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public Text[] getText() {
        return text;
    }

    public void setText(Text[] text) {
        this.text = text;
    }

    public Button[] getButton() {
        return button;
    }

    public void setButton(Button[] button) {
        this.button = button;
    }
}
