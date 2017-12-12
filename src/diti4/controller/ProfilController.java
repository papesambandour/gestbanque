package diti4.controller;

import diti4.helper.Helper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfilController implements Initializable {

    @FXML
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bienVenu.setText("BIENVENU MONSIEUR L'ADMINISTRATEUR");
    }
}