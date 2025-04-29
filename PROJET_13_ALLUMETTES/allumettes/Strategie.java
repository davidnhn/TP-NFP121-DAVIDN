package allumettes;

/**
 * Interface définissant une stratégie de jeu pour le jeu des 13 allumettes.
 */
public interface Strategie {

    /**
     * Obtenir le nombre d'allumettes que le joueur veut prendre.
     * @param jeu le jeu en cours
     * @return le nombre d'allumettes à prendre
     */
    int getPrise(Jeu jeu);

} 