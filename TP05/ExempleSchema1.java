import afficheur.AfficheurSVG;
import afficheur.Ecran;

import java.awt.*;

/** Construire le schéma proposé dans le sujet de TP avec des points,
  * et des segments.
  *
  * @author	Xavier Crégut
  * @version	$Revision: 1.7 $
  */
public class ExempleSchema1 {

	/** Construire le schéma et le manipuler.
	  * Le schéma est affiché.
	  * @param args les arguments de la ligne de commande
	  */
	public static void main(String[] args)
	{
		Ecran ecran = new Ecran("ExempleSchema1", 600, 400, 20);
		// Créer les trois segments
		Point p1 = new Point(3, 2);
		Point p2 = new Point(6, 9);
		Point p3 = new Point(11, 4);
		Segment s12 = new Segment(p1, p2);
		Segment s23 = new Segment(p2, p3);
		Segment s31 = new Segment(p3, p1);

		ecran.dessinerAxes();

		s12.dessiner(ecran);
		s23.dessiner(ecran);
		s31.dessiner(ecran);

		// Créer le barycentre
		double sx = p1.getX() + p2.getX() + p3.getX();
		double sy = p1.getY() + p2.getY() + p3.getY();
		Point barycentre = new Point(sx / 3, sy / 3);

		barycentre.dessiner(ecran);

		AfficheurSVG afficheurSVG = new AfficheurSVG();
		s12.dessiner(afficheurSVG);
		s23.dessiner(afficheurSVG);
		s31.dessiner(afficheurSVG);

		barycentre.dessiner(afficheurSVG);
		afficheurSVG.ecrire("schema.svg");


		AfficheurTexte afficheurTexte = new AfficheurTexte();
		s12.dessiner(afficheurTexte);
		s23.dessiner(afficheurTexte);
		s31.dessiner(afficheurTexte);

		barycentre.dessiner(afficheurTexte);


		double dx = 4;
		double dy = -3;

		s12.translater(dx, dy);
		s23.translater(dx, dy);
		s31.translater(dx, dy);

		barycentre.translater(dx, dy);

		s12.dessiner(ecran);
		s23.dessiner(ecran);
		s31.dessiner(ecran);
		barycentre.dessiner(ecran);





		// Afficher le schéma
		System.out.println("Le schéma est composé de : ");
		s12.afficher();		System.out.println();
		s23.afficher();		System.out.println();
		s31.afficher();		System.out.println();
		barycentre.afficher();	System.out.println();
	}

}
