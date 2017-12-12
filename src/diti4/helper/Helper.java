package diti4.helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Helper {

    public void redirect(AnchorPane container,String fileFxml) throws IOException {
        String path = "/diti4/view/" + fileFxml ;
        AnchorPane newContainer = new FXMLLoader(getClass().getResource(path)).load();
        Scene sene = new Scene(newContainer);
        Stage windows = (Stage)container.getScene().getWindow();
        windows.setScene(sene);
        windows.show();
    }

    public static void error(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("GESTION BANQUAIRE");
        alert.setHeaderText("Vous avez fait des erreurs");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void valide(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GESTION BANQUAIRE");
        alert.setHeaderText("Succes");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean confirm(String message)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("GESTION BANQUAIRE");
        alert.setHeaderText("Confirmation");
        alert.setContentText(message);
        Optional<ButtonType> r = alert.showAndWait();
        return r.get() == ButtonType.OK ;

    }

}
