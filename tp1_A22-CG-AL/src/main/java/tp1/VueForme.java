package tp1;


import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Vue pour le Tp1 sur les équations
 *
 * @author Adam Lidam Christophe Guerin
 * version avec les équations
 */
public class VueForme {

    /**
     * Couleur des bordes
     */
    private final static Border BORDER = new Border(
            new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null, null));
    /**
     * Nombre de points dans les graphiques
     */
    public static final int NUMBER_OF_DATA = 5;
    /**
     * Largeur de la scene
     */
    public static final int LARGEUR_SCENE = 600;
    /**
     * Hauteur de la Scene
     */
    public static final int HATEUR_SCENE = 500;
    /**
     * La hauteur des images au niveau superieur de la scene
     */
    public static final int TOP_IMAGE_HAUTEUR = 40;
    /**
     * La Largeur des images au niveau superieur de la scene
     */
    public static final int TOP_IMAGE_LARGEUR = 100;
    /**
     * Taille de base des images de la grid pane de gauche
     */
    public static final int IMAGE_GAUCHE_TAILLE_SIMPLE = 50;
    /**
     * L'espace entre les boutons au bas de la scene
     */
    public static final int ESPACE_ENTRE_BOUTONS_BAS = 40;
    /**
     * Spacing verticale des boutons du graphique
     */
    public static final int ESPACE_VERTICAL_ENTRE_DONNEES_DU_GRAPHIQUE = 10;
    /**
     * La largeur minimale des etiquette de la création des graphique
     */
    public static final int LARGEUR_MIN_ETIQUETTE_DONNEES = 20;
    /**
     * Largeur minimale des textfield qui prennent les donnés pour la creation des graphiques
     */
    public static final int LARGEUR_MIN_TEXTFIELD_DONNEES = 40;
    /**
     * Spacing entre les données x et y du compilateur de donnée
     */
    public static final int EXPACEMENT_ENTRE_X_Y = 10;
    /**
     * Spacing des images au niveau superieur de la scene
     */
    public static final int ESPACE_ENTRE_IMAGE_HAUT = 5;
    /**
     * Largeur minimale des image au niveau superieur de la scène
     */
    public static final double LARGEUR_MIN_SECTION_HAUT = 200.0;

    /**
     * Tile pane dans le quelle se trouve les graphiques générés
     */
    public TilePane graphTile = new TilePane();


    /**
     * Crée la scene de l'application en combinant toutes les differentes panes
     *
     * @return la scene
     */
    public Scene getScene() {


        //Instancie la root (BorderPane)
        BorderPane root = new BorderPane();

        //Instancie le pane du centre par une gridpane
        GridPane imgSce = getPaneImageScience("./src/res/tp1");

        //Instancie la Pane du haut par une Hbox
        HBox topBox = getTopRow("./travail/images");

        //Instancie la pane du bas par une Hbox
        HBox bottomBox = getBotRow();

        //Instancie la plane de droite qui sert a la creation de graphique par une Vbox
        VBox graphCreator = getGraphCreator();

        //Ajoute les border au pane demander
        bottomBox.setBorder(BORDER);

        //Conforme la pane de droite au spacing et padding demandés
        graphCreator.setSpacing(20);
        graphCreator.setPadding(new Insets(20, 20, 20, 20));


        //ajoute les differentes pane à la root
        root.setTop(topBox);
        root.setLeft(imgSce);
        root.setBottom(bottomBox);
        root.setRight(graphCreator);
        root.setCenter(graphTile);

        return new Scene(root, LARGEUR_SCENE, HATEUR_SCENE);
    }

    /**
     * Ajoute un graphique a la tileList
     *
     * @param parameters les paramettre du graphique a crée
     */
    public void ajouterGraphique(Grapher.Parameters parameters) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        //Crée les titres des axes
        xAxis.setLabel("x");
        yAxis.setLabel("y");

        //Création du graphique
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.getData().add(new Grapher().createGraph(parameters));
        lineChart.setPrefSize(200.0, 150.0);

        //Ajoute le graphique
        graphTile.getChildren().add(lineChart);
    }

    /**
     * Création de la Vbox contenant les setters du graphique et les boutons de gestion de la création des graphique
     *
     * @return la Vbox de Gestion de graphique
     */
    public VBox getXYBoxDonner() {
        VBox vBox = new VBox();

        //Crée et ajoute les differentes lignes de setter de données du graphique
        for (int i = 0; i < NUMBER_OF_DATA; i++) {
            vBox.getChildren().add(getXYSetter(i));
        }

        Button addGraph = new Button("Ajouter un graphique");

        //Creation de l'handler lorsqu'on veut ajouter un graphique
        addGraph.setOnAction((EventHandler) -> {
            List<Double> xList = new ArrayList<>();
            List<Double> yList = new ArrayList<>();

            //Recolte les données x et y du graphique crée
            for (int i = 0; i < NUMBER_OF_DATA; i++) {
                HBox hBox = (HBox) vBox.getChildren().get(i);

                TextField xVal = (TextField) hBox.getChildren().get(1);
                TextField yVal = (TextField) hBox.getChildren().get(3);

                xList.add(Double.parseDouble(xVal.getText()));
                yList.add(Double.parseDouble(yVal.getText()));
            }

            ajouterGraphique(new Grapher.Parameters(xList, yList, "Maurice"));
        });
        Button delGraph = new Button("Effacer les graphiques");

        //Création du handler pour effacer tous les graphique
        delGraph.setOnAction(event -> graphTile.getChildren().clear());

        //ajoute les boutons dans la vbox de droite
        vBox.getChildren().addAll(addGraph, delGraph);

        //Conformisation de la mise en page
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(ESPACE_VERTICAL_ENTRE_DONNEES_DU_GRAPHIQUE);
        vBox.setPadding(new Insets(20, 20, 20, 20));

        vBox.setBorder(new Border(new BorderStroke(Color.DARKBLUE, BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(2), null)));

        return vBox;
    }


    /**
     * Creation de la Hbox dans la quelle les données x et y de la row indiqué sont contenue
     *
     * @param row la ligne de donnés (x et y) a crée
     * @return La Hbox des setter de donnée (x et y)
     */
    public HBox getXYSetter(int row) {
        HBox hBox = new HBox();

        //instanciation des élementes de la row
        Label labX = new Label("x" + row);
        labX.setMinWidth(LARGEUR_MIN_ETIQUETTE_DONNEES);
        Label labY = new Label("y" + row);
        labY.setMinWidth(LARGEUR_MIN_ETIQUETTE_DONNEES);

        TextField txtX = new TextField();
        txtX.setMinWidth(LARGEUR_MIN_TEXTFIELD_DONNEES);
        txtX.setText(String.valueOf(row));
        txtX.setMaxWidth(40);
        TextField txtY = new TextField();
        txtY.setMinWidth(LARGEUR_MIN_TEXTFIELD_DONNEES);
        txtY.setText(String.valueOf(row));
        txtY.setMaxWidth(40);

        //ajout et conformisation des elements dans la row (Hbox)
        hBox.getChildren().addAll(labX, txtX, labY, txtY);
        hBox.setSpacing(EXPACEMENT_ENTRE_X_Y);

        hBox.setAlignment(Pos.CENTER);

        return hBox;
    }

    /**
     * Creation de la Vbox de droite dans la quelle se trouve les auteurs ainsi que les Vbox de création de graphiques
     *
     * @return La Vbox de droite
     */
    public VBox getGraphCreator() {
        VBox vbox = new VBox();


        //Creation de la section des auteurs
        Label labelAuteurs = new Label("Auteurs");
        ListView<String> auteurs = new ListView<>();
        ObservableList<String> names = FXCollections.observableArrayList("Christophe Guérin", "Adam Lidam");

        auteurs.getItems().addAll("Christophe Guérin", "Adam Lidam");
        auteurs.minHeightProperty().bind(Bindings.size(names).multiply(24));
        auteurs.maxHeightProperty().bind(Bindings.size(names).multiply(48));

        //Conformisation de la mise en page des auteurs
        auteurs.maxHeight(20.0);

        vbox.setVgrow(auteurs, Priority.ALWAYS);

        vbox.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        //ajoutes des composante de la vbox de droite
        vbox.getChildren().addAll(labelAuteurs, auteurs, getXYBoxDonner());

        //Conformisation de la mise en page de la vbox de droite
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }


    /**
     * Création de la Hbox du bas
     * @see ActionGenerer
     * @return la Hbox du bas
     */
    public HBox getBotRow() {
        HBox hbox = new HBox();

        //Creation de l'actionGenerer
        EventHandler<ActionEvent> showAlert = new ActionGenerer();

        //Instanciation des boutons
        Button generer = new Button("Générer");
        Button reini = new Button("Rénitialiser");
        Button quitter = new Button("Quitter");


        //Conformisation des size des boutons
        generer.setMaxWidth(350);
        reini.setMaxWidth(350);
        quitter.setMaxWidth(350);

        generer.setMinWidth(50);
        reini.setMinWidth(50);
        quitter.setMinWidth(50);

        generer.setPrefWidth(75);
        reini.setPrefWidth(75);
        quitter.setPrefWidth(75);

        //setter des action des boutons
        generer.setOnAction(showAlert);
        reini.setOnAction(showAlert);
        quitter.setOnAction(showAlert);

        //ajout de boutons dans la hbox du bas
        hbox.getChildren().addAll(generer, reini, quitter);

        //Conformisation de la mise en page de la hbox du bas
        hbox.setSpacing(ESPACE_ENTRE_BOUTONS_BAS);

        hbox.setHgrow(generer, Priority.ALWAYS);
        hbox.setHgrow(reini, Priority.ALWAYS);
        hbox.setHgrow(quitter, Priority.SOMETIMES);

        return hbox;
    }

    /**
     * Creation de la Hbox du haut dans le quelle se trouve des images
     *
     * @param path le path du ficher dans lequel se trouve les images
     * @return La hbox du haut
     */
    public HBox getTopRow(String path) {
        HBox hbox = new HBox();

        //Instanciation du dossier
        File dossier = new File(path);
        File[] files = dossier.listFiles();

        //Parcour toutes le images du fichier et les ajoutes dans la hbox du haut
        assert files != null;
        for (File file : files) {
            ImageView imageView = new ImageView(new Image(file.toURI().toString()));
            imageView.setFitHeight(TOP_IMAGE_HAUTEUR);
            imageView.setFitWidth(TOP_IMAGE_LARGEUR);
            hbox.getChildren().add(imageView);
        }
        //Conformisation de la mise en page
        hbox.setSpacing(ESPACE_ENTRE_IMAGE_HAUT);
        hbox.setMinWidth(LARGEUR_MIN_SECTION_HAUT);

        return hbox;
    }

    /**
     * Creation de la gridPane qui contient les 5 images
     *
     * @param path le ficher dans lequel se trouve les images
     * @return La gridePane de gauche
     */
    public GridPane getPaneImageScience(String path) {
        GridPane gridPane = new GridPane();

        File dossier = new File(path);
        File[] files = dossier.listFiles();
        //Instanciation de tous les images du fichier et leur conformisation
        assert files != null;
        ImageView image1 = new ImageView(new Image(files[0].toURI().toString()));
        image1.setFitHeight(IMAGE_GAUCHE_TAILLE_SIMPLE);
        image1.setFitWidth(IMAGE_GAUCHE_TAILLE_SIMPLE);
        ImageView image2 = new ImageView(new Image(files[1].toURI().toString()));
        image2.setFitHeight(IMAGE_GAUCHE_TAILLE_SIMPLE);
        image2.setFitWidth(IMAGE_GAUCHE_TAILLE_SIMPLE);
        ImageView image3 = new ImageView(new Image(files[2].toURI().toString()));
        image3.setFitHeight(IMAGE_GAUCHE_TAILLE_SIMPLE * 2);
        image3.setFitWidth(IMAGE_GAUCHE_TAILLE_SIMPLE);
        ImageView image4 = new ImageView(new Image(files[3].toURI().toString()));
        image4.setFitHeight(IMAGE_GAUCHE_TAILLE_SIMPLE * 2);
        image4.setFitWidth(IMAGE_GAUCHE_TAILLE_SIMPLE * 2);
        ImageView image5 = new ImageView(new Image(files[4].toURI().toString()));
        image5.setFitHeight(IMAGE_GAUCHE_TAILLE_SIMPLE);
        image5.setFitWidth(IMAGE_GAUCHE_TAILLE_SIMPLE * 2);
        //Ajout des images dans le gridPane
        gridPane.add(image1, 0, 0, 1, 1);
        gridPane.add(image2, 0, 1, 1, 1);
        gridPane.add(image3, 1, 0, 1, 2);
        gridPane.add(image4, 0, 3, 2, 1);
        gridPane.add(image5, 0, 2, 2, 1);

        return gridPane;
    }
}

