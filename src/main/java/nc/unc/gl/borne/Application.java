package nc.unc.gl.borne;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Application {
    private Scanner input;
    private Jeu jeu;
    private int nombreJoueurs;

    public Application() {
        this.input = new Scanner(System.in);
        this.jeu = new Jeu();
        this.nombreJoueurs = 0;
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
    public void initialiserPartie() {
        while (this.nombreJoueurs <= 1 || this.nombreJoueurs > 4) {
            System.out.print("\ncombien de joueur ?\n> ");
            this.nombreJoueurs = scanInt();
            if (this.nombreJoueurs <= 1 || this.nombreJoueurs > 4) {
                System.out.println("Il ne peut y avoir qu'entre 2 et 4 joueurs.");
            }
        }
        System.out.print("Il y a "+this.nombreJoueurs+" joueurs");
    }

    public void configurerJoueurs() {
        for (int i = 0; i < (this.nombreJoueurs); i++) {
            System.out.print("\nSaisissez le nom du joueur "+(i+1)+" ?\n");
            jeu.ajouteJoueurs(new Joueur(input.next()));
        }
    }

    public void jouer() {
        initialiserPartie();
        configurerJoueurs();
        jeu.prepareJeu();

        System.out.println("\nLa partie est lancé\n");

        while (!(jeu.estPartieFinie())) {
            jeu.joue();
        }

        System.out.println("La partie est finie.");

        List<Joueur> gagnants = jeu.getGagnant();
        if (gagnants.size() == 1) {
            System.out.println("Le gagnant est "+gagnants.get(0).nom+" !");
        } else {
            System.out.println("Les gagnants à égalités sont :");
            for (Joueur j: gagnants) {
                System.out.println("- "+j.nom);
            }
        }
    }
}


