package nc.unc.gl.borne.metier.cartes;

import nc.unc.gl.borne.metier.Carte;

public abstract class Botte extends Carte {

    public Class<Attaque> contre;

    public abstract Class getContre();
}
