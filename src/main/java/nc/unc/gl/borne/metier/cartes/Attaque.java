package nc.unc.gl.borne.metier.cartes;
import nc.unc.gl.borne.metier.Carte;
import nc.unc.gl.borne.metier.Deck;
import nc.unc.gl.borne.metier.Jeu;
import nc.unc.gl.borne.metier.cartes.bottes.Prioritaire;


public abstract class Attaque extends Carte {

    protected Class<Parade> paradeAssocie;
    protected Class<Botte> botteAssocie;


    public void joue(Jeu jeu, Deck deck) throws IllegalStateException {
        if (deck.getBataille() == null && deck.getBottes().stream().noneMatch(c -> c instanceof Prioritaire)) {
            throw new IllegalStateException("Impossible de poser une attaque sur un joueur qui n'a pas encore posé de Feu Vert.");
        }
        if (deck.getBataille() instanceof Attaque) {
            throw new IllegalStateException("Ce joueur est déjà bloqué par une attaque.");
        }
        for (Botte botte : deck.getBottes()) {
            if (this.getClass() == botte.getContre()) {
                throw new IllegalStateException("Impossible d'appliquer l'attaque " + this + " sur ce joueur car il est " + botte + ".");
            }
        }
        deck.setBataille(this);
        for (Carte carte : deck.getMain()) {
            if (carte instanceof Botte && this.getClass() == ((Botte) carte).getContre()) {
                deck.choisitCoupFourre(jeu, this, deck.getMain().indexOf(carte));
                break;
            }
        }
    }
}
