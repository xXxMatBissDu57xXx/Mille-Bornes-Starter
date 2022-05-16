package nc.unc.gl.borne;

import java.util.ArrayList;
import java.util.Collections;

import nc.unc.gl.borne.cartes.Attaque;
import nc.unc.gl.borne.cartes.Botte;
import nc.unc.gl.borne.cartes.bottes.Prioritaire;

public class Deck {
    private Pile pileBataille;
    public int km;
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

    public int getDistance() {
        return km;
    }

    public void ajouteDistance(int km) throws IllegalStateException {
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

    public boolean hasLimiteVitesse(){
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

    public void defausserCarteOnTopOfBataille(Jeu jeu){
        jeu.defausserCarte(pileBataille.pop());
    }

    public void defausserCarteOnTopOfVitesse(Jeu jeu){
        jeu.defausserCarte(pileVitesse.pop());
    }

    public void defausseCarteDeMain(Jeu jeu, int numero) {
        jeu.defausserCarte(main.get(numero));
        main.remove(numero);
    }

    public void attaque(Jeu jeu, Attaque carte) throws IllegalStateException {
        carte.jouerCarte(jeu, this);
    }

    public void joueCarte(Jeu jeu, int numero) throws IllegalStateException {
        Carte carte = main.get(numero);
        if (carte instanceof Attaque) {
            throw new IllegalStateException("La carte est une attaque donc il faut spécifier un adversaire.");
        }

        carte.jouerCarte(jeu, this);
        main.remove(numero);
    }

    public void joueCarteAttaque(Jeu jeu, int numero, Joueur adversaire) throws IllegalStateException {
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
        if (hasLimiteVitesse()) {
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
