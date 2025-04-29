package allumettes;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests unitaires de la stratégie Rapide.
 */
public class StrategieRapideTest {

    /**
     * Test de la stratégie rapide avec différents nombres d'allumettes.
     */
    @Test
    public void testGetPrise() {
        StrategieRapide strategie = new StrategieRapide();
        
        Jeu jeuMock = new JeuMock(13);
        assertEquals(3, strategie.getPrise(jeuMock));
        
        jeuMock = new JeuMock(3);
        assertEquals(3, strategie.getPrise(jeuMock));
        
        jeuMock = new JeuMock(2);
        assertEquals(2, strategie.getPrise(jeuMock));
        
        jeuMock = new JeuMock(1);
        assertEquals(1, strategie.getPrise(jeuMock));
    }
    
    /**
     * Classe mock pour les tests.
     */
    private static class JeuMock implements Jeu {
        private int nbAllumettes;
        
        public JeuMock(int nbAllumettes) {
            this.nbAllumettes = nbAllumettes;
        }
        
        @Override
        public int getNombreAllumettes() {
            return nbAllumettes;
        }
        
        @Override
        public void retirer(int nbPrises) throws CoupInvalideException {
        }
    }
} 