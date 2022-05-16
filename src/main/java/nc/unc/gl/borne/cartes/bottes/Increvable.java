package nc.unc.gl.borne.cartes.bottes;

import nc.unc.gl.borne.Deck;
import nc.unc.gl.borne.Jeu;
import nc.unc.gl.borne.cartes.Botte;
import nc.unc.gl.borne.cartes.attaques.Crevaison;

public class Increvable  extends Botte {

    public Class<Crevaison> contre = Crevaison.class;

    @Override
    public Class<Crevaison> getContre() {
        return contre;
    }

    public void jouerCarte(Jeu jeu, Deck deck){
        deck.addBotte(this);
        if (deck.getBataille() instanceof Crevaison){
            deck.defausserCarteOnTopOfBataille(jeu);
        }
        jeu.setProchainJoueur(jeu.getJoueurCourant());
    }
}
