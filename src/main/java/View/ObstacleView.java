package View;

import javafx.scene.image.Image;

public class ObstacleView extends Image implements ObjectFieldView {
    private String color;

    public ObstacleView(String color) {
        super(color);
    }
}
