package nc.unc.gl.borne.metier.cartes.parades;

import nc.unc.gl.borne.metier.cartes.Parade;

import nc.unc.gl.borne.metier.cartes.attaques.Panne;

public class Essence extends Parade {

    public String sourceImage = "images/parade_essence.jpeg";

    public String getSourceImage() {
        return sourceImage;
    }

    public Class<Panne> contre = Panne.class;

    @Override
    public Class<Panne> getContre() {
        return this.contre;
    }
}
