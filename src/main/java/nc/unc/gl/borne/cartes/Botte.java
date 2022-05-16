package nc.unc.gl.borne.cartes;

import nc.unc.gl.borne.Carte;
import nc.unc.gl.borne.cartes.attaques.Accident;

public abstract class Botte extends Carte {

    public Class contre;

    public Class getContre() {
        return contre;
    }
}
