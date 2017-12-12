/**
 * Created by Pape Ndour on 25/10/2017.
 */

import diti4.model.Users;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainRun extends Application {


public static Users usersGlobal ;
    @Override
    public void start(Stage primaryStage) throws IOException {

        // StackPane root = new StackPane();
        AnchorPane root = new AnchorPane();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/diti4/view/FLogin.fxml"));
        // loader.setLocation(Class.getResource("/diit4/view/FLogin.fxml"));
       // dd("debut"+getClass()+"fin");
        root = (AnchorPane)loader.load();
        Scene scene = new Scene(root,500,350);
        primaryStage.setTitle("GESTION BANQUAIRE");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
    public static void dd(String chaine)
    {
        System.out.println(chaine);
    }

}
