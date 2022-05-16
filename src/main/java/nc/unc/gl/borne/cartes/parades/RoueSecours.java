package nc.unc.gl.borne.cartes.parades;

import nc.unc.gl.borne.cartes.Parade;
import nc.unc.gl.borne.cartes.attaques.Crevaison;

public class RoueSecours extends Parade {

    public Class<Crevaison> contre = Crevaison.class;

    @Override
    public Class<Crevaison> getContre() {
        return contre;
    }
}
