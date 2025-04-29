package allumettes;

import java.util.Scanner;
import javax.swing.SwingUtilities;
import java.awt.Frame;

/** Lance une partie des 13 allumettes en fonction des arguments fournis
 * sur la ligne de commande.
 * @author	Xavier Crégut
 * @version	$Revision: 1.5 $
 */
public class Jouer {

	/** Nombre d'allumettes au début du jeu. */
	private static final int NB_ALLUMETTES_INITIAL = 13;
	
	/** Indique si l'application doit se terminer après la partie. */
	private static boolean doitTerminer = false;

	/** Lancer une partie. En argument sont donnés les deux joueurs sous
	 * la forme nom@stratégie.
	 * @param args la description des deux joueurs
	 */
	public static void main(String[] args) {
		try {
			verifierNombreArguments(args);

			boolean confiant = false;
			String[] parametresJoueurs;
			
			if (args[0].equals("-confiant")) {
				confiant = true;
				parametresJoueurs = new String[]{args[1], args[2]};
			} else {
				parametresJoueurs = new String[]{args[0], args[1]};
			}
			
			Jeu jeu = new JeuSimple(NB_ALLUMETTES_INITIAL);
			
			Scanner scanner = new Scanner(System.in);
			
			Joueur joueur1 = creerJoueur(parametresJoueurs[0], scanner);
			Joueur joueur2 = creerJoueur(parametresJoueurs[1], scanner);
			
			Arbitre arbitre = new Arbitre(joueur1, joueur2, confiant);
			
			creerDTD();
			
			arbitre.arbitrer(jeu);

			SwingUtilities.invokeLater(() -> {
    		Frame[] frames = Frame.getFrames();
    	for (Frame frame : frames) {
        if (frame.isDisplayable()) {
            frame.dispose();
        }
    }
});

			
			scanner.close();
			
			if (doitTerminer) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
			}

		} catch (ConfigurationException e) {
			System.out.println();
			System.out.println("Erreur : " + e.getMessage());
			afficherUsage();
			System.exit(1);
		}
	}

	/**
	 * Indiquer que l'application doit se terminer.
	 * Cette méthode est utilisée par les stratégies Swing pour indiquer
	 * que le programme principal doit se terminer.
	 */
	public static void terminer() {
		doitTerminer = true;
	}

	private static void verifierNombreArguments(String[] args) {
		final int nbJoueurs = 2;
		if (args.length < nbJoueurs) {
			throw new ConfigurationException("Trop peu d'arguments : "
					+ args.length);
		}
		if (args.length > nbJoueurs + 1) {
			throw new ConfigurationException("Trop d'arguments : "
					+ args.length);
		}
	}

	/** Afficher des indications sur la manière d'exécuter cette classe. */
	public static void afficherUsage() {
		System.out.println("\n" + "Usage :"
				+ "\n\t" + "java allumettes.Jouer joueur1 joueur2"
				+ "\n\t\t" + "joueur est de la forme nom@stratégie"
				+ "\n\t\t" + "strategie = naif | rapide | expert | humain | tricheur | swing | supertricheur"
				+ "\n"
				+ "\n\t" + "Exemple :"
				+ "\n\t" + "	java allumettes.Jouer Xavier@humain "
					   + "Ordinateur@naif"
				+ "\n"
				);
	}
	
	/**
	 * Créer un joueur à partir de son nom et de sa stratégie.
	 * @param parametreJoueur le paramètre du joueur de la forme nom@stratégie
	 * @param scanner le scanner pour l'entrée standard
	 * @return le joueur créé
	 */
	private static Joueur creerJoueur(String parametreJoueur, Scanner scanner) {
		String[] parties = parametreJoueur.split("@");
		if (parties.length != 2) {
			throw new ConfigurationException("Format invalide pour " + parametreJoueur);
		}
		
		String nom = parties[0];
		String nomStrategie = parties[1];
		Strategie strategie = creerStrategie(nomStrategie, scanner);
		
		return new Joueur(nom, strategie);
	}
	
	/**
	 * Créer une stratégie à partir de son nom.
	 * @param nomStrategie le nom de la stratégie
	 * @param scanner le scanner pour l'entrée standard
	 * @return la stratégie créée
	 */
	private static Strategie creerStrategie(String nomStrategie, Scanner scanner) {
		switch (nomStrategie.toLowerCase()) {
			case "humain":
				return new StrategieHumain(scanner);
			case "naif":
				return new StrategieNaif();
			case "rapide":
				return new StrategieRapide();
			case "expert":
				return new StrategieExpert();
			case "tricheur":
				return new StrategieTricheur();
			case "swing":
				return new StrategieSwing();
			case "supertricheur":
				return new StrategieSuperTricheur();
			default:
				throw new ConfigurationException("Stratégie inconnue : " + nomStrategie);
		}
	}
	
	/**
	 * Créer le fichier DTD pour le document XML.
	 */
	private static void creerDTD() {
		try {
			java.io.FileWriter dtdWriter = new java.io.FileWriter("deroulement.dtd");
			dtdWriter.write("<!ELEMENT partie (coup*, vainqueur?, tricheur?)>\n");
			dtdWriter.write("<!ELEMENT coup (joueur, prise)>\n");
			dtdWriter.write("<!ATTLIST coup numero CDATA #REQUIRED>\n");
			dtdWriter.write("<!ELEMENT joueur (#PCDATA)>\n");
			dtdWriter.write("<!ELEMENT prise (#PCDATA)>\n");
			dtdWriter.write("<!ELEMENT vainqueur (#PCDATA)>\n");
			dtdWriter.write("<!ELEMENT tricheur (#PCDATA)>\n");
			dtdWriter.close();
		} catch (java.io.IOException e) {
			System.err.println("Erreur lors de la création du fichier DTD : " + e.getMessage());
		}
	}

}
