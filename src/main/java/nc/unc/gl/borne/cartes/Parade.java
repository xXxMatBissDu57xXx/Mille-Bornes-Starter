package nc.unc.gl.borne.cartes;

import nc.unc.gl.borne.Carte;
import nc.unc.gl.borne.Deck;
import nc.unc.gl.borne.Jeu;
import nc.unc.gl.borne.cartes.attaques.FeuRouge;
import nc.unc.gl.borne.cartes.bottes.Prioritaire;

public abstract class Parade extends Carte {


    public Class<Attaque> contre;

    public Class getContre(){
        return contre;
    }
    public void jouerCarte(Jeu jeu, Deck deck) throws IllegalStateException {
        boolean isBatailleJoueurVide = deck.getBataille() == null;
        boolean nEstPasPrioritaire = deck.getBottes().stream().noneMatch(c -> c instanceof Prioritaire);
        if (isBatailleJoueurVide && nEstPasPrioritaire) {
            throw new IllegalStateException("Vous n'avez toujours pas démarré, il faut commencer par un feu vert");
        }
        boolean carteContreCarteInTopOfPile = deck.getBataille() instanceof Attaque && getContre() == deck.getBataille().getClass();
        if (carteContreCarteInTopOfPile) {
            deck.setBataille(this);
        } else {
            throw new IllegalStateException("Vous ne pouvez pas jouer cette carte.");
        }
    }
}
