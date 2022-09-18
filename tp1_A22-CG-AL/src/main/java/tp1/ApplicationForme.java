package tp1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Classe contrôleur pour le Tp1
 * @author Adam Lidam Christophe Guerin
   @version 1.0
 *
 */
public class ApplicationForme extends Application {
	/**
	 * La vueForme qui contient la scene
	 * @see VueForme
	 */
	VueForme vue;

	/**
	 * lanceur et Créateur du stage
	 * @param stage le stage qui va être affiché
	 * @throws Exception
	 */
	@Override
	public void start(Stage stage) throws Exception {
		// Instancier la vue
		vue = new VueForme();

		stage.setTitle("TP1 Graphiques A22");

		// Joindre la vue au stage
		Scene scene = vue.getScene();
		scene.setFill(Color.web("F5F5F5"));
		stage.setScene(scene);

		// Mettre le stage au size de la scene
		stage.setMinHeight(scene.getHeight() + 25);
		stage.setMinWidth(scene.getWidth() - 120);

		stage.setMaxWidth(1140);
		stage.show();

	}

}
