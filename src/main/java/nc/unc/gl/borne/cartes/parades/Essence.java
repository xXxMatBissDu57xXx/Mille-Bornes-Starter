package nc.unc.gl.borne.cartes.parades;

import nc.unc.gl.borne.cartes.Parade;

import nc.unc.gl.borne.cartes.attaques.Panne;

public class Essence extends Parade {


    public Class<Panne> contre = Panne.class;

    @Override
    public Class<Panne> getContre() {
        return this.contre;
    }
}
