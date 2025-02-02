import afficheur.Afficheur;

import java.util.ArrayList;
import java.util.List;

public class Groupe extends ElementGraphique{
    private List<ElementGraphique> elements;

    public Groupe() {
        this.elements = new ArrayList<>();
    }

    public void ajouter(ElementGraphique element) {
        this.elements.add(element);
    }

    @Override
    public void afficher() {
        System.out.println("Group :");
        for(ElementGraphique element : elements) {
            element.afficher();
        }
    }

    @Override
    public  void translater(double dx, double dy) {
        for(ElementGraphique element : elements) {
            element.translater(dx, dy);
        }
    }

    @Override
    public void dessiner(Afficheur afficheur) {
        for (ElementGraphique element : elements) {
            element.dessiner(afficheur);
        }
    }
}
