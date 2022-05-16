package nc.unc.gl.borne.gui;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.communication.PushMode;
import nc.unc.gl.borne.metier.App;
import nc.unc.gl.borne.metier.Carte;
import nc.unc.gl.borne.metier.Joueur;

import java.util.ArrayList;


@Route("/jeu")
public class PlateauUI extends Div implements HasUrlParameter<String>{

    private App app;
    public String nomJoueur;

    public Joueur joueur;

    public Joueur adversaire;



    HorizontalLayout layoutMain = new HorizontalLayout();
    ArrayList<Image> main = new ArrayList<>(7);
    ArrayList<Image> mainAdversaire = new ArrayList<>(6);
    ArrayList<Image> bottes = new ArrayList<>(4);
    ArrayList<Image> bottesAdversaire = new ArrayList<>(4);
    HorizontalLayout layoutMainAdversaire = new HorizontalLayout();
    HorizontalLayout layoutPlateau = new HorizontalLayout();
    VerticalLayout layoutPioche = new VerticalLayout();
    HorizontalLayout layoutBataille = new HorizontalLayout();
    VerticalLayout layoutDansBatailleAttaque = new VerticalLayout();
    VerticalLayout layoutDansBatailleVitesse = new VerticalLayout();
    VerticalLayout layoutDansBatailleLimitation = new VerticalLayout();
    VerticalLayout layoutDansBatailleCompteur = new VerticalLayout();
    VerticalLayout layoutBotte = new VerticalLayout();
    HorizontalLayout layoutDansBotteHaut = new HorizontalLayout();
    HorizontalLayout layoutDansBotteBas = new HorizontalLayout();


