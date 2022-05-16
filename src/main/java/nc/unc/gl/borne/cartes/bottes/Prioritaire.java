package nc.unc.gl.borne.cartes.bottes;

import nc.unc.gl.borne.Deck;
import nc.unc.gl.borne.Jeu;
import nc.unc.gl.borne.cartes.Botte;
import nc.unc.gl.borne.cartes.attaques.FeuRouge;
import nc.unc.gl.borne.cartes.attaques.Limitation;

public class Prioritaire extends Botte {

    public Class<FeuRouge> contre = FeuRouge.class;

    @Override
    public Class<FeuRouge> getContre() {
        return contre;
    }

    public void jouerCarte(Jeu jeu, Deck deck){
        deck.addBotte(this);
        if (deck.getBataille() instanceof FeuRouge){
            deck.defausserCarteOnTopOfBataille(jeu);
        }
        if (deck.getLimiteVitesse() instanceof Limitation){
            deck.defausserCarteOnTopOfVitesse(jeu);
        }
        jeu.setProchainJoueur(jeu.getJoueurCourant());
    }
}
