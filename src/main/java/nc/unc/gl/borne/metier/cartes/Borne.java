package nc.unc.gl.borne.metier.cartes;

import nc.unc.gl.borne.metier.Carte;
import nc.unc.gl.borne.metier.Deck;
import nc.unc.gl.borne.metier.Jeu;

public class Borne extends Carte {

    public String sourceImage;

    public String getSourceImage() {
        return sourceImage;
    }

    private final int distance;

    public Borne(int distance) {
        this.distance = distance;
        switch (distance) {
            case 25 -> sourceImage = "images/borne_25.jpeg";
            case 50 -> sourceImage = "images/borne_50.jpeg";
            case 75 -> sourceImage = "images/borne_75.jpeg";
            case 100 -> sourceImage = "images/borne_100.jpeg";
            case 200 -> sourceImage = "images/borne_200.jpeg";
        }
    }

    public void joue(Jeu jeu, Deck deck){
        deck.ajouteDistance(distance);
    }

    @Override
    public String toString() {
        return super.toString()+" "+distance;
    }
}
