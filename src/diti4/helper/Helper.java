package diti4.helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class Helper {

    public void redirect(Parent container, String fileFxml) throws IOException {
        String path = "/diti4/view/" + fileFxml ;
        Parent newContainer = new FXMLLoader(getClass().getResource(path)).load();
        Scene sene = new Scene(newContainer,770,700);
        Stage windows = (Stage)container.getScene().getWindow();
        windows.setScene(sene);
        windows.centerOnScreen();
        windows.show();
    }

    public void setBorderPane(BorderPane menu,String fileFxml ,int position) throws IOException {
        String path = "/diti4/view/" + fileFxml;
        try {
            Parent layout = new FXMLLoader(getClass().getResource(path)).load();
            if(position == 1)
            {
                menu.setCenter(layout);
            }
            else if(position == 2)
            {
                menu.setLeft(layout);
            }
            else if(position==3)
            {
                menu.setRight(layout);
            }
            else {
                menu.setBottom(layout);
            }
        } catch (IOException e) {
            throw e;
        }

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
    public static String currenDate()
    {
        Date actuelle = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(actuelle);
    }
}
