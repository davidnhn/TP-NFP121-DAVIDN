package allumettes;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Arbitre du jeu des 13 allumettes.
 */
public class Arbitre {

    /** Premier joueur. */
    private Joueur joueur1;
    
    /** Second joueur. */
    private Joueur joueur2;
    
    /** Mode confiant ou non. */
    private boolean confiant;
    
    /** Writer pour générer le fichier XML. */
    private FileWriter xmlWriter;
    
    /** Numéro du coup en cours. */
    private int numeroCoup;

    /**
     * Constructeur de l'arbitre en mode non confiant par défaut.
     * @param joueur1 le premier joueur
     * @param joueur2 le second joueur
     */
    public Arbitre(Joueur joueur1, Joueur joueur2) {
        this(joueur1, joueur2, false);
    }

    /**
     * Constructeur de l'arbitre.
     * @param joueur1 le premier joueur
     * @param joueur2 le second joueur
     * @param confiant vrai si l'arbitre est confiant, faux sinon
     */
    public Arbitre(Joueur joueur1, Joueur joueur2, boolean confiant) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.confiant = confiant;
        this.numeroCoup = 1;
        
        try {
            this.xmlWriter = new FileWriter("deroulement.xml");
            this.xmlWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            this.xmlWriter.write("<!DOCTYPE partie SYSTEM \"deroulement.dtd\">\n");
            this.xmlWriter.write("<partie>\n");
        } catch (IOException e) {
            System.err.println("Erreur lors de la création du fichier XML : " + e.getMessage());
        }
    }

    /**
     * Arbitrer une partie entre les deux joueurs.
     * @param jeu le jeu à arbitrer
     */
    public void arbitrer(Jeu jeu) {
        Joueur joueurCourant = this.joueur1;
        
        try {
            while (jeu.getNombreAllumettes() > 0) {
                System.out.println("Allumettes restantes : " + jeu.getNombreAllumettes());
                
                Jeu jeuPresente = confiant ? jeu : new Procuration(jeu);
                joueurCourant = jouerTour(joueurCourant, jeuPresente, jeu);
                
                System.out.println();
            }
            
            Joueur perdant = (joueurCourant == joueur1) ? joueur2 : joueur1;
            Joueur gagnant = (joueurCourant == joueur1) ? joueur1 : joueur2;
            
            System.out.println(perdant.getNom() + " perd !");
            System.out.println(gagnant.getNom() + " gagne !");
            
            ecrireXML("vainqueur", gagnant.getNom());
            this.xmlWriter.write("</partie>\n");
            this.xmlWriter.close();
            
        } catch (OperationInterditeException e) {
            System.out.println("Abandon de la partie car " + joueurCourant.getNom() + " triche !");
            
            try {
                ecrireXML("tricheur", joueurCourant.getNom());
                this.xmlWriter.write("</partie>\n");
                this.xmlWriter.close();
            } catch (IOException ex) {
                System.err.println("Erreur lors de l'écriture du fichier XML : " + ex.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier XML : " + e.getMessage());
        }
    }
    
    /**
     * Faire jouer un tour à un joueur.
     * @param joueur le joueur qui doit jouer
     * @param jeuPresente le jeu présenté au joueur (peut être une procuration)
     * @param jeuReel le jeu réel pour retirer les allumettes
     * @return le prochain joueur
     * @throws OperationInterditeException si le joueur triche
     */
    private Joueur jouerTour(Joueur joueur, Jeu jeuPresente, Jeu jeuReel) throws IOException {
        boolean coupValide = false;
        
        while (!coupValide) {
            try {
                if (joueur.getNom().toLowerCase().contains("humain")) {
                    System.out.print(joueur.getNom() + ", combien d'allumettes ? ");
                }
                
                System.setProperty("joueur.courant", joueur.getNom());
                
                int nbPrises = joueur.getPrise(jeuPresente);
                
                String s = (nbPrises > 1) ? "s" : "";
                System.out.println(joueur.getNom() + " prend " + nbPrises + " allumette" + s + ".");
                
                jeuReel.retirer(nbPrises);
                
                ecrireXML("coup", joueur.getNom(), nbPrises);
                numeroCoup++;
                
                coupValide = true;
            } catch (CoupInvalideException e) {
                System.out.println("Impossible ! Nombre invalide : " + e.getCoup() +
                        " (" + e.getProbleme() + ")");
                System.out.println();
                
                System.out.println("Allumettes restantes : " + jeuReel.getNombreAllumettes());
            }
        }
        
        return (joueur == joueur1) ? joueur2 : joueur1;
    }
    
    /**
     * Écrire une information sur le coup joué dans le fichier XML.
     * @param type le type d'information (coup, vainqueur, tricheur)
     * @param nom le nom du joueur
     * @throws IOException en cas d'erreur d'écriture
     */
    private void ecrireXML(String type, String nom) throws IOException {
        this.xmlWriter.write("  <" + type + ">" + nom + "</" + type + ">\n");
    }
    
    /**
     * Écrire une information sur le coup joué dans le fichier XML.
     * @param type le type d'information (coup)
     * @param nom le nom du joueur
     * @param nbPrises le nombre d'allumettes prises
     * @throws IOException en cas d'erreur d'écriture
     */
    private void ecrireXML(String type, String nom, int nbPrises) throws IOException {
        this.xmlWriter.write("  <" + type + " numero=\"" + numeroCoup + "\">\n");
        this.xmlWriter.write("    <joueur>" + nom + "</joueur>\n");
        this.xmlWriter.write("    <prise>" + nbPrises + "</prise>\n");
        this.xmlWriter.write("  </" + type + ">\n");
    }

} 