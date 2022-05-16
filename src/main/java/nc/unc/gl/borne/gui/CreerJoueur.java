package nc.unc.gl.borne.gui;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import nc.unc.gl.borne.metier.App;
import nc.unc.gl.borne.metier.Jeu;
import nc.unc.gl.borne.metier.Joueur;

public class CreerJoueur extends VerticalLayout {


    private TextField champ_joueur_1 = new TextField("Nom joueur 1");

    private TextField champ_joueur_2 = new TextField("Nom joueur 2");

    private Button bouton_jouer = new Button("Jouer");

    public CreerJoueur(){


        bouton_jouer.addClickListener(event -> {
            App.creerApp();
            String nomJoueur1 = champ_joueur_1.getValue();
            String nomJoueur2 = champ_joueur_2.getValue();

            App.app.jeu.ajouteJoueurs(new Joueur(nomJoueur1), new Joueur(nomJoueur2));
            App.app.start();

            getUI().ifPresent(ui -> {
                ui.getPage().open("http://127.0.0.1:8080/jeu/"+nomJoueur2, "_blank");
                ui.getPage().setLocation("/jeu/"+nomJoueur1);
            });
        });

        this.add(champ_joueur_1, champ_joueur_2, bouton_jouer);
    }

}
