package nc.unc.gl.borne.metier.cartes.bottes;

import nc.unc.gl.borne.metier.Deck;
import nc.unc.gl.borne.metier.Jeu;
import nc.unc.gl.borne.metier.cartes.Botte;
import nc.unc.gl.borne.metier.cartes.attaques.FeuRouge;
import nc.unc.gl.borne.metier.cartes.attaques.Limitation;

public class Prioritaire extends Botte {

    public String sourceImage = "images/botte_vitesse.jpeg";

    public String getSourceImage() {
        return sourceImage;
    }

    public Class<FeuRouge> contre = FeuRouge.class;

    @Override
    public Class<FeuRouge> getContre() {
        return contre;
    }

    public void joue(Jeu jeu, Deck deck){
        deck.addBotte(this);
        if (deck.getBataille() instanceof FeuRouge){
            deck.defausseBataille(jeu);
        }
        if (deck.getLimiteVitesse() instanceof Limitation){
            deck.defausseVitesse(jeu);
        }
        jeu.setProchainJoueur(jeu.getJoueurCourant());
    }
}
