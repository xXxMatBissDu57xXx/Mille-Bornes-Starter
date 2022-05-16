package nc.unc.gl.borne.cartes.parades;

import nc.unc.gl.borne.Deck;
import nc.unc.gl.borne.Jeu;
import nc.unc.gl.borne.cartes.Parade;
import nc.unc.gl.borne.cartes.attaques.Limitation;

public class FinLimitation  extends Parade {

    public Class<Limitation> contre = Limitation.class;

    @Override
    public Class<Limitation> getContre() {
        return contre;
    }

    public void joue(Jeu jeu, Deck deck) throws IllegalStateException {
        if(deck.isLimiteVitesse()) {
            deck.setLimiteVitesse(this);
        }
        else {
            throw new IllegalStateException("Votre vitesse n'est pas limitée.");
        }
    }
}
