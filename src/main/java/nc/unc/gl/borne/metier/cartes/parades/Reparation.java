package nc.unc.gl.borne.metier.cartes.parades;

import nc.unc.gl.borne.metier.cartes.Parade;
import nc.unc.gl.borne.metier.cartes.attaques.Accident;

public class Reparation extends Parade {

    public String sourceImage = "images/parade_accident.jpeg";

    public String getSourceImage() {
        return sourceImage;
    }

    public Class<Accident> contre = Accident.class;

    @Override
    public Class<Accident> getContre() {
        return contre;
    }
}
