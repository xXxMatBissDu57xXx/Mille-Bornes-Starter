package nc.unc.gl.borne;

import nc.unc.gl.borne.cartes.Attaque;

public abstract class Bataille extends Carte {

    public abstract boolean contre (Attaque carte);

    public abstract void appliqueEffet(Jeu jeu, Deck joueur) throws IllegalStateException;
}
