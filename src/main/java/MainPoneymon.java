import Controller.*;
import Modele.Modele;
import View.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainPoneymon extends Application {

    @Override
    public void start(Stage primaryStage) {
        Modele m = new Modele(600,600,5,5);
        PoneymonView view = new PoneymonView(m, primaryStage);
        //Création du contrôleur
        Controleur controler = new ControleurPoneymon(m, view);


    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        launch();
    }
}
