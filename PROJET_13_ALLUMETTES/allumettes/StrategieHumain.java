package allumettes;

import java.util.Scanner;

/**
 * Stratégie de jeu permettant à un joueur humain de jouer à travers la console.
 */
public class StrategieHumain implements Strategie {

    private static Scanner scanner;

    /**
     * Constructeur de la stratégie humain.
     * @param scanner le scanner à utiliser pour l'interaction avec l'utilisateur
     */
    public StrategieHumain(Scanner scanner) {
        StrategieHumain.scanner = scanner;
    }

    @Override
    public int getPrise(Jeu jeu) {
        int choix = 0;
        boolean choixValide = false;

        while (!choixValide) {
            try {
                System.out.print(System.getProperty("joueur.courant") + ", combien d'allumettes ? ");

                String entree = scanner.nextLine();
                
                if (entree.trim().toLowerCase().equals("triche")) {
                    System.out.println("[Une allumette en moins, plus que " + 
                            (jeu.getNombreAllumettes() - 1) + ". Chut !]");
                    
                    try {
                        jeu.retirer(1);
                    } catch (CoupInvalideException | OperationInterditeException e) {
                    }
                    continue;
                }
                
                choix = Integer.parseInt(entree);
                choixValide = true;
            } catch (NumberFormatException e) {
                System.out.println("Vous devez donner un entier.");
            }
        }

        return choix;
    }

} 