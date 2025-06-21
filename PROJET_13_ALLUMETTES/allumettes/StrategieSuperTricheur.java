package allumettes;

import java.lang.reflect.Field;

/**
 * Stratégie de jeu qui permet de tricher même en mode non confiant.
 * Cette stratégie utilise la réflexion Java pour accéder aux champs privés.
 */
public class StrategieSuperTricheur implements Strategie {

    @Override
    public int getPrise(Jeu jeu) {
        System.out.println("[Je super triche...]");
        
        try {
            Jeu jeuReel = jeu;
            if (jeu instanceof Procuration) {
                Field champJeuReel = Procuration.class.getDeclaredField("jeuReel");
                champJeuReel.setAccessible(true);
                jeuReel = (Jeu) champJeuReel.get(jeu);
            }
            
            int nbAllumettes = jeuReel.getNombreAllumettes();
            
            if (jeuReel instanceof JeuSimple) {
                Field champNbAllumettes = JeuSimple.class.getDeclaredField("nbAllumettes");
                champNbAllumettes.setAccessible(true);
                
                if (nbAllumettes > 2) {
                    champNbAllumettes.set(jeuReel, 2);
                    System.out.println("[Allumettes restantes : 2]");
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException | SecurityException | ClassCastException e) {
            System.out.println("[La super triche a échoué]");
        }
        
        return 1;
    }

} 