    public void initPlateau(){
        app = App.getApp();
        joueur = app.jeu.getJoueurByName(nomJoueur);
        adversaire = app.jeu.getAdversaireByName(nomJoueur);
        app.AddPlateauUI(this);
        displayPlateauUI();
    }
    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        nomJoueur = s;
        initPlateau();
    }

    public PlateauUI(){
        this.getStyle().set("background-image", "url(images/tapis.jpg) ");
        this.getStyle().set("height", "100%");
        this.getStyle().set("width", "100%");
        this.getStyle().set("background-size", "cover");
    }

    public void generateMain(){
        main.clear();
        if(joueur.getMain().isEmpty()){
            for(int i = 0; i < 6; i++){
                main.add(createImageCarteBack());
            }
            return;
        }

        for(Carte carte : joueur.getMain()){
            try {
                main.add(createImageCarte(carte));
            }
            catch(Exception e){
                main.add(createImageCarteBack());
            }
        }
    }

    public void generateMainAdversaire(){

        mainAdversaire.clear();
        for(int i = 0; i < 6; i++){
            mainAdversaire.add(createImageCarteBack());
        }
    }

    public void generateBottes(){
        bottes.clear();
        for(Carte carte : joueur.getBottes()){
            bottes.add(createImageCarte(carte));
        }
    }

    public void generateBottesAdversaire(){
        bottesAdversaire.clear();
        for(Carte carte : adversaire.getBottes()){
            bottesAdversaire.add(createImageCarte(carte));
        }
    }

    public void cleanUI(){

        layoutMain.removeAll();
        layoutMainAdversaire.removeAll();
        layoutPioche.removeAll();
        layoutDansBotteHaut.removeAll();
        layoutDansBotteBas.removeAll();
        layoutDansBatailleAttaque.removeAll();
        layoutDansBatailleVitesse.removeAll();
        layoutDansBatailleLimitation.removeAll();
        layoutDansBatailleCompteur.removeAll();
        layoutBataille.removeAll();
    }


    public void displayPlateauUI(){

        cleanUI();

        //Cartes de la main du joueur

        generateMain();

        //Cartes de la main du joueur adverse

        generateMainAdversaire();


        //Cartes bottes du joueur

        generateBottes();


        //Cartes bottes de l'adversaire

        generateBottesAdversaire();


        //Piles de bataille du joueur

        Image imagePileAttaque = createImageCarteBack();

        Image imagePileVitesse = createImageCarteBack();

        Image imagePileLimitation = createImageCarteBack();

        IntegerField compteur = new IntegerField();
        compteur.setLabel(joueur.getNom() + " : ");
        compteur.setValue(75);
        compteur.setReadOnly(true);
        compteur.getStyle().set("size","5%");

        //Piles de bataille de l'adversaire

        Image imagePileAttaqueAdversaire = createImageCarteBack();

        Image imagePileVitesseAdversaire = createImageCarteBack();

        Image imagePileLimitationAdversaire = createImageCarteBack();

        IntegerField compteurAdversaire = new IntegerField();
        compteurAdversaire.setLabel(adversaire.getNom()+" : ");
        compteurAdversaire.setValue(200);
        compteurAdversaire.setReadOnly(true);
        compteurAdversaire.getStyle().set("size","5%");

        //Pioche et défausse

        Image imagePioche = createImageCarteBack();

        Image imageDefausse = createImageCarteBack();

        //Layout pioche et défausse

        layoutPioche.add(imageDefausse, imagePioche);
        layoutPioche.setAlignItems(Alignment.CENTER);
        layoutPioche.setHeight("600px");
        layoutPioche.setSpacing(true);
        layoutPioche.setJustifyContentMode(JustifyContentMode.EVENLY);
        layoutPioche.getStyle().set("border","solid white");
        layoutPioche.getStyle().set("margin-top","2%");
        layoutPioche.getStyle().set("margin-bottom","2%");
        layoutPioche.getStyle().set("border-radius","30px");


        //Layout bataille


        layoutDansBatailleAttaque.add(imagePileAttaqueAdversaire, imagePileAttaque);
        layoutDansBatailleAttaque.setJustifyContentMode(JustifyContentMode.EVENLY);
        layoutDansBatailleVitesse.add(imagePileVitesseAdversaire, imagePileVitesse);
        layoutDansBatailleVitesse.setJustifyContentMode(JustifyContentMode.EVENLY);
        layoutDansBatailleLimitation.add(imagePileLimitationAdversaire, imagePileLimitation);
        layoutDansBatailleLimitation.setJustifyContentMode(JustifyContentMode.EVENLY);
        layoutDansBatailleCompteur.add(compteurAdversaire, compteur);
        layoutDansBatailleCompteur.setJustifyContentMode(JustifyContentMode.EVENLY);
        layoutBataille.add(layoutDansBatailleAttaque, layoutDansBatailleVitesse, layoutDansBatailleLimitation, layoutDansBatailleCompteur);
        layoutBataille.setHeight("600px");
        layoutBataille.setSpacing(true);
        layoutBataille.setJustifyContentMode(JustifyContentMode.EVENLY);
        layoutBataille.getStyle().set("border","solid white");
        layoutBataille.getStyle().set("margin-top","2%");
        layoutBataille.getStyle().set("margin-bottom","2%");
        layoutBataille.getStyle().set("border-radius","30px");

        //Layout bottes


        for(Image image : bottes){
            layoutDansBotteBas.add(image);
        }


        for(Image image : bottes){
            layoutDansBotteHaut.add(image);
        }
        layoutBotte.add(layoutDansBotteHaut, layoutDansBotteBas);
        layoutBotte.setHeight("600px");
        layoutBotte.setSpacing(true);
        layoutBotte.setJustifyContentMode(JustifyContentMode.EVENLY);
        layoutBotte.setAlignItems(Alignment.CENTER);
        layoutBotte.getStyle().set("border","solid white");
        layoutBotte.getStyle().set("margin-top","2%");
        layoutBotte.getStyle().set("margin-bottom","2%");
        layoutBotte.getStyle().set("border-radius","30px");

        //Layout main du joueur


        for (Image image : main) {
            layoutMain.add(image);
        }
        layoutMain.setJustifyContentMode(JustifyContentMode.CENTER);
        layoutMain.setSpacing(true);
        layoutMain.getStyle().set("border","solid white");
        layoutMain.getStyle().set("border-radius","30px");



        //Layout main de l'adversaire


        for (Image image : mainAdversaire) {
            layoutMainAdversaire.add(image);
        }
        layoutMainAdversaire.setJustifyContentMode(JustifyContentMode.CENTER);
        layoutMainAdversaire.setSpacing(true);
        layoutMainAdversaire.getStyle().set("border","solid white");
        layoutMainAdversaire.getStyle().set("border-radius","30px");

        //Layout plateau

        layoutPlateau.add(layoutPioche, layoutBataille, layoutBotte);
        layoutPlateau.setSpacing(true);

        this.add(layoutMainAdversaire, layoutPlateau, layoutMain);
    }


    private Image createImageCarteBack(){
        Image image = new Image("images/back.png", "back");
        image.setWidth("70px");
        image.setHeight("110px");
        image.addClickListener(event ->{
        });
        return image;
    }

    private Image createImageCarte(Carte carte){
        Image image;
        if(carte != null){
            image = new Image(carte.getSourceImage(), carte.getClass().getSimpleName());
            image.setWidth("70px");
            image.setHeight("110px");
        }
        else {
            image = createImageCarteBack();
        }
        return image;
    }

}


