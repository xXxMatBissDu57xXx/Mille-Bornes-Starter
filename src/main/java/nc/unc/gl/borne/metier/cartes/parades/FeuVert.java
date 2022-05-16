package nc.unc.gl.borne.metier.cartes.parades;

import nc.unc.gl.borne.metier.Deck;
import nc.unc.gl.borne.metier.Jeu;
import nc.unc.gl.borne.metier.cartes.Parade;
import nc.unc.gl.borne.metier.cartes.attaques.FeuRouge;
import nc.unc.gl.borne.metier.cartes.bottes.Prioritaire;

public class FeuVert  extends Parade {

    public String sourceImage = "images/parade_feu.jpeg";

    public String getSourceImage() {
        return sourceImage;
    }

    public Class<FeuRouge> contre = FeuRouge.class;

    @Override
    public Class<FeuRouge> getContre() {
        return contre;
    }

    public void joue(Jeu jeu, Deck deck) throws IllegalStateException {
        if (deck.getBataille() == null) { // la pile est vide, il faut un FeuVert pour commencer
            if (deck.getBottes().stream().anyMatch(c -> c instanceof Prioritaire)) {
                throw new IllegalStateException("Pas besoin de commencer par un feu vert si vous êtes véhicule prioritaire !");
            }
            deck.setBataille(this);
        }
        else if (deck.getBataille() instanceof FeuRouge) {
            deck.setBataille(this);
        }
        else {
            throw new IllegalStateException("Vous ne pouvez pas jouer un feu vert dans cette situation.");
        }
    }
}
