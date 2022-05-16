package nc.unc.gl.borne.cartes;
import nc.unc.gl.borne.Carte;
import nc.unc.gl.borne.Deck;
import nc.unc.gl.borne.Jeu;
import nc.unc.gl.borne.cartes.bottes.Prioritaire;


public abstract class Attaque extends Carte {

    protected Class<Parade> paradeAssocie;
    protected Class<Botte> botteAssocie;


    public void jouerCarte(Jeu jeu, Deck deck) throws IllegalStateException {
        boolean isBatailleJoueurVide = deck.getBataille() == null;
        boolean nEstPasPrioritaire = deck.getBottes().stream().noneMatch(c -> c instanceof Prioritaire);
        if (isBatailleJoueurVide && nEstPasPrioritaire) {
            throw new IllegalStateException("Impossible de poser une attaque sur un joueur qui n'a pas encore posé de Feu Vert.");
        }
        if (deck.getBataille() instanceof Attaque) {
            throw new IllegalStateException("Ce joueur est déjà bloqué par une attaque.");
        }
        for (Botte botte : deck.getBottes()) {
            boolean hasBotteContre = this.getClass() == botte.getContre();
            if (hasBotteContre) {
                throw new IllegalStateException("Impossible d'appliquer l'attaque " + this + " sur ce joueur car il est " + botte + ".");
            }
        }
        deck.setBataille(this);
        for (Carte carte : deck.getMain()) {
            boolean hasBotteContreInHand = carte instanceof Botte && this.getClass() == ((Botte) carte).getContre();
            if (hasBotteContreInHand) {
                deck.choisitCoupFourre(jeu, this, deck.getMain().indexOf(carte));
                break;
            }
        }
    }
}
