package nc.unc.gl.borne;

public abstract class Carte {

    public abstract void jouerCarte(Jeu jeu, Deck deck);

    public String toString(){
        return this.getClass().getSimpleName();
    }
}
