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
            System.out.print("\nÀ combien voulez-vous jouer ?\n> ");
            this.nombreJoueurs = scanInt();
            if (this.nombreJoueurs <= 1 || this.nombreJoueurs > 4) {
                System.out.println("Erreur : Il ne peut y avoir qu'entre 2 et 4 joueurs.");
            }
        }
        System.out.print("Il y a "+this.nombreJoueurs+" joueurs");
    }

    public void configurerJoueurs() {
        for (int i = 0; i < (this.nombreJoueurs); i++) {
            System.out.print("\nQuel nom voulez-vous pour le joueur n°"+(i+1)+" ?\nLes espaces ne sont pas pris en compte.\n> ");
            jeu.ajouteJoueurs(new Joueur(input.next()));
        }
    }

    public void jouer() {
        System.out.println("Bienvenue dans cette nouvelle partie de Mille Bornes.");
        initialiserPartie();
        configurerJoueurs();
        jeu.prepareJeu();

        System.out.println("\n----------------------------------- C'est parti ! -----------------------------------\n");

        while (!(jeu.estPartieFinie())) {
            jeu.joue();
        }

        System.out.println("La partie est finie.");

        List<Joueur> gagnants = jeu.getGagnant();
        if (gagnants.size() == 1) {
            System.out.println("Le gagnant est "+gagnants.get(0).nom+" !");
        } else {
            System.out.println("Les gagnants ex æquo sont :");
            for (Joueur j: gagnants) {
                System.out.println("- "+j.nom);
            }
        }
    }
}


