package nc.unc.gl.borne.cartes.bottes;

import nc.unc.gl.borne.Deck;
import nc.unc.gl.borne.Jeu;
import nc.unc.gl.borne.cartes.Botte;
import nc.unc.gl.borne.cartes.attaques.Panne;

public class CamionCiterne  extends Botte {

    public Class<Panne> contre = Panne.class;

    @Override
    public Class<Panne> getContre() {
        return contre;
    }

    public void jouerCarte(Jeu jeu, Deck deck){
        deck.addBotte(this);
        if (deck.getBataille() instanceof Panne){
            deck.defausserCarteOnTopOfBataille(jeu);
        }
        jeu.setProchainJoueur(jeu.getJoueurCourant());
    }
}
