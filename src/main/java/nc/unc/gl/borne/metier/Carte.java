package nc.unc.gl.borne.metier;

public abstract class Carte {

    public String sourceImage;

    public String getSourceImage() {
        return sourceImage;
    }

    public abstract void joue(Jeu jeu, Deck deck);

    public String toString(){
        return this.getClass().getSimpleName();
    }
}
