package nc.unc.gl.borne.cartes.parades;

import nc.unc.gl.borne.Deck;
import nc.unc.gl.borne.Jeu;
import nc.unc.gl.borne.cartes.Parade;
import nc.unc.gl.borne.cartes.attaques.FeuRouge;
import nc.unc.gl.borne.cartes.bottes.Prioritaire;

public class FeuVert  extends Parade {

    public Class<FeuRouge> contre = FeuRouge.class;

    @Override
    public Class<FeuRouge> getContre() {
        return contre;
    }

    public void jouerCarte(Jeu jeu, Deck deck) throws IllegalStateException {
        if (deck.getBataille() == null) {
            boolean isJoueurPossedePrioritaire = deck.getBottes().stream().anyMatch(c -> c instanceof Prioritaire);
            if (isJoueurPossedePrioritaire) {
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
