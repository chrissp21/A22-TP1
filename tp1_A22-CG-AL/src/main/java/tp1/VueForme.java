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
 * @author Martin Simoneau
 * version avec les équations
 */
public class VueForme {

    private final static Border BORDER = new Border(
            new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null, null));
    public static final int NUMBER_OF_DATA = 5;
    public static final int LARGEUR_SCENE = 600;
    public static final int HATEUR_SCENE = 500;
    public static final int TOP_IMAGE_HAUTEUR = 40;
    public static final int TOP_IMAGE_LARGEUR = 100;
    public static final int IMAGE_GAUCHE_TAILLE_SIMPLE = 50;
    public static final int ESPACE_ENTRE_BOUTONS_BAS = 40;
    public static final int ESPACE_VERTICAL_ENTRE_DONNEES_DU_GRAPHIQUE = 10;
    public static final int LARGEUR_MIN_ETIQUETTE_DONNEES = 20;
    public static final int LARGEUR_MIN_TEXTFIELD_DONNEES = 40;
    public static final int EXPACEMENT_ENTRE_X_Y = 10;
    public static final int ESPACE_ENTRE_IMAGE_HAUT = 5;
    public static final double LARGEUR_MIN_SECTION_HAUT = 200.0;


    public TilePane graphTile = new TilePane();

    public Scene getScene() {
        BorderPane root = new BorderPane();
        GridPane imgSce = getPaneImageScience("./src/res/tp1");
        HBox topBox = getTopRow("./travail/images");
        HBox bottomBox = getBotRow();
        VBox graphCreator = getGraphCreator();

        bottomBox.setBorder(BORDER);
        graphCreator.setSpacing(20);
        graphCreator.setPadding(new Insets(20, 20, 20, 20));

        root.setTop(topBox);
        root.setLeft(imgSce);
        root.setBottom(bottomBox);
        root.setRight(graphCreator);
        root.setCenter(graphTile);

        return new Scene(root, LARGEUR_SCENE, HATEUR_SCENE);
    }

    public void ajouterGraphique(Grapher.Parameters parameters) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("x");
        yAxis.setLabel("y");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.getData().add(new Grapher().createGraph(parameters));
        lineChart.setPrefSize(200.0, 150.0);
        graphTile.getChildren().add(lineChart);
    }

    public VBox getXYBoxDonner() {
        VBox vBox = new VBox();

        for (int i = 0; i < NUMBER_OF_DATA; i++) {
            vBox.getChildren().add(getXYSetter(i));
        }

        Button addGraph = new Button("Ajouter un graphique");
        addGraph.setOnAction((EventHandler) -> {
            List<Double> xList = new ArrayList<>();
            List<Double> yList = new ArrayList<>();

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
        delGraph.setOnAction(event -> graphTile.getChildren().clear());

        vBox.getChildren().addAll(addGraph, delGraph);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(ESPACE_VERTICAL_ENTRE_DONNEES_DU_GRAPHIQUE);
        vBox.setPadding(new Insets(20, 20, 20, 20));

        vBox.setBorder(new Border(new BorderStroke(Color.DARKBLUE, BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(2), null)));
        return vBox;
    }

    public HBox getXYSetter(int row) {
        HBox hBox = new HBox();

        Label labX = new Label("x" + row);
        labX.setMinWidth(LARGEUR_MIN_ETIQUETTE_DONNEES);
        Label labY = new Label("y" + row);
        labY.setMinWidth(LARGEUR_MIN_ETIQUETTE_DONNEES);

        TextField txtX = new TextField();
        txtX.setMinWidth(LARGEUR_MIN_TEXTFIELD_DONNEES);
        txtX.setMaxWidth(40);
        TextField txtY = new TextField();
        txtY.setMinWidth(LARGEUR_MIN_TEXTFIELD_DONNEES);
        txtY.setMaxWidth(40);

        hBox.getChildren().addAll(labX, txtX, labY, txtY);
        hBox.setSpacing(EXPACEMENT_ENTRE_X_Y);

        hBox.setAlignment(Pos.CENTER);

        return hBox;
    }

    public VBox getGraphCreator() {
        VBox vbox = new VBox();

        Label labelAuteurs = new Label("Auteurs");
        ListView<String> auteurs = new ListView<>();
        ObservableList<String> names = FXCollections.observableArrayList("Christophe Guérin", "Adam Lidam");

        auteurs.getItems().addAll("Christophe Guérin", "Adam Lidam");
        auteurs.minHeightProperty().bind(Bindings.size(names).multiply(24));
        auteurs.maxHeightProperty().bind(Bindings.size(names).multiply(48));

        auteurs.maxHeight(20.0);

        vbox.setVgrow(auteurs, Priority.ALWAYS);

        vbox.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        vbox.getChildren().addAll(labelAuteurs, auteurs, getXYBoxDonner());

        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }


    public HBox getBotRow() {
        HBox hbox = new HBox();

        EventHandler<ActionEvent> showAlert = new ActionGenerer();

        Button generer = new Button("Générer");
        Button reini = new Button("Rénitialiser");
        Button quitter = new Button("Quitter");

        generer.setMaxWidth(350);
        reini.setMaxWidth(350);
        quitter.setMaxWidth(350);

        generer.setMinWidth(50);
        reini.setMinWidth(50);
        quitter.setMinWidth(50);

        generer.setPrefWidth(75);
        reini.setPrefWidth(75);
        quitter.setPrefWidth(75);

        generer.setOnAction(showAlert);
        reini.setOnAction(showAlert);
        quitter.setOnAction(showAlert);

        hbox.getChildren().addAll(generer, reini, quitter);
        hbox.setSpacing(ESPACE_ENTRE_BOUTONS_BAS);

        hbox.setHgrow(generer, Priority.ALWAYS);
        hbox.setHgrow(reini, Priority.ALWAYS);
        hbox.setHgrow(quitter, Priority.SOMETIMES);

        return hbox;
    }

    public HBox getTopRow(String path) {
        HBox hbox = new HBox();

        File dossier = new File(path);
        File[] files = dossier.listFiles();

        assert files != null;
        for (File file : files) {
            ImageView imageView = new ImageView(new Image(file.toURI().toString()));
            imageView.setFitHeight(TOP_IMAGE_HAUTEUR);
            imageView.setFitWidth(TOP_IMAGE_LARGEUR);
            hbox.getChildren().add(imageView);
        }
        hbox.setSpacing(ESPACE_ENTRE_IMAGE_HAUT);
        hbox.setMinWidth(LARGEUR_MIN_SECTION_HAUT);

        return hbox;
    }

    public GridPane getPaneImageScience(String path) {
        GridPane gridPane = new GridPane();

        File dossier = new File(path);
        File[] files = dossier.listFiles();

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

        gridPane.add(image1, 0, 0, 1, 1);
        gridPane.add(image2, 0, 1, 1, 1);
        gridPane.add(image3, 1, 0, 1, 2);
        gridPane.add(image4, 0, 3, 2, 1);
        gridPane.add(image5, 0, 2, 2, 1);

        return gridPane;
    }
}

