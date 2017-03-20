package fr.univ_lyon1.info.m1.poneymon_mvn;


import Modele.ObstacleDeplace;
import Modele.Poney;
import Modele.PoneyAmeliore;
import Modele.PoneyIA;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    public AppTest() {

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void equalsCheck() {
        System.out.println("* PoneyssJUnit4Test: equalsCheck()");
        Poney P = new PoneyIA("blue", 100);
        ObstacleDeplace O = new ObstacleDeplace(100, 1.5);
        double X = 0;

        /* On vérifier que le Poney Avance toujours */
        for (int i = 0; i <= 500; i++) {
            X = P.getX();
            System.out.print("Poney Moved from " + X);
            P.move();
            System.out.println("To " + P.getX());
            if (X < 519) /* Car il repasse en négatif après 520 */
                assertTrue(P.getX() > X);
        }

        /* On vérifier que l'Obstacle recule toujours */
        for (int i = 0; i <= 500; i++) {
            X = O.getX();
            System.out.print("Obstacle Moved from " + X);
            O.move();
            System.out.println("To " + O.getX());
            if (X > -219) /* Car il repasse à 600 après négatif après 520 */
                assertTrue(O.getX() < X);
        }
    }

}
