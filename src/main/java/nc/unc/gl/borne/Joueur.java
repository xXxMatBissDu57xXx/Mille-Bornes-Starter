package nc.unc.gl.borne;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import nc.unc.gl.borne.cartes.Attaque;
import nc.unc.gl.borne.cartes.Botte;

public class Joueur {
    public final String nom;
    private Joueur prochainJoueur;

    private final Deck deck;
    private final Scanner input;


    public Joueur(String nom) {
        this.nom = nom;
        this.deck = new Deck(this);
        this.input = new Scanner(System.in);
    }

    public Joueur getProchainJoueur(){
        return prochainJoueur;
    }

    public void setProchainJoueur(Joueur prochainJoueur){
        this.prochainJoueur = prochainJoueur;
    }

    public ArrayList<Carte> getMain() {
        return deck.getMain();
    }

    public int getDistance() {
        return deck.getDistance();
    }

    public void prendCarte(Carte carte) throws IllegalStateException {
        deck.prendCarte(carte);
    }

    public int scanInt(){
        try {
            return this.input.nextInt();
        }
        catch(InputMismatchException me){
            this.input.next();
            return 0;
        }
    }

    public int choisitCarte() {
        ArrayList<Carte> main = getMain();
        System.out.println("\nChoisissez la carte à jouer :");
        for (int i = 0; i < main.size(); i++) {
            System.out.println((i+1)+" : "+main.get(i));
        }
        System.out.println("(Rajoutez '-' devant le numéro de la carte pour la défausser)");
        System.out.print("> ");
        return this.scanInt();
    }

    public Joueur choisitAdversaire(Attaque carte) throws IllegalStateException {
        Joueur j = getProchainJoueur();
        int i = 0;
        ArrayList<Joueur> adversaires = new ArrayList<Joueur>();

        System.out.println("\nChoisissez l'adversaire à attaquer :");
        while (j != this) {
            System.out.println((i+1)+" : "+j.nom);
            adversaires.add(j);
            i++;
            j = j.getProchainJoueur();
        }
        System.out.println("0 : annuler l'attaque");

        int choix = -1;
        while (choix < 0 || choix > i) {

            System.out.print("> ");
            choix = this.scanInt();
            if (choix == 0) {
                throw new IllegalStateException("Vous avez annulé l'attaque.");
            }
            else if (choix < 0 || choix > i) {
                System.out.println("Choisissez un nombre de la liste ci-dessus.");
            }

        }
        return adversaires.get(choix-1);
    }

    public void joueCarte(Jeu jeu, int numero) throws IllegalStateException {
        Carte carte = getMain().get(numero);
        if (carte instanceof Attaque) {
            deck.joueCarteAttaque(jeu, numero, choisitAdversaire((Attaque) carte));
        } else {
            deck.joueCarte(jeu, numero);
        }
    }

    public void defausseCarte(Jeu jeu, int numero) {
        deck.defausseCarteDeMain(jeu, numero);
    }

    public void attaque(Jeu jeu, Attaque carte) throws IllegalStateException {
        deck.attaque(jeu, carte);
    }

    public void choisitCoupFourre(Jeu jeu, Attaque attaque, int numero) throws IllegalStateException {
        Carte carte = getMain().get(numero);
        if (!(carte instanceof Botte)) {
            throw new IllegalStateException(carte+" ne permet de faire un coup fourré contre "+attaque);
        }

        String choix = "";
        System.out.println("\n"+nom+", on t'attaque avec "+attaque+" mais tu as "+carte+". Veux-tu poser ta botte maintenant ?");

        while (!(choix.equalsIgnoreCase("OUI") || choix.equalsIgnoreCase("NON"))) {
            System.out.print("(oui/non) > ");
            choix = input.next();
        }

        if (choix.equalsIgnoreCase("OUI")) {
            System.out.println("Coup fourré !");
            joueCarte(jeu, numero);
            prendCarte(jeu.piocherCarte());
            jeu.setProchainJoueur(this);
        }
    }

    public String toString() {
        return nom+" : "+deck.toString();
    }

}
