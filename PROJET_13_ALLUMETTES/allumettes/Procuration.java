package allumettes;

/**
 * Procuration (Proxy) pour le jeu des 13 allumettes.
 * Cette classe sert à contrôler les accès au jeu réel et à éviter la triche.
 */
public class Procuration implements Jeu {

    /** Le jeu réel. */
    private Jeu jeuReel;
    
    /**
     * Constructeur de la procuration.
     * @param jeuReel le jeu réel
     */
    public Procuration(Jeu jeuReel) {
        this.jeuReel = jeuReel;
    }

    @Override
    public int getNombreAllumettes() {
        return jeuReel.getNombreAllumettes();
    }

    @Override
    public void retirer(int nbPrises) throws OperationInterditeException {
        throw new OperationInterditeException("Vous n'êtes pas autorisé à modifier le jeu directement !");
    }

} 