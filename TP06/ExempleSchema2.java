import afficheur.Ecran;

/** Construire le schéma proposé dans le sujet de TP avec des points,
  * et des segments.
  *
  * @author	Xavier Crégut
  * @version	$Revision: 1.7 $
  */
public class ExempleSchema2 {

	/** Construire le schéma et le manipuler.
	  * Le schéma est affiché.
	  * Ensuite, il est translaté et affiché de nouveau.
	  * @param args les arguments de la ligne de commande
	  */
	public static void main(String[] args)
	{
		// Créer les trois segments
		Point A = new Point(3, 2);
		Point S = new Point(6, 9);
		Point p3 = new Point(11, 4);
		Segment s12 = new Segment(A, S);
		Segment s23 = new Segment(S, p3);
		Segment s31 = new Segment(p3, A);

		// Créer le barycentre
		double sx = A.getX() + S.getX() + p3.getX();
		double sy = A.getY() + S.getY() + p3.getY();
		Point C = new Point(sx / 3, sy / 3);

		// Afficher le schéma
		System.out.println("Le schéma est composé de : ");
		s12.afficher();		System.out.println();
		s23.afficher();		System.out.println();
		s31.afficher();		System.out.println();
		C.afficher();	System.out.println();
		// Créer l'écran d'affichage
		Ecran ecran = new Ecran("ExempleSchema2", 600, 400, 20);
		ecran.dessinerAxes();

		// Dessiner le schéma sur l'écran graphique
		s12.dessiner(ecran);
		s23.dessiner(ecran);
		s31.dessiner(ecran);
		C.dessiner(ecran);


		// Forcer l'affichage du schéma (au cas où...)
		ecran.rafraichir();
	}

}
