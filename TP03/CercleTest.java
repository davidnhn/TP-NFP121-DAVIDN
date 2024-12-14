import org.junit.*;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Classe de tests unitaires pour la classe Cercle.
 * Elle vérifie les différents constructeurs et la méthode creerCercle.
 */
public class CercleTest {

    /**
     * Teste le constructeur de Cercle avec deux points diamétralement opposés.
     */
    @Test
    public void testConstructeurDeuxPoints() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 0);
        Cercle c = new Cercle(p1, p2);

        assertEquals(2.0, c.getCentre().getX(), 0.001);
        assertEquals(0.0, c.getCentre().getY(), 0.001);
        assertEquals(2.0, c.getRayon(), 0.001);
    }

    /**
     * Teste le constructeur de Cercle avec deux points identiques, ce qui doit lever une AssertionError.
     */
    @Test(expected = AssertionError.class)
    public void testContructeurDeuxPointsIdentiques() {
        new Cercle(new Point(1, 2), new Point(1, 2));
    }

    /**
     * Teste le constructeur de Cercle avec deux points diamétralement opposés et une couleur.
     */
    @Test
    public void testConstructorDeuxPointsCouleur() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 0);
        Cercle c = new Cercle(p1, p2, Color.red);
        assertEquals(Color.red, c.getCouleur());
    }

    /**
     * Teste le constructeur de Cercle avec une couleur nulle, ce qui doit lever une AssertionError.
     */
    @Test(expected = AssertionError.class)
    public void testConstructorDeuxPointsCouleureEstNull() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 0);
        Cercle c = new Cercle(p1, p2, null);
    }

    /**
     * Teste la méthode creerCercle pour créer un cercle à partir d'un centre et d'un point sur la circonférence.
     */
    @Test
    public void testMethodeCreerCercle() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 0);
        Cercle c = Cercle.creerCercle(p1, p2);

        assertEquals(0.0, c.getCentre().getX(), 0.001);
        assertEquals(0.0, c.getCentre().getY(), 0.001);
        assertEquals(4.0, c.getRayon(), 0.001);
    }

    /**
     * Teste la méthode creerCercle avec un centre nul, ce qui doit lever une AssertionError.
     */
    @Test(expected = AssertionError.class)
    public void testMethodeCreerCercleAvecCentreEstNull() {
        Point p2 = new Point(4, 0);
        Cercle c = Cercle.creerCercle(null, p2);
    }

    /**
     * Teste la méthode creerCercle avec un point sur la circonférence nul, ce qui doit lever une AssertionError.
     */
    @Test(expected = AssertionError.class)
    public void testMethodeCreerCercleAvecPointSurCirconferenceEstNull() {
        Point p1 = new Point(4, 0);
        Cercle c = Cercle.creerCercle(p1, null);
    }
}
