classDiagram
    class Jeu {
        <<interface>>
        +int PRISE_MAX
        +int getNombreAllumettes()
        +void retirer(int nbPrises)
    }
    
    class JeuSimple {
        -int nbAllumettes
        +JeuSimple(int nbAllumettes)
        +int getNombreAllumettes()
        +void retirer(int nbPrises)
    }
    
    class Procuration {
        -Jeu jeuReel
        +Procuration(Jeu jeuReel)
        +int getNombreAllumettes()
        +void retirer(int nbPrises)
    }
    
    class Joueur {
        -String nom
        -Strategie strategie
        +Joueur(String nom, Strategie strategie)
        +String getNom()
        +int getPrise(Jeu jeu)
        +void setStrategie(Strategie strategie)
    }
    
    class Arbitre {
        -Joueur joueur1
        -Joueur joueur2
        -boolean confiant
        -FileWriter xmlWriter
        -int numeroCoup
        +Arbitre(Joueur joueur1, Joueur joueur2, boolean confiant)
        +void arbitrer(Jeu jeu)
        -Joueur jouerTour(Joueur joueur, Jeu jeuPresente, Jeu jeuReel)
        -void ecrireXML(String type, String nom)
        -void ecrireXML(String type, String nom, int nbPrises)
    }
    
    class Strategie {
        <<interface>>
        +int getPrise(Jeu jeu)
    }
    
    class StrategieNaif {
        -Random random
        +StrategieNaif()
        +int getPrise(Jeu jeu)
    }
    
    class StrategieRapide {
        +int getPrise(Jeu jeu)
    }
    
    class StrategieExpert {
        +int getPrise(Jeu jeu)
    }
    
    class StrategieHumain {
        -static Scanner scanner
        +StrategieHumain(Scanner scanner)
        +int getPrise(Jeu jeu)
    }
    
    class StrategieTricheur {
        +int getPrise(Jeu jeu)
    }
    
    class StrategieSuperTricheur {
        +int getPrise(Jeu jeu)
    }
    
    class StrategieSwing {
        -JFrame fenetre
        -JLabel labelAllumettes
        -JButton bouton1, bouton2, bouton3
        -JButton boutonTricher
        -JTextField champTriche
        -final Object verrou
        -int choix
        -boolean estDerniereAllumette
        -String nomJoueur
        +StrategieSwing()
        -void initialiserInterface()
        +int getPrise(Jeu jeu)
    }
    
    class Jouer {
        -static final int NB_ALLUMETTES_INITIAL
        -static boolean doitTerminer
        +static void main(String[] args)
        +static void terminer()
        -static void verifierNombreArguments(String[] args)
        +static void afficherUsage()
        -static Joueur creerJoueur(String parametreJoueur, Scanner scanner)
        -static Strategie creerStrategie(String nomStrategie, Scanner scanner)
        -static void creerDTD()
    }
    
    class CoupInvalideException {
        -int coup
        -String probleme
        +CoupInvalideException(int coup, String probleme)
        +int getCoup()
        +String getProbleme()
    }
    
    class OperationInterditeException {
        +OperationInterditeException(String message)
    }
    
    class ConfigurationException {
        +ConfigurationException(String message)
    }
    
    JeuSimple ..|> Jeu
    Procuration ..|> Jeu
    Procuration --> Jeu : protège
    
    Arbitre --> Joueur : arbitre
    Arbitre --> Jeu : utilise
    
    Joueur --> Strategie : utilise
    
    StrategieNaif ..|> Strategie
    StrategieRapide ..|> Strategie
    StrategieExpert ..|> Strategie
    StrategieHumain ..|> Strategie
    StrategieTricheur ..|> Strategie
    StrategieSuperTricheur ..|> Strategie
    StrategieSwing ..|> Strategie
    
    Jouer --> Arbitre : crée
    Jouer --> Joueur : crée
    Jouer --> JeuSimple : crée
    Jouer --> Strategie : crée