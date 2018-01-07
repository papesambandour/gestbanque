package diti4.controller;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import diti4.dao.DatabaseHelper;
import diti4.dao.UsersDOA;
import diti4.helper.Helper;
import diti4.model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FLoginControler implements Initializable {

    @FXML
    private Button valider;

    @FXML
    private PasswordField password;

    @FXML
    private TextField login;

    @FXML
    private Label info;
    @FXML
    public AnchorPane contenForm ;


    @FXML
    void connexionHanller(ActionEvent event) {

        try {
            String errorMessage = "";
            Pattern paternLogin = Pattern.compile("^([a-zA-Z])([a-zA-Z-0-9_]){3,}$",Pattern.CASE_INSENSITIVE);
            Pattern paternPasswor = Pattern.compile("^(.*){4,}$", Pattern.CASE_INSENSITIVE);
            Matcher  seachLogin = paternLogin.matcher(login.getText());
            Matcher searchPassword = paternPasswor.matcher(password.getText());

            if(!seachLogin.find())
            {
                errorMessage += "Format login incorrect\n";
            }
            if(!searchPassword.find())
            {
                errorMessage += "Format Password incorrect\n";
            }
            if (!errorMessage.isEmpty())
            {
                Helper.error(errorMessage);
            }
            UsersDOA userDoa = new UsersDOA(DatabaseHelper.getInstance());
            if(userDoa.login(login.getText(),password.getText()) && errorMessage.isEmpty())
            {
                Users user = userDoa.getUserByLoginPwd(login.getText(),password.getText());
                Helper h = new Helper();
                if(user.getEtat() == 1)
                {
                    Helper.CURENUSER = user ;
                    Helper.CURENAGENCE = userDoa.getAgenceByIdUser(user.getId());
                    System.out.println("ID USER :" + Helper.CURENUSER.getId() +" ID AGENCE "+Helper.CURENAGENCE.getId());
                    h.redirect(contenForm,"profil.fxml");
                }
                else
                {
                    Helper.error("Votre Compte a ete desactiver\nVeillez Contacter le chef d'agence");
                }


            }
            else if(errorMessage.isEmpty())
            {
                String message;
                Helper.error("Login ou Mot de Passe incorrect\nVeillez ressayer");
            }

        }
        catch (Exception e){
            Helper.error("Echec Connexion Réessayer ");
            //e.printStackTrace();
        }



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        login.setText("admin");
        password.setText("admin");
    }
}
