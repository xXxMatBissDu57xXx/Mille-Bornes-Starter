package nc.unc.gl.borne.cartes.attaques;

import nc.unc.gl.borne.Carte;
import nc.unc.gl.borne.Deck;
import nc.unc.gl.borne.Jeu;
import nc.unc.gl.borne.cartes.Attaque;
import nc.unc.gl.borne.cartes.Botte;
import nc.unc.gl.borne.cartes.bottes.Prioritaire;

public class Limitation  extends Attaque {



    public void jouerCarte(Jeu jeu, Deck deck) throws IllegalStateException {
        if (deck.hasLimiteVitesse()) {
            throw new IllegalStateException("La vitesse de ce joueur est déjà limitée.");
        }
        else {
            deck.setLimiteVitesse(this);
        }
        for (Botte botte : deck.getBottes()) {
            boolean deckHasPrioritaire = botte.getClass() == Prioritaire.class;
            if (deckHasPrioritaire) {
                throw new IllegalStateException("Impossible d'appliquer l'attaque " + this + " sur ce joueur car il est " + botte + ".");
            }
        }
        for (Carte carte : deck.getMain()) {
            boolean hasPrioritaireInHand = carte instanceof Botte && carte.getClass() == Prioritaire.class;
            if (hasPrioritaireInHand) {
                deck.choisitCoupFourre(jeu, this, deck.getMain().indexOf(carte));
                break;
            }
        }
    }
}
