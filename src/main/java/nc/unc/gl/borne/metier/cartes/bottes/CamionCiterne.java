package nc.unc.gl.borne.metier.cartes.bottes;

import nc.unc.gl.borne.metier.Deck;
import nc.unc.gl.borne.metier.Jeu;
import nc.unc.gl.borne.metier.cartes.Botte;
import nc.unc.gl.borne.metier.cartes.attaques.Panne;

public class CamionCiterne  extends Botte {

    public String sourceImage = "images/botte_essence.jpeg";

    public String getSourceImage() {
        return sourceImage;
    }

    public Class<Panne> contre = Panne.class;

    @Override
    public Class<Panne> getContre() {
        return contre;
    }

    public void joue(Jeu jeu, Deck deck){
        deck.addBotte(this);
        if (deck.getBataille() instanceof Panne){
            deck.defausseBataille(jeu);
        }
        jeu.setProchainJoueur(jeu.getJoueurCourant());
    }
}
