package Controller;

import Modele.Field;
import Modele.Modele;
import View.PoneymonView;
import View.ScoreView;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.Vector;

public class ControleurPoneymon extends Controleur {
    private Timeline timer;
    private AnimationTimer animationTimer;
    private AnimationTimer scoretime;
    ArrayList<String> input = new ArrayList<String>();
    int IAchoice;

    public ControleurPoneymon(Modele m, PoneymonView view) {
        super(m, view);
        touchHandler(view.getScene());
        timeHandler(view.width, view.height);
        Click();
    }

    public void Click() {
        for (int i = 0; i < 5; i++) {
            final int j = i;
            getView().getGameContol().getButton()[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int clicked;
                    if (j == 0) {
                        animationTimer.start();
                        timer.play();
                        getView().getGameContol().getButton()[1].getStyleClass().remove("btn-selected");
                        if (getView().getGameContol().getButton()[2].getStyleClass().hashCode() != 1986151201
                                && getView().getGameContol().getButton()[3].getStyleClass().hashCode() != 1986151201
                                && getView().getGameContol().getButton()[4].getStyleClass().hashCode() != 1986151201) {
                            getView().getGameContol().getButton()[2].getStyleClass().add("btn-selected");
                        }
                    }
                    if (j == 1) {
                        animationTimer.stop();
                        timer.pause();
                        if (getView().getGameContol().getButton()[j].getStyleClass().hashCode() != 1986151201)
                            getView().getGameContol().getButton()[j].getStyleClass().add("btn-selected");
                    }
                    if (j == 2) {
                        getView().getGameContol().getButton()[3].getStyleClass().remove("btn-selected");
                        getView().getGameContol().getButton()[4].getStyleClass().remove("btn-selected");
                        if (getView().getGameContol().getButton()[j].getStyleClass().hashCode() != 1986151201)
                            getView().getGameContol().getButton()[j].getStyleClass().add("btn-selected");
                        IAchoice = 1;
                    }
                    if (j == 3) {
                        getView().getGameContol().getButton()[2].getStyleClass().remove("btn-selected");
                        getView().getGameContol().getButton()[4].getStyleClass().remove("btn-selected");
                        if (getView().getGameContol().getButton()[j].getStyleClass().hashCode() != 1986151201)
                            getView().getGameContol().getButton()[j].getStyleClass().add("btn-selected");
                        IAchoice = 2;
                    }
                    if (j == 4) {
                        getView().getGameContol().getButton()[2].getStyleClass().remove("btn-selected");
                        getView().getGameContol().getButton()[3].getStyleClass().remove("btn-selected");
                        if (getView().getGameContol().getButton()[j].getStyleClass().hashCode() != 1986151201)
                            getView().getGameContol().getButton()[j].getStyleClass().add("btn-selected");
                        IAchoice = 3;
                    }
                }
            });
        }
    }

    @Override
    public void onKeyTouche(int i) {
        this.getM().getField().setNionPoney(i);
    }

    public void onKeyTouche(int i, boolean b) {
        this.getM().getField().jumpPoney(i, true);
    }

    void touchHandler(Scene scene) {
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        System.out.println("pressed key");
                        String code = e.getCode().toString();
                        //System.out.println(code);
                        // only add once... prevent duplicates
                        if (!input.contains(code))
                            input.add(code);

                        if (input.contains("B")) {
                            onKeyTouche(0);
                        }

                        if (input.contains("G")) {
                            onKeyTouche(1);
                        }

                        if (input.contains("O")) {
                            onKeyTouche(2);
                        }

                        if (input.contains("P")) {
                            onKeyTouche(3);
                        }

                        if (input.contains("Y")) {
                            onKeyTouche(4);
                        }

                        if (input.contains("NUMPAD1")) {
                            onKeyTouche(0, true);
                        }

                        if (input.contains("NUMPAD2")) {
                            onKeyTouche(1, true);
                        }

                        if (input.contains("NUMPAD3")) {
                            onKeyTouche(2, true);
                        }

                        if (input.contains("NUMPAD4")) {
                            onKeyTouche(3, true);
                        }

                        if (input.contains("NUMPAD5")) {
                            onKeyTouche(4, true);
                        }
                    }
                }
        );

        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code;
                        code = e.getCode().toString();
                        input.remove(code);
                    }
                }
        );


    }

    void timeHandler(int width, int height) {
        PoneymonView v = super.getView();
        super.getM().getField().addObserver(v);
        timer = new Timeline();
        timer.getKeyFrames().add(new KeyFrame(Duration.seconds(2), (ActionEvent e) -> {
            getM().getField().addSpeed(IAchoice);

        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
        {
            animationTimer = new AnimationTimer() {
                @Override
                public void handle(long currentNanoTime) {
                    v.getField().getGc().setFill(Color.rgb(250, 250, 250));
                    v.getField().getGc().fillRect(0, 0, width, height);
                    getM().getField().Poneymove();
                    getM().getField().Obstaclemove();
                    getM().getField().jumpPoneyIA();
                    if (getM().getField().endGame()) {
                        stopGame();
                    }
                }
            };
        }
    }

    void timeScoreHandler() {
        ScoreView v = super.getView().viewscore;
        {
            animationTimer = new AnimationTimer() {
                @Override
                public void handle(long currentNanoTime) {
                    v.getGc().setFill(Color.rgb(250, 250, 250));
                    //v.getGc().fillRect(0, 0, width, height);
                    getM().getField().Poneymove();
                    getM().getField().Obstaclemove();
                    getM().getField().jumpPoneyIA();
                    if (getM().getField().endGame()) {
                        stopGame();
                    }
                }
            };
        }
    }

    public void stopGame() {
        System.out.println("entered");
        timer.stop();
        animationTimer.stop();
        System.out.println(getM().getField().displayRank(getM().getField().getRank()));
        getView().viewScore();
        getView().viewscore.rank = new Vector(getM().getField().getRank());
        getView().viewscore.displayPoneys();
        for (int i = 0; i < 6; i++) {
            getView().root.getChildren().add(getView().viewscore.text[i]);
        }
    }

    public AnimationTimer getScoretime() {
        return scoretime;
    }

    public void setScoretime(AnimationTimer scoretime) {
        this.scoretime = scoretime;
    }
}
