package nc.unc.gl.borne.cartes;

import nc.unc.gl.borne.Carte;
import nc.unc.gl.borne.Deck;
import nc.unc.gl.borne.Jeu;

public class Borne extends Carte {

    private final int distance;

    public Borne(int distance) {
        this.distance = distance;
    }

    public void joue(Jeu jeu, Deck deck){
        deck.ajouteDistance(distance);
    }

    @Override
    public String toString() {
        return super.toString()+" "+distance;
    }
}
