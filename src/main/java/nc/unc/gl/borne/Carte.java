package nc.unc.gl.borne;

public abstract class Carte {

    public abstract void joue(Jeu jeu, Deck deck);

    public String toString(){
        return this.getClass().getSimpleName();
    }
}
