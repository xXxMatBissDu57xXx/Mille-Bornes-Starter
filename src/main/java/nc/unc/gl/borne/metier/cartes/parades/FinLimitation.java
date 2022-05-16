package nc.unc.gl.borne.metier.cartes.parades;

import nc.unc.gl.borne.metier.Deck;
import nc.unc.gl.borne.metier.Jeu;
import nc.unc.gl.borne.metier.cartes.Parade;
import nc.unc.gl.borne.metier.cartes.attaques.Limitation;

public class FinLimitation  extends Parade {

    public String sourceImage = "images/parade_vitesse.jpeg";

    public String getSourceImage() {
        return sourceImage;
    }

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
