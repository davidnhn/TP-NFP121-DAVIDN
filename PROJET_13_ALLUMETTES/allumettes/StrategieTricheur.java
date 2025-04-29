package allumettes;

/**
 * Stratégie de jeu qui consiste à tricher en modifiant directement le jeu.
 */
public class StrategieTricheur implements Strategie {

    @Override
    public int getPrise(Jeu jeu) {
        System.out.println("[Je triche...]");
        
        try {
            int nbAllumettes = jeu.getNombreAllumettes();
            
            if (nbAllumettes > 2) {
                int totalARetirer = nbAllumettes - 2;
                
                while (totalARetirer > 0) {
                    int aRetirerMaintenant = Math.min(totalARetirer, Jeu.PRISE_MAX);
                    
                    jeu.retirer(aRetirerMaintenant);
                    
                    totalARetirer -= aRetirerMaintenant;
                }
                
                System.out.println("[Allumettes restantes : 2]");
            }
        } catch (CoupInvalideException e) {
        } catch (OperationInterditeException e) {
            throw e;
        }
        
        return 1;
    }

} 