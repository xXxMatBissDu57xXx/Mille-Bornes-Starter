package nc.unc.gl.borne.metier.cartes.bottes;

import nc.unc.gl.borne.metier.Deck;
import nc.unc.gl.borne.metier.Jeu;
import nc.unc.gl.borne.metier.cartes.Botte;
import nc.unc.gl.borne.metier.cartes.attaques.Crevaison;

public class Increvable  extends Botte {

    public String sourceImage = "images/botte_crevaison.jpeg";

    public String getSourceImage() {
        return sourceImage;
    }

    public Class<Crevaison> contre = Crevaison.class;

    @Override
    public Class<Crevaison> getContre() {
        return contre;
    }

    public void joue(Jeu jeu, Deck deck){
        deck.addBotte(this);
        if (deck.getBataille() instanceof Crevaison){
            deck.defausseBataille(jeu);
        }
        jeu.setProchainJoueur(jeu.getJoueurCourant());
    }
}
