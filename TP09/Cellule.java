/**
 * Classe représentant une cellule d'une structure chaînée.
 */
public class Cellule<T> {
    T valeur;
    Cellule<T> suivant;

    /**
     * Constructeur de Cellule
     * @param valeur La valeur de la cellule.
     */
    public Cellule(T valeur) {
        this.valeur = valeur;
        this.suivant = null;
    }


}
