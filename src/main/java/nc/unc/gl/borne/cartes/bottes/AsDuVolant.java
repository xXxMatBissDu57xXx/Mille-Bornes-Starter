package nc.unc.gl.borne.cartes.bottes;

import nc.unc.gl.borne.Deck;
import nc.unc.gl.borne.Jeu;
import nc.unc.gl.borne.cartes.Botte;
import nc.unc.gl.borne.cartes.attaques.Accident;

public class AsDuVolant extends Botte {

    public Class<Accident> contre = Accident.class;

    @Override
    public Class<Accident> getContre() {
        return contre;
    }

    public void joue(Jeu jeu, Deck deck){
        deck.addBotte(this);
        if (deck.getBataille() instanceof Accident){
            deck.defausseBataille(jeu);
        }
        jeu.setProchainJoueur(jeu.getJoueurCourant());
    }
}
