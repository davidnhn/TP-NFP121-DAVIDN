import afficheur.Afficheur;
import afficheur.Ecran;

public abstract class ElementGraphique {


    /** Méthode pour afficher l'élément graphique.
     * Chaque classe dérivée doit fournir une implémentation.
     */
    public abstract void afficher();

    /** Méthode pour translater l'élément graphique.
     * Chaque classe dérivée doit fournir une implémentation.
     * @param dx déplacement en X
     * @param dy déplacement en Y
     */
    public abstract void translater(double dx, double dy);

    public abstract void dessiner(Afficheur afficheur);
}
