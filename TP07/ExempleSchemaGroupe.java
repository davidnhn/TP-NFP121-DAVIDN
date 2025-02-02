import afficheur.Ecran;

public class ExempleSchemaGroupe {
    public static void main(String[] args) {
        // Créer les points et les segments
        Point p1 = new PointNomme(3, 2, "A");
        Point p2 = new PointNomme(6, 9, "S");
        Point p3 = new Point(11, 4);
        Segment s12 = new Segment(p1, p2);
        Segment s23 = new Segment(p2, p3);
        Segment s31 = new Segment(p3, p1);

        // Calculer le barycentre
        double sx = p1.getX() + p2.getX() + p3.getX();
        double sy = p1.getY() + p2.getY() + p3.getY();
        Point barycentre = new PointNomme(sx / 3, sy / 3, "C");

        // Créer un groupe contenant les segments
        Groupe groupeSegments = new Groupe();
        groupeSegments.ajouter(s12);
        groupeSegments.ajouter(s23);
        groupeSegments.ajouter(s31);

        // Créer un groupe contenant le groupe des segments et le barycentre
        Groupe schema = new Groupe();
        schema.ajouter(groupeSegments);
        schema.ajouter(barycentre);

        // Créer l'écran d'affichage
        Ecran ecran = new Ecran("ExempleSchemaGroupe", 600, 400, 20);
        ecran.dessinerAxes();

        // Afficher et dessiner le schéma
        schema.afficher();
        schema.dessiner(ecran);

        // Translater tout le schéma
        System.out.println("Translater le schéma de (4, -3) :");
        schema.translater(4, -3);

        // Afficher et dessiner le schéma après translation
        schema.afficher();
        schema.dessiner(ecran);

        // Forcer l'affichage
        ecran.rafraichir();
    }
}
