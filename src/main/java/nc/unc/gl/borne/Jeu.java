package nc.unc.gl.borne;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Jeu {
    private  ArrayList<Joueur> joueurs;
    private Joueur joueurCourant;
    private Joueur prochainJoueur;
    public Pioche pioche;
    private Pile defausse;

    public Jeu(){
        this.joueurs = new ArrayList<Joueur>();
        this.joueurCourant = null;
        this.prochainJoueur = null;
    }

    public void ajouteJoueurs(Joueur... joueurs) throws IllegalStateException {
        if (this.joueurCourant != null) {
            throw new IllegalStateException("La partie a déjà commencé.");
        }
        this.joueurs.addAll(Arrays.asList(joueurs));
    }

    public void defausserCarte(Carte carte) {
        defausse.deposer(carte);
    }

    public void setProchainJoueur(Joueur prochainJoueurCourant) {
        this.prochainJoueur = prochainJoueurCourant;
    }

    public Joueur getJoueurCourant() {
        return this.joueurCourant;
    }

    public Carte piocherCarte() {
        return pioche.piocher();
    }

    public int getNbCartesPioche(){
        return pioche.getNbCartes();
    }

    public void prepareJeu() {
        Collections.shuffle(joueurs);
        this.pioche = new Pioche();
        this.pioche.init();
        this.pioche.melangeCartes();
        this.defausse = new Pile();

        for (int i = 0; i < joueurs.size(); i++) {
            for (int j = 0; j < 6; j++) {
                joueurs.get(i).prendCarte(pioche.piocher());
            }
            joueurs.get(i).setProchainJoueur(joueurs.get((i+1)%joueurs.size()));
        }

        prochainJoueur = joueurs.get(0);
    }

    public boolean estPartieFinie() {
        if (pioche.isEmpty()) {
            return true;
        }
        for (Joueur j: joueurs) {
            if (j.getDistance() == 1000) {
                return true;
            }
        }
        return false;
    }

    public List<Joueur> getGagnant() {
        ArrayList<Joueur> rep = new ArrayList<Joueur>();
        int max = 0;
        for (Joueur j: joueurs) {
            if (j.getDistance() > max) {
                max = j.getDistance();
            }
        }

        for (Joueur j: joueurs) {
            if (j.getDistance() == max) {
                rep.add(j);
            }
        }
        return rep;
    }

    public void activeProchainJoueurEtTireCarte() {
        if (!estPartieFinie()) {
            this.joueurCourant = prochainJoueur;
            this.prochainJoueur = joueurCourant.getProchainJoueur();
            joueurCourant.prendCarte(piocherCarte());
        }
    }

    public boolean joue() {
        activeProchainJoueurEtTireCarte();
        System.out.println(this);
        int succes = 0;
        while (succes == 0) {
            try {
                int choix = joueurCourant.choisitCarte();
                if (choix < 0 && -choix <= joueurCourant.getMain().size()) {
                    joueurCourant.defausseCarte(this, -choix-1);
                    succes = 1;
                } else if (choix > 0 && choix <= joueurCourant.getMain().size()) {
                    joueurCourant.joueCarte(this, choix-1);
                    succes = 1;
                } else {
                    throw new IllegalStateException("Choix invalide.");
                }
            } catch (Exception e) {
                if (e instanceof IllegalStateException) {
                    System.out.println("Erreur : "+e.getMessage());
                } else {
                    System.out.println("Erreur improbale : "+e);
                }

            }
        }

        System.out.print("______________________________ fin de tour _______________________________\n\n");

        return estPartieFinie();
    }

    public String toString() {
        StringBuilder txt = new StringBuilder();
        for (Joueur j: joueurs) {
            if (j == joueurCourant) {
                txt.append(">> ");
                txt.append(j.toString());
                txt.append(" <<");
            } else {
                txt.append(j.toString());
            }
            txt.append("\n");

        }
        txt.append("\n");
        txt.append(this.getNbCartesPioche()).append(" carte");
        if (this.getNbCartesPioche() > 1) {
            txt.append("s");
        }
        txt.append(" dans la pioche et ").append(this.defausse.getNbCartes()).append(" carte");
        if (this.defausse.getNbCartes() > 1) {
            txt.append("s");
        }
        txt.append(" dans la défausse.");
        return txt.toString();
    }

}
