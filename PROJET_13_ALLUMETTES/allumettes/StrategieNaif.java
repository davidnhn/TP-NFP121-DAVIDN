package allumettes;

import java.util.Random;

/**
 * Stratégie de jeu qui consiste à prendre un nombre aléatoire d'allumettes.
 */
public class StrategieNaif implements Strategie {

    private final Random random;

    /**
     * Constructeur de la stratégie naïve.
     */
    public StrategieNaif() {
        this.random = new Random();
    }

    @Override
    public int getPrise(Jeu jeu) {
        int nbAllumettes = jeu.getNombreAllumettes();
        
        int maxPrise = Math.min(Jeu.PRISE_MAX, nbAllumettes);
        return random.nextInt(maxPrise) + 1;
    }

} 