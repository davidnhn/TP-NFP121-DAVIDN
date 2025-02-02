import afficheur.Ecran;

import java.util.ArrayList;
import java.util.List;

/** Construire le schéma proposé dans le sujet de TP avec des points,
  * des points nommés
  * et des segments.
  *
  * @author	Xavier Crégut
  * @version	$Revision: 1.7 $
  */
public class ExempleSchemaTab {

	/** Construire le schéma et le manipuler.
	  * Le schéma est affiché.
	  * Ensuite, il est translaté et affiché de nouveau.
	  * @param args les arguments de la ligne de commande
	  */
	public static void main(String[] args)
	{
		// Créer les trois segments
		Point p1 = new PointNomme(3, 2, "A");
		Point p2 = new PointNomme(6, 9, "S");
		Point p3 = new Point(11, 4);
		Segment s12 = new Segment(p1, p2);
		Segment s23 = new Segment(p2, p3);
		Segment s31 = new Segment(p3, p1);

		// Créer le barycentre
		double sx = p1.getX() + p2.getX() + p3.getX();
		double sy = p1.getY() + p2.getY() + p3.getY();
		Point barycentre = new PointNomme(sx / 3, sy / 3, "C");

		// Définir le schéma (vide)
		List<ElementGraphique> schema = new ArrayList<>();    // le schéma
			// 10 : capacité suffisante ici, non contrôlée dans la suite.
		int nb = 0;		// Le nombre d'éléments dans le schéma

		// Peupler le schéma
		schema.add(s12);
		schema.add(s23);
		schema.add(s31);
		schema.add(barycentre);

		// Afficher le schéma
		afficherSchema(schema);

		// Créer l'écran d'affichage
		Ecran ecran = new Ecran("ExempleSchemaListe", 600, 400, 20);
		ecran.dessinerAxes();

		// Dessiner les éléments du schéma
		dessinerSchema(schema, ecran);

		// Translater le schéma
		System.out.println("Translater le schéma de (4, -3) : ");
		for (ElementGraphique eg : schema) {
			eg.translater(4, -3);
		}

		// Afficher et dessiner après translation
		afficherSchema(schema);
		dessinerSchema(schema, ecran);

		// Forcer l'affichage du schéma (au cas où...)
		ecran.rafraichir();
	}


	// Méthode pour afficher tous les éléments d'un schéma
	private static void afficherSchema(List<ElementGraphique> schema) {
		System.out.println("Le schéma est composé de : ");
		for (ElementGraphique eg : schema) {
			eg.afficher();
			System.out.println();
		}
	}

	// Méthode pour dessiner tous les éléments d'un schéma
	private static void dessinerSchema(List<ElementGraphique> schema, Ecran ecran) {
		for (ElementGraphique eg : schema) {
			eg.dessiner(ecran);
		}
	}


}
