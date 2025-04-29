package allumettes;

/**
 * Implémentation simple du jeu des 13 allumettes.
 */
public class JeuSimple implements Jeu {

    /** Nombre d'allumettes restantes. */
    private int nbAllumettes;

    /**
     * Constructeur d'un jeu avec nbAllumettes initiales.
     * @param nbAllumettes nombre initial d'allumettes
     */
    public JeuSimple(int nbAllumettes) {
        this.nbAllumettes = nbAllumettes;
    }

    @Override
    public int getNombreAllumettes() {
        return this.nbAllumettes;
    }

    @Override
    public void retirer(int nbPrises) throws CoupInvalideException {
        if (nbPrises < 1) {
            throw new CoupInvalideException(nbPrises, "< 1");
        }
        if (nbPrises > this.nbAllumettes) {
            throw new CoupInvalideException(nbPrises, "> " + this.nbAllumettes);
        }
        if (nbPrises > PRISE_MAX) {
            throw new CoupInvalideException(nbPrises, "> " + PRISE_MAX);
        }
        this.nbAllumettes -= nbPrises;
    }

} 