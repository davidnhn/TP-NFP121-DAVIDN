
import afficheur.Ecran;
import java.awt.Color;

/**
  * Exemple d'utilisation de la classe Ecran.
  */
class ExempleEcran {

	public static void main(String[] args) {
		// Construire un écran
		Ecran ecran = new Ecran("ExempleEcran", 400, 250, 15);

		// Dessin des axes
		ecran.dessinerAxes();

		// Dessin d'un point vert à la position (1 ,2)
		ecran.dessinerPoint(1, 2, Color.GREEN);

		// Dessin d'une ligne rouge entre les points (6, 2) et (11, 9);
		ecran.dessinerLigne(6,2,11,9,Color.RED);

		// Dessin d'un d'un cercle jaune de centre (4, 4) et de rayon 2,5
		ecran.dessinerCercle(4,4,2.5, Color.YELLOW);

		// Dessin du texte "Pemier dessin" en bleu à la position (1, -2)
		ecran.dessinerTexte(1, -2, "Premier dessin", Color.BLUE);
	}

}
