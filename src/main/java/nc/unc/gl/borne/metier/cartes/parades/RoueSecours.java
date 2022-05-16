package nc.unc.gl.borne.metier.cartes.parades;

import nc.unc.gl.borne.metier.cartes.Parade;
import nc.unc.gl.borne.metier.cartes.attaques.Crevaison;

public class RoueSecours extends Parade {

    public String sourceImage = "images/parade_crevaison.jpeg";

    public String getSourceImage() {
        return sourceImage;
    }

    public Class<Crevaison> contre = Crevaison.class;

    @Override
    public Class<Crevaison> getContre() {
        return contre;
    }
}
