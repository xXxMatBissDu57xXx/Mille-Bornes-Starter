package nc.unc.gl.borne.metier.cartes.bottes;

import nc.unc.gl.borne.metier.Deck;
import nc.unc.gl.borne.metier.Jeu;
import nc.unc.gl.borne.metier.cartes.Botte;
import nc.unc.gl.borne.metier.cartes.attaques.Accident;

public class AsDuVolant extends Botte {

    public String sourceImage = "images/botte_accident.jpeg";

    public String getSourceImage() {
        return sourceImage;
    }

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
