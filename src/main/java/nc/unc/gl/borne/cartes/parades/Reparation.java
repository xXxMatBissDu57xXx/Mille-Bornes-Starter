package nc.unc.gl.borne.cartes.parades;

import nc.unc.gl.borne.cartes.Parade;
import nc.unc.gl.borne.cartes.attaques.Accident;

public class Reparation extends Parade {

    public Class<Accident> contre = Accident.class;

    @Override
    public Class<Accident> getContre() {
        return contre;
    }
}
