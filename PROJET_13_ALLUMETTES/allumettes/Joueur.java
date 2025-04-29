package allumettes;

/**
 * Représente un joueur dans le jeu des 13 allumettes.
 */
public class Joueur {

    /** Le nom du joueur. */
    private String nom;
    
    /** La stratégie utilisée par le joueur. */
    private Strategie strategie;

    /**
     * Constructeur d'un joueur.
     * @param nom le nom du joueur
     * @param strategie la stratégie du joueur
     */
    public Joueur(String nom, Strategie strategie) {
        this.nom = nom;
        this.strategie = strategie;
    }

    /**
     * Obtenir le nom du joueur.
     * @return le nom du joueur
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Obtenir le nombre d'allumettes que le joueur veut prendre.
     * @param jeu le jeu en cours
     * @return le nombre d'allumettes à prendre
     */
    public int getPrise(Jeu jeu) {
        return this.strategie.getPrise(jeu);
    }
    
    /**
     * Changer la stratégie du joueur.
     * @param strategie la nouvelle stratégie
     */
    public void setStrategie(Strategie strategie) {
        this.strategie = strategie;
    }

} 