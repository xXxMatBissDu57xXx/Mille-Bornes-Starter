package nc.unc.gl.borne;

import nc.unc.gl.borne.cartes.Borne;
import nc.unc.gl.borne.cartes.parades.*;
import nc.unc.gl.borne.cartes.bottes.*;
import nc.unc.gl.borne.cartes.attaques.*;

import java.util.Collections;


public class Pioche {

    public Pile pile;

    public Pioche(){
        this.pile = new Pile();
    }

    public void init() {

        // les bottes :
        pile.push(new Prioritaire());
        pile.push(new CamionCiterne());
        pile.push(new Increvable());
        pile.push(new AsDuVolant());

        // les bornes kilométriques :
        for (int i = 0; i < 10; i++) {
            pile.push(new Borne(25));
            pile.push(new Borne(50));
            pile.push(new Borne(75));
        }
        for (int i = 0; i < 12; i++) {
            pile.push(new Borne(100));
        }
        for (int i = 0; i < 4; i++) {
            pile.push(new Borne(200));
        }

        // les attaques :
        for (int i = 0; i < 5; i++) {
            pile.push(new FeuRouge());
        }
        for (int i = 0; i < 4; i++) {
            pile.push(new Limitation());
        }
        for (int i = 0; i < 3; i++) {
            pile.push(new Panne());
            pile.push(new Crevaison());
            pile.push(new Accident());
        }

        // les parades :
        for (int i = 0; i < 14; i++) {
            pile.push(new FeuVert());
        }
        for (int i = 0; i < 6; i++) {
            pile.push(new FinLimitation());
            pile.push(new Essence());
            pile.push(new RoueSecours());
            pile.push(new Reparation());
        }

    }

    public void melangeCartes() {
        Collections.shuffle(pile.stack);
    }

    public int getNbCartes() {
        return pile.getNbCartes();
    }

    public Carte piocher() {
        return pile.piocher();
    }

    public boolean isEmpty() {
        return pile.isEmpty();
    }

    public static void main(String[] args) {
        Pioche p = new Pioche();
        p.init();
        System.out.println(p.getNbCartes());
        p.melangeCartes();
        System.out.println(p.getNbCartes());

        System.out.println(p.getNbCartes());
        System.out.println(p.piocher());
    }
}
