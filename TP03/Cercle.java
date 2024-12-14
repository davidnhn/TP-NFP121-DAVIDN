import java.awt.Color;

/**
 * La classe Cercle représente un cercle géométrique dans un plan 2D.
 * Elle permet de créer des cercles, de les déplacer, et de calculer leur périmètre et leur aire.
 */
public class Cercle implements Mesurable2D {
    private static final double PI = Math.PI;
    private Point centre;
    private double rayon;
    private Color couleur;

    /**
     * Constructeur pour créer un cercle avec un centre et un rayon.
     *
     * @param centre Le centre du cercle.
     * @param rayon  Le rayon du cercle (doit être positif).
     */
    public Cercle(Point centre, double rayon) {
        assert centre != null : "Le centre ne doit pas être nul";
        assert rayon > 0 : "Le rayon doit être positif";
        this.rayon = rayon;
        this.centre = centre;
        this.couleur = Color.BLUE;
    }

    /**
     * Constructeur pour créer un cercle à partir de deux points diamétralement opposés.
     *
     * @param p1 Le premier point.
     * @param p2 Le deuxième point.
     */
    public Cercle(Point p1, Point p2) {
        assert p1 != null : "Le point p1 ne doit pas être nul";
        assert p2 != null : "Le point p2 ne doit pas être nul";
        assert !(p1.getX() == p2.getX() && p1.getY() == p2.getY()) : "Les points p1 et p2 ne doivent pas être identiques";

        Point centre = new Point(
                (p1.getX() + p2.getX()) / 2,
                (p1.getY() + p2.getY()) / 2
        );
        double rayon = p1.distance(p2) / 2;

        this.centre = centre;
        this.rayon = rayon;
        this.couleur = Color.BLUE;
    }

    /**
     * Constructeur pour créer un cercle à partir de deux points diamétralement opposés et d'une couleur.
     *
     * @param p1      Le premier point.
     * @param p2      Le deuxième point.
     * @param couleur La couleur du cercle.
     */
    public Cercle(Point p1, Point p2, Color couleur) {
        this(p1, p2);
        assert couleur != null : "La couleur ne doit pas être nulle";
        this.couleur = couleur;
    }

    /**
     * Méthode statique pour créer un cercle à partir d'un centre et d'un point de la circonférence.
     *
     * @param centre                Le centre du cercle.
     * @param pointSurCirconference Un point situé sur la circonférence du cercle.
     * @return Un nouveau cercle avec le centre et le rayon calculé.
     */
    public static Cercle creerCercle(Point centre, Point pointSurCirconference) {
        assert centre != null : "Le centre ne doit pas être nul";
        assert pointSurCirconference != null : "Le point sur la circonférence ne doit pas être nul";
        return new Cercle(centre, centre.distance(pointSurCirconference));
    }

    /**
     * Retourne le centre du cercle.
     *
     * @return Le point représentant le centre du cercle.
     */
    public Point getCentre() {
        return centre;
    }

    /**
     * Retourne le rayon du cercle.
     *
     * @return Le rayon du cercle.
     */
    public double getRayon() {
        return rayon;
    }

    /**
     * Modifie le rayon du cercle.
     *
     * @param rayon Le nouveau rayon du cercle (doit être positif).
     */
    public void setRayon(double rayon) {
        assert rayon > 0 : "Le rayon doit être positif";
        this.rayon = rayon;
    }

    /**
     * Retourne le diamètre du cercle.
     *
     * @return Le diamètre du cercle.
     */
    public double getDiametre() {
        return 2 * rayon;
    }

    /**
     * Modifie le diamètre du cercle.
     *
     * @param diametre Le nouveau diamètre du cercle (doit être positif).
     */
    public void setDiametre(double diametre) {
        assert diametre > 0 : "Le diamètre doit être positif";
        rayon = diametre / 2;
    }

    /**
     * Retourne la couleur du cercle.
     *
     * @return La couleur du cercle.
     */
    public Color getCouleur() {
        return couleur;
    }

    /**
     * Modifie la couleur du cercle.
     *
     * @param couleur La nouvelle couleur du cercle.
     */
    public void setCouleur(Color couleur) {
        assert couleur != null : "La couleur ne doit pas être nulle";
        this.couleur = couleur;
    }

    /**
     * Déplace le cercle en appliquant une translation.
     *
     * @param dx Déplacement selon l'axe des X.
     * @param dy Déplacement selon l'axe des Y.
     */
    public void translater(double dx, double dy) {
        centre.translater(dx, dy);
    }

    /**
     * Retourne le périmètre du cercle.
     *
     * @return Le périmètre du cercle.
     */
    @Override
    public double perimetre() {
        return 2 * PI * rayon;
    }

    /**
     * Retourne l'aire du cercle.
     *
     * @return L'aire du cercle.
     */
    @Override
    public double aire() {
        return PI * Math.pow(rayon, 2);
    }

    /**
     * Vérifie si un point est à l'intérieur du cercle (ou sur sa circonférence).
     *
     * @param p Le point à vérifier.
     * @return {@code true} si le point est à l'intérieur ou sur le cercle, {@code false} sinon.
     */
    public boolean contient(Point p) {
        assert p != null : "Le point ne peut pas être nul";
        return centre.distance(p) <= rayon;
    }

    /**
     * Retourne une représentation textuelle du cercle.
     *
     * @return Une chaîne de caractères représentant le cercle sous la forme "C<rayon>@(<x>, <y>)".
     */
    @Override
    public String toString() {
        return "C" + this.rayon + "@(" + this.centre.getX() + ", " + this.centre.getY() + ")";
    }
}
