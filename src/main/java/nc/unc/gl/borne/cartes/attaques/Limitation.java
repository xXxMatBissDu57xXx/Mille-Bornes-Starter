package nc.unc.gl.borne.cartes.attaques;

import nc.unc.gl.borne.Carte;
import nc.unc.gl.borne.Deck;
import nc.unc.gl.borne.Jeu;
import nc.unc.gl.borne.cartes.Attaque;
import nc.unc.gl.borne.cartes.Botte;
import nc.unc.gl.borne.cartes.bottes.Prioritaire;

public class Limitation  extends Attaque {



    public void joue(Jeu jeu, Deck deck) throws IllegalStateException {
        if (deck.isLimiteVitesse()) {
            throw new IllegalStateException("La vitesse de ce joueur est déjà limitée.");
        }
        else {
            deck.setLimiteVitesse(this);
        }
        for (Botte botte : deck.getBottes()) {
            if (botte.getClass() == Prioritaire.class) {
                throw new IllegalStateException("Impossible d'appliquer l'attaque " + this + " sur ce joueur car il est " + botte + ".");
            }
        }
        for (Carte carte : deck.getMain()) {
            if (carte instanceof Botte && carte.getClass() == Prioritaire.class) {
                deck.choisitCoupFourre(jeu, this, deck.getMain().indexOf(carte));
                break; // Désole pour le break mais sinon c'est ConcurrentModificationException
            }
        }
    }
}
