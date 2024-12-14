import org.junit.*;
import static org.junit.Assert.*;

/**
 * Classe de tests unitaires pour les fonctionnalités complémentaires de la classe Cercle.
 * Elle teste le déplacement, la gestion du diamètre invalide, l'appartenance de points au cercle,
 * et la représentation textuelle du cercle.
 */
public class ComplementsCercleTest {

    /**
     * Teste la méthode translater pour vérifier que le centre du cercle est correctement déplacé.
     */
    @Test
    public void testTranslater() {
        Cercle c = new Cercle(new Point(2, 2), 2);
        c.translater(2, 2);
        assertEquals(4.0, c.getCentre().getX(), 0.001);
        assertEquals(4.0, c.getCentre().getY(), 0.001);
    }

    /**
     * Teste la méthode setDiametre pour s'assurer qu'une AssertionError est levée avec un diamètre invalide.
     */
    @Test(expected = AssertionError.class)
    public void testSetDiametreInvalid() {
        Cercle c = new Cercle(new Point(0, 0), 5);
        c.setDiametre(-10);
    }

    /**
     * Teste la méthode contient pour vérifier qu'un point à l'intérieur du cercle est correctement identifié.
     */
    @Test
    public void testContietnPointInterieur() {
        Cercle c = new Cercle(new Point(0, 0), 5);
        Point p = new Point(3, 4);
        assertTrue(c.contient(p));
    }

    /**
     * Teste la méthode contient pour vérifier qu'un point à l'extérieur du cercle est correctement identifié.
     */
    @Test
    public void testContientPointExterieur() {
        Cercle c = new Cercle(new Point(0, 0), 5);
        Point p = new Point(6, 0);
        assertFalse(c.contient(p));
    }

    /**
     * Teste la méthode toString pour vérifier que la représentation textuelle du cercle est correcte.
     */
    @Test
    public void testToString() {
        Cercle c = new Cercle(new Point(1, 2), 3);
        assertEquals("C3.0@(1.0, 2.0)", c.toString());
    }
}
