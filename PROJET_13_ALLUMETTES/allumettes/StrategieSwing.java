package allumettes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;

/**
 * Stratégie de jeu qui utilise une interface graphique Swing pour demander
 * le nombre d'allumettes à prendre.
 */
public class StrategieSwing implements Strategie {

    /** Fenêtre principale. */
    private JFrame fenetre;
    
    /** Label pour afficher le nombre d'allumettes. */
    private JLabel labelAllumettes;
    
    /** Boutons pour choisir le nombre d'allumettes. */
    private JButton bouton1, bouton2, bouton3;
    
    /** Bouton pour tricher. */
    private JButton boutonTricher;
    
    /** Champ pour le nombre d'allumettes à retirer en trichant. */
    private JTextField champTriche;
    
    /** Objet de synchronisation entre les threads. */
    private final Object verrou = new Object();
    
    /** Le choix fait par l'utilisateur. */
    private int choix = 0;
    
    /** Indique si c'est la dernière allumette */
    private boolean estDerniereAllumette = false;
    
    /** Nom du joueur */
    private String nomJoueur = "Xavier";

    /**
     * Constructeur de la stratégie Swing.
     */
    public StrategieSwing() {
        initialiserInterface();
    }

    /**
     * Initialiser l'interface graphique.
     */
    private void initialiserInterface() {
        fenetre = new JFrame("Jeu des allumettes");
        fenetre.setSize(250, 250);
        fenetre.setResizable(false);
        fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        fenetre.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                demanderFermeture();
            }
        });
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        JPanel panneauTriche = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        boutonTricher = new JButton("tricher");
        champTriche = new JTextField(1);
        champTriche.setText("1");
        champTriche.setHorizontalAlignment(JTextField.CENTER);
        panneauTriche.add(boutonTricher);
        panneauTriche.add(champTriche);
        
        labelAllumettes = new JLabel("13", SwingConstants.CENTER);
        labelAllumettes.setFont(new Font("SansSerif", Font.BOLD, 72));
        
        JPanel panneauBoutons = new JPanel(new GridLayout(1, 3, 5, 0));
        bouton1 = new JButton("1");
        bouton2 = new JButton("2");
        bouton3 = new JButton("3");
        
        Dimension buttonSize = new Dimension(70, 30);
        bouton1.setPreferredSize(buttonSize);
        bouton2.setPreferredSize(buttonSize);
        bouton3.setPreferredSize(buttonSize);
        
        panneauBoutons.add(bouton1);
        panneauBoutons.add(bouton2);
        panneauBoutons.add(bouton3);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5, 5, 5, 5);
        panel.add(panneauTriche, c);
        
        c.gridy = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        panel.add(labelAllumettes, c);
        
        c.gridy = 2;
        c.weightx = 1.0;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(panneauBoutons, c);
        
        fenetre.add(panel);
        
        bouton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                faireChoix(1);
            }
        });
        
        bouton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                faireChoix(2);
            }
        });
        
        bouton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                faireChoix(3);
            }
        });
        
        boutonTricher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer.parseInt(champTriche.getText());
                    faireChoix(-1);
                } catch (NumberFormatException ex) {
                }
            }
        });
    }
    
    /**
     * Mettre à jour l'interface en fonction du nombre d'allumettes restantes.
     * @param nbAllumettes le nombre d'allumettes restantes
     * @param joueur le nom du joueur
     */
    private void mettreAJourInterface(int nbAllumettes, String joueur) {
        this.nomJoueur = joueur;
        mettreAJourTitre();
        
        labelAllumettes.setText(String.valueOf(nbAllumettes));
        
        bouton1.setEnabled(nbAllumettes >= 1);
        bouton2.setEnabled(nbAllumettes >= 2);
        bouton3.setEnabled(nbAllumettes >= 3);
        
        if (nbAllumettes == 1) {
            champTriche.setText("1");
            estDerniereAllumette = true;
        } else {
            champTriche.setText("1");
            estDerniereAllumette = false;
        }
    }
    
    /**
     * Mettre à jour le titre de la fenêtre avec le nom du joueur.
     */
    private void mettreAJourTitre() {
        String prenom = nomJoueur;
        if (prenom.contains("@")) {
            prenom = prenom.substring(0, prenom.indexOf("@"));
        }
        String titre = prenom + " ?";
        fenetre.setTitle(titre);
    }
    
    /**
     * Faire un choix d'allumettes à prendre.
     * @param n le nombre d'allumettes à prendre
     */
    private void faireChoix(int n) {
        synchronized (verrou) {
            choix = n;
            fenetre.setVisible(false);
            verrou.notify();
        }
    }

    /**
     * Demande la fermeture propre de l'application.
     * Cette méthode est appelée quand la dernière allumette est jouée.
     */
    private void demanderFermeture() {
        synchronized (verrou) {
            choix = -2;
            verrou.notify();
        }
        
        if (fenetre != null) {
            fenetre.dispose();
        }
        
        Jouer.terminer();
    }

    @Override
    public int getPrise(Jeu jeu) {
        String joueurCourant = System.getProperty("joueur.courant");
        
        if (joueurCourant != null && !joueurCourant.isEmpty()) {
            nomJoueur = joueurCourant;
        } else {
            nomJoueur = "Xavier";
        }
        
        choix = 0;
        
        mettreAJourInterface(jeu.getNombreAllumettes(), nomJoueur);
        
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
        
        synchronized (verrou) {
            while (choix == 0) {
                try {
                    verrou.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        
        if (jeu.getNombreAllumettes() <= 1 || estDerniereAllumette) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    fenetre.dispose();
                    if (estDerniereAllumette) {
                        System.out.println("Fermeture finale de la fenêtre");
                        demanderFermeture();
                    }
                }
            });
        }
        
        if (choix == -1) {
            try {
                int nbRetirer = Integer.parseInt(champTriche.getText());
                
                if (jeu.getNombreAllumettes() == 1) {
                    System.out.println("[Je triche... 1 allumette en plus]");
                     try {
                        Field champ = JeuSimple.class.getDeclaredField("nbAllumettes");
                        champ.setAccessible(true);
                        int valeurActuelle = (int) champ.get(jeu);
                        champ.set(jeu, valeurActuelle + 1);
                        } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException | SecurityException | ClassCastException e) {
                            System.out.println("Échec de l'ajout d'allumette par triche !");
                        }
                } else {
                    System.out.println("[Je triche... " + nbRetirer + " allumette" + 
                            (nbRetirer > 1 ? "s" : "") + " en moins]");
                    
                    try {
                        jeu.retirer(nbRetirer);
                    } catch (CoupInvalideException e) {
                        System.out.println("Impossible de tricher, nombre d'allumettes invalide");
                    } catch (OperationInterditeException e) {
                        System.out.println("Triche échouée : opération interdite !");
                    }
                }
                
                return 1;
            } catch (NumberFormatException e) {
                return 1;
            }
        }
        
        return choix;
    }
} 