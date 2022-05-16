package nc.unc.gl.borne.metier;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.communication.PushMode;
import nc.unc.gl.borne.gui.PlateauUI;

import java.util.List;


public class App extends Thread{

    public static App app;


    public Jeu jeu;
    public PlateauUI plateauJoueur1;
    public PlateauUI plateauJoueur2;


    public static void creerApp(){
        if(app == null) {
            app = new App();
        }
    }
    public static App getApp(){
        return app;
    }

    public App(){
        jeu = new Jeu();
    }

    public void AddPlateauUI(PlateauUI plateauUI){
        if(plateauJoueur1 == null){
            plateauJoueur1=plateauUI;
            return;
        }
        if(plateauJoueur2 == null){
            plateauJoueur2=plateauUI;
        }
    }

    public void rendering(){
        plateauJoueur1.getUI().ifPresent(ui -> {
            ui.access(()->{
                plateauJoueur1.displayPlateauUI();
                ui.push();
            });
        });
        plateauJoueur2.getUI().ifPresent(ui -> {
            ui.access(()->{
                plateauJoueur2.displayPlateauUI();
                ui.push();
            });
        });
    }
    public void run() {

        while(plateauJoueur1 == null || plateauJoueur2 == null){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        jeu.prepareJeu();
        rendering();


        while (!(jeu.estPartieFinie())) {
            jeu.joue();
        }

        //System.out.println("La partie est finie.");

        List<Joueur> gagnants = jeu.getGagnant();
        if (gagnants.size() == 1) {
            //System.out.println("Le gagnant est "+gagnants.get(0).nom+" !");
        } else {
            //System.out.println("Les gagnants ex æquo sont :");
            for (Joueur j: gagnants) {
                System.out.println("- "+j.nom);
            }
        }
    }


}
