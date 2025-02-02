/**
 * Classe représentant un ensemble d'entiers basé sur une structure chaînée
 */
public class EnsembleChaine<T> implements Ensemble<T> {
    private Cellule<T> premier; // Premier élément de la chaîne
    private int taille; // Nombre d'éléments dans l'ensemble

    public EnsembleChaine() {
        this.premier = null;
        this.taille = 0;
    }

    @Override
    public int cardinal() {
        return taille;
    }

    @Override
    public boolean estVide() {
        return taille == 0;
    }

    @Override
    public boolean contient(T x) {
        Cellule<T> current = premier;
        while (current != null) {
            if(current.valeur.equals(x)) {
                return true;
            }
            current = current.suivant;
        }
        return false;
    }

    @Override
    public void ajouter(T x) {
        if(!contient(x)) { // Vérifie si l'entier n'est pas déjà dans l'ensemble
            Cellule<T> nouvelleCellule = new Cellule<>(x); // Crée une nouvelle cellule avec la valeur x
            nouvelleCellule.suivant = premier;  // La nouvelle cellule poTe vers l'ancienne tête de la chaîne
            premier = nouvelleCellule; // La nouvelle cellule devient la tête de la chaîne
            taille++;
        }

    }

    @Override
    public void supprimer(T x) {
        Cellule<T> current = premier;
        Cellule<T> previous = null;

        while(current != null) {
            if(current.valeur.equals(x)) {
                if(previous == null) {
                    premier = current.suivant;
                } else {
                    previous.suivant = current.suivant;
                }
                taille--;
                return;
            }
            previous = current;
            current = current.suivant;
        }
    }
}
