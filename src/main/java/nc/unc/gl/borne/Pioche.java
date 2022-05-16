package nc.unc.gl.borne;

import nc.unc.gl.borne.cartes.Borne;
import nc.unc.gl.borne.cartes.parades.*;
import nc.unc.gl.borne.cartes.bottes.*;
import nc.unc.gl.borne.cartes.attaques.*;

import java.util.Collections;


public class Pioche extends Pile{


    public Pioche(){
    }

    public void init() {

        // les bottes :
        push(new Prioritaire());
        push(new CamionCiterne());
        push(new Increvable());
        push(new AsDuVolant());

        // les bornes kilométriques :
        for (int i = 0; i < 10; i++) {
            push(new Borne(25));
            push(new Borne(50));
            push(new Borne(75));
        }
        for (int i = 0; i < 12; i++) {
            push(new Borne(100));
        }
        for (int i = 0; i < 4; i++) {
            push(new Borne(200));
        }

        // les attaques :
        for (int i = 0; i < 5; i++) {
            push(new FeuRouge());
        }
        for (int i = 0; i < 4; i++) {
            push(new Limitation());
        }
        for (int i = 0; i < 3; i++) {
            push(new Panne());
            push(new Crevaison());
            push(new Accident());
        }

        // les parades :
        for (int i = 0; i < 14; i++) {
            push(new FeuVert());
        }
        for (int i = 0; i < 6; i++) {
            push(new FinLimitation());
            push(new Essence());
            push(new RoueSecours());
            push(new Reparation());
        }

    }

    public void melangeCartes() {
        Collections.shuffle(stack);
    }


}
