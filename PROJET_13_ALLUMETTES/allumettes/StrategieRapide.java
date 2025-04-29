package allumettes;

/**
 * Stratégie de jeu qui consiste à prendre le maximum d'allumettes possible.
 */
public class StrategieRapide implements Strategie {

    @Override
    public int getPrise(Jeu jeu) {
        return Math.min(jeu.getNombreAllumettes(), Jeu.PRISE_MAX);
    }

} 