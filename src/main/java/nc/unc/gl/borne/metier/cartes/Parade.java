package nc.unc.gl.borne.metier.cartes;

import nc.unc.gl.borne.metier.Carte;
import nc.unc.gl.borne.metier.Deck;
import nc.unc.gl.borne.metier.Jeu;
import nc.unc.gl.borne.metier.cartes.bottes.Prioritaire;

public abstract class Parade extends Carte {


    public Class<Attaque> contre;

    public Class getContre(){
        return contre;
    }
    public void joue(Jeu jeu, Deck deck) throws IllegalStateException {
        if (deck.getBataille() == null && deck.getBottes().stream().noneMatch(c -> c instanceof Prioritaire)) {
            throw new IllegalStateException("Vous n'avez toujours pas démarré, il faut commencer par un feu vert");
        }
        if (deck.getBataille() instanceof Attaque && getContre() == deck.getBataille().getClass()) {
            deck.setBataille(this);
        } else {
            throw new IllegalStateException("Vous ne pouvez pas jouer cette carte.");
        }
    }
}
