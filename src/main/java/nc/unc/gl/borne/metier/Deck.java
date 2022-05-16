package nc.unc.gl.borne.metier;

import java.util.ArrayList;

import nc.unc.gl.borne.metier.cartes.Attaque;
import nc.unc.gl.borne.metier.cartes.Botte;
import nc.unc.gl.borne.metier.cartes.bottes.Prioritaire;

public class Deck {
    private Pile pileBataille;
    private int km;
    private Pile pileVitesse;
    private Joueur joueur;
    private ArrayList<Botte> bottes;
    private ArrayList<Carte> main;

    public Deck(Joueur joueur) {
        this.pileVitesse = new Pile();
        this.pileBataille = new Pile();
        this.main = new ArrayList<Carte>();
        this.bottes = new ArrayList<Botte>();
        this.km = 0;
        this.joueur = joueur;
    }

    public String ditPourquoiPeutPasAvancer() {
        Carte bataille = this.getBataille();
        if (bataille == null && getBottes().stream().noneMatch(c -> c instanceof Prioritaire)) {
            return "Vous ne pouvez pas avancer car il faut un feu vert pour commencer.";
        }
        if (bataille instanceof Attaque) {
            return "Vous ne pouvez pas avancer car vous êtes bloqué par l'attaque "+bataille;
        }
        return null;
    }

    public int getDistance() {
        return km;
    }

    public void ajouteDistance(int km) throws IllegalStateException {
        if (isLimiteVitesse() && km > 50) {
            throw new IllegalStateException("Vous êtes limité à 50 km/h.");
        }
        if (this.km + km > 1000) {
            throw new IllegalStateException("Il n'est pas possible de dépasser les 1000 km.");
        }
        String msgErreur = ditPourquoiPeutPasAvancer();
        if (msgErreur != null) {
            throw new IllegalStateException(msgErreur);
        }
        this.km += km;
    }

    public Carte getBataille() {
        if (pileBataille.isEmpty()) {
            return null;
        }
        return pileBataille.peek();
    }

    public void setLimiteVitesse(Carte carte){
        pileVitesse.deposer(carte);
    }

    public Carte getLimiteVitesse(){
        if (pileVitesse.isEmpty()) {
            return null;
        }
        return pileVitesse.peek();

    }

    public boolean isLimiteVitesse(){
        if(!pileVitesse.isEmpty()) {
            return (getLimiteVitesse() instanceof Attaque);
        }
        return false;
    }

    public void setBataille(Carte carte) {
        pileBataille.deposer(carte);
    }

    public ArrayList<Carte> getMain() {
        //return (ArrayList<Carte>) Collections.unmodifiableList(main);
        return this.main;
    }

    public void addBotte(Botte carte) {
        bottes.add(carte);
    }

    public ArrayList<Botte> getBottes() {
        return bottes;
    }

    public void prendCarte(Carte carte) throws IllegalStateException {
        if (main.size() > 6) {
            throw new IllegalStateException("Le joueur a déjà 6 cartes en main.");
        }
        main.add(carte);
    }

    public void defausseBataille(Jeu jeu){
        jeu.defausse(pileBataille.pop());
    }

    public void defausseVitesse(Jeu jeu){
        jeu.defausse(pileVitesse.pop());
    }

    public void defausseCarte(Jeu jeu, int numero) {
        jeu.defausse(main.get(numero));
        main.remove(numero);
    }

    public void attaque(Jeu jeu, Attaque carte) throws IllegalStateException {
        carte.joue(jeu, this);
    }

    public void joueCarte(Jeu jeu, int numero) throws IllegalStateException {
        Carte carte = main.get(numero);
        if (carte instanceof Attaque) {
            throw new IllegalStateException("La carte est une attaque donc il faut spécifier un adversaire.");
        }

        carte.joue(jeu, this);
        main.remove(numero);
    }

    public void joueCarte(Jeu jeu, int numero, Joueur adversaire) throws IllegalStateException {
        Carte carte = main.get(numero);
        if (!(carte instanceof Attaque)) {
            throw new IllegalStateException("La carte n'est pas une attaque donc il ne faut pas spécifier d'adversaire.");
        }
        adversaire.attaque(jeu, (Attaque) carte);
        main.remove(numero);
    }

    public void choisitCoupFourre(Jeu jeu, Attaque carte, int numero) {
        joueur.choisitCoupFourre(jeu, carte, numero);
    }

    public String toString() {
        StringBuilder txt = new StringBuilder(km + " km");
        if (isLimiteVitesse()) {
            txt.append(", limité à 50 km/h");
        }
        if (!bottes.isEmpty()) {
            for(Botte botte : bottes) {
                txt.append(", ").append(botte);
            }
        }
        if (!pileBataille.isEmpty()) {
            txt.append(", ").append(pileBataille.peek());
        }
        return txt.toString();
    }

}
