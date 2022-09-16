


package tp1;

/**
 * Generateur des auteurs du Tp1
 *
 * @author Adam Lidam Christophe Guerin
 * @version 1.0
 */
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

public class ActionGenerer implements EventHandler<ActionEvent> {

    /**
     * Handler d'une alerte
     * @param event l'évènement
     */
    @Override
    public void handle(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("TP1 420-203 A20");
        alert.setHeaderText("Christophe Guérin\nAdam Lidam");
        alert.setContentText("La fonctionnalité n'est pas en service");
        alert.show();
    }
}
