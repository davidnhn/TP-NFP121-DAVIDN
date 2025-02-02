import afficheur.Ecran;

/** Construire le schÃ©ma proposÃ© dans le sujet de TP avec des points,
 * et des segments.
 *
 * @author	Xavier CrÃ©gut
 * @version	$Revision: 1.7 $
 */
public class ExempleSchema1 {

    /** Construire le schÃ©ma et le manipuler.
     * Le schÃ©ma est affichÃ©.
     * Ensuite, il est translatÃ© et affichÃ© de nouveau.
     * @param args les arguments de la ligne de commande
     */
    public static void main(String[] args)
    {
        // CrÃ©er les trois segments
        Point p1 = new Point(3, 2);
        Point p2 = new Point(6, 9);
        Point p3 = new Point(11, 4);
        Segment s12 = new Segment(p1, p2);
        Segment s23 = new Segment(p2, p3);
        Segment s31 = new Segment(p3, p1);

        // CrÃ©er le barycentre
        double sx = p1.getX() + p2.getX() + p3.getX();
        double sy = p1.getY() + p2.getY() + p3.getY();
        Point barycentre = new Point(sx / 3, sy / 3);

        // Afficher le schÃ©ma
        System.out.println("Le schÃ©ma est composÃ© de : ");
        s12.afficher();		System.out.println();
        s23.afficher();		System.out.println();
        s31.afficher();		System.out.println();
        barycentre.afficher();	System.out.println();
        // CrÃ©er l'Ã©cran d'affichage
        Ecran ecran = new Ecran("ExempleSchema1", 600, 400, 20);
        ecran.dessinerAxes();

        // Dessiner le schÃ©ma sur l'Ã©cran graphique
        s12.dessiner(ecran);
        s23.dessiner(ecran);
        s31.dessiner(ecran);
        barycentre.dessiner(ecran);

        // Translater le schÃ©ma
        System.out.println("Translater le schÃ©ma de (4, -3) : ");
        s12.translater(4, -3);
        s23.translater(4, -3);
        s31.translater(4, -3);
        barycentre.translater(4, -3);

        // Afficher le schÃ©ma
        System.out.println("Le schÃ©ma est composÃ© de : ");
        s12.afficher();		System.out.println();
        s23.afficher();		System.out.println();
        s31.afficher();		System.out.println();
        barycentre.afficher();	System.out.println();

        // Dessiner le schÃ©ma sur l'Ã©cran graphique
        s12.dessiner(ecran);
        s23.dessiner(ecran);
        s31.dessiner(ecran);
        barycentre.dessiner(ecran);

        // Forcer l'affichage du schÃ©ma (au cas oÃ¹...)
        ecran.rafraichir();
    }

}