package diti4.controller;

import diti4.helper.Helper;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfilController implements Initializable {

    @FXML
    private BorderPane profil;

    @FXML
    private Menu menufile;

    @FXML
    private MenuItem logOut;

    @FXML
    private MenuItem quitez;

    @FXML
    private Menu menuparams;

    @FXML
    private MenuItem agence;

    @FXML
    private MenuItem user;

    @FXML
    private MenuItem client;

    @FXML
    private MenuItem comptebanque;

    @FXML
    private Menu menuhelp;

    @FXML
    private MenuItem about;

    @FXML
    private Label bienVenu;

    @FXML
    private ImageView img;

    @FXML
    void aboutclick(ActionEvent event) {
        try {
            h.setBorderPane(profil,"about.fxml",1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Helper h = new Helper();
    @FXML
    void agenceClick(ActionEvent event) {
        try {
            h.setBorderPane(profil,"addAgence.fxml",1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clientClick(ActionEvent event) {
        try {
            h.setBorderPane(profil,"addClient.fxml",1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void logOutClick(ActionEvent event)
    {
        Helper.CURENUSER = null ;
        try {
            h.redirect(profil,"FLogin.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void comptebanqueClick(ActionEvent event) {
        try {
            h.setBorderPane(profil,"addCompteBanque.fxml",1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void quitezClick(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void userClick(ActionEvent event) {
        try {
            h.setBorderPane(profil,"addUser.fxml",1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bienVenu.setText("BienVenu L'administrateur");
    }
    /*@FXML
    private AnchorPane profil;
    @FXML
    private Label bienVenu;

    @FXML
    private Button addAgence;

    @FXML
    private Button addUser;


    @FXML
    private Button logOut;

    @FXML
    void addAgenceClick(ActionEvent event) {
        Helper h = new Helper();
        try {
            h.redirect(profil,"addAgence.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addUserClick(ActionEvent event) {
        Helper h = new Helper();
        try {
            h.redirect(profil,"addUser.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void logOutClick(ActionEvent event) {
        Helper h =new Helper();
        try {
            h.redirect(profil,"FLogin.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    */
}