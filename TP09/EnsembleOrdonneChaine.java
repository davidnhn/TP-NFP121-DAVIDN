public class EnsembleOrdonneChaine<T extends Comparable<T>> implements EnsembleOrdonne<T>{
    private Cellule<T> premier;
    private int taille;

    @Override
    public int cardinal() {
        return taille;
    }

    @Override
    public boolean estVide() {
        return taille==0;
    }

    @Override
    public boolean contient(T x) {
        Cellule<T> current = premier;
        while (current != null) {
            if(current.valeur.equals(x)) {
                return true;
            }
            if(current.valeur.compareTo(x) >0  ) { // equivalent à current.valeur > x , difference entre current.valeur et x , current.valeur - x > 0 equivaut a current.valeur > x
                return false; // Les éléments suivants sont plus grands, inutile de continuer
            }
            current = current.suivant;
        }
        return false;
    }

    @Override
    public void ajouter(T x) {
        if(contient(x)) {
            return;
        }

        Cellule<T> nouvelleCellule = new Cellule<>(x);

        if(premier == null || premier.valeur.compareTo(x)>0) {
            // Inserer au début
            nouvelleCellule.suivant = premier;
            premier = nouvelleCellule;
        } else  {
            // Trouver la position où inserer pour maintenir l'ordre
            Cellule<T> current = premier;
            while (current.suivant != null && current.suivant.valeur.compareTo(x) < 0) {
                current = current.suivant;
            }
            nouvelleCellule.suivant = premier;
            premier = nouvelleCellule;
        }

        taille++;
    }

    @Override
    public void supprimer(T x) {
        Cellule<T> current = premier;
        Cellule<T> previous = null;

        while (current != null) {
            if(current.valeur.equals(x)) {
                if(previous == null) {
                    // supprimer le premier élément
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

    @Override
    public T trouverMin() throws IllegalStateException {
        if (estVide()) {
            throw new IllegalStateException("L'ensemble est vide.");
        }
        return premier.valeur;
    }

    @Override
    public T justePlusGrandQue(T x) {
        Cellule<T> current = premier;
        T result = null;

        while (current != null) {
            if (current.valeur.compareTo(x) > 0) {
                if(result == null || current.valeur.compareTo(result) < 0) {
                    result = current.valeur;
                }
            }
            current = current.suivant;
        }
    }
}
