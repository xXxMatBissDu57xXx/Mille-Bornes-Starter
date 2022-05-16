package nc.unc.gl.borne.cartes;

import nc.unc.gl.borne.Carte;
import nc.unc.gl.borne.Deck;
import nc.unc.gl.borne.Jeu;
import nc.unc.gl.borne.cartes.bottes.Prioritaire;

public class Borne extends Carte {

    private final int distance;

    public Borne(int distance) {
        this.distance = distance;
    }

    public void jouerCarte(Jeu jeu, Deck deck){

        if (deck.hasLimiteVitesse() && distance > 50) {
            throw new IllegalStateException("Vous êtes limité à 50 km/h.");
        }
        if (deck.km + distance > 1000) {
            throw new IllegalStateException("Il n'est pas possible de dépasser les 1000 km.");
        }
        boolean isBatailleJoueurVide = deck.getBataille() == null;
        boolean nEstPasPrioritaire = deck.getBottes().stream().noneMatch(c -> c instanceof Prioritaire);
        if (isBatailleJoueurVide && nEstPasPrioritaire) {
            throw new IllegalStateException("Vous ne pouvez pas avancer car il faut un feu vert pour commencer.");
        }
        if (deck.getBataille() instanceof Attaque) {
            throw new IllegalStateException("Vous ne pouvez pas avancer car vous êtes bloqué par l'attaque "+deck.getBataille());
        }

        deck.ajouteDistance(distance);
    }

    @Override
    public String toString() {
        return super.toString()+" "+distance;
    }
}
