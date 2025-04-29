package allumettes;

/**
 * Stratégie de jeu qui consiste à jouer du mieux possible pour gagner.
 * La stratégie optimale quand celui qui prend la dernière allumette perd
 * est de laisser un nombre d'allumettes de la forme 4n+1.
 */
public class StrategieExpert implements Strategie {

    @Override
    public int getPrise(Jeu jeu) {
        int nbAllumettes = jeu.getNombreAllumettes();
        int prise;
        
        int cible = ((nbAllumettes - 1) / 4) * 4 + 1;
        
        if (nbAllumettes == cible) {
            prise = 1;
        } else {
            prise = nbAllumettes - cible;
            
            if (prise > Jeu.PRISE_MAX) {
                prise = 1;
            }
        }
        
        return prise;
    }

}