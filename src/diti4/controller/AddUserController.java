package diti4.controller;

import diti4.dao.AgencesDOA;
import diti4.dao.DatabaseHelper;
import diti4.dao.UsersDOA;
import diti4.helper.Helper;
import diti4.model.Agences;
import diti4.model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddUserController implements Initializable{

   ////////AJOUT USER///////////
    @FXML
    private AnchorPane addUser;

    @FXML
    private TextField nomcomComplet;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private TextField login;

    @FXML
    private ComboBox<Agences> listAgence ;

    @FXML
    private Label labelAgence;

    @FXML
    private ComboBox<String> listProfil ;

    @FXML
    private Label labelProfil;

    @FXML
    private Button valideUser;

    @FXML
    private Button resetCamp;

    @FXML
    private ImageView retour;

    @FXML
    private Button activeAcount;

    @FXML
    private Button deleteUser;

    private boolean editing = false;
    private int editingUserId;

    @FXML
    void resetCampClick(ActionEvent event) {
        effacer();

    }

    @FXML
    void valideAddUserClick(ActionEvent event) {
        UsersDOA userDoa = new UsersDOA(DatabaseHelper.getInstance());
        ////INSERTION
        if (!editing) {
            try {
                int res = userDoa.addUser(nomcomComplet.getText(), login.getText(),
                        password.getText(), listProfil.getSelectionModel().getSelectedItem(), listAgence.getSelectionModel().getSelectedItem().getId());
                if (res == 1) {
                    Helper.valide("Utilisateur Ajouter Avec Succée");
                    effacer();
                    refresh();
                    editingUserId = 0;
                    editing = false ;
                } else {
                    Helper.error("Certaine Champs Sont Vide ou \nContiennent des Valeur invalide\nVeillez Reessayer");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        /////UPDATE
        else {
            try {
                int idAgence = listAgence.getSelectionModel().getSelectedItem().getId();

                int res = userDoa.updateUser(editingUserId, nomcomComplet.getText(), login.getText(),
                        password.getText(), listProfil.getSelectionModel().getSelectedItem(), listAgence.getSelectionModel().getSelectedItem().getId(),etatCompte);
                if (res == 1) {
                    Helper.valide("Utilisateur mise a jour Avec Succée");
                    effacer();
                    refresh();
                    editing = false;
                    editingUserId = 0;
                } else {
                    Helper.error("Certaine Champs Sont Vide ou \nContiennent des Valeur invalide\nVeillez Reessayer");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void effacer()
    {
        nomcomComplet.setText("");
        login.setText("");
        password.setText("");
        confirmPassword.setText("");
        editing = false;
        editingUserId = 0;
        resetCamp.setText("reset");
        activeAcount.setText("Action");
        activeAcount.setDisable(true);
        deleteUser.setDisable(true);
        valideUser.setText("Valider");

    }

    @FXML
    void retourClick(MouseEvent event) {
        Helper h = new Helper();
        try {
            h.redirect(addUser,"profil.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //cette methode permet d'initialiser les valeur dans le fxml a appel du controller,
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AgencesDOA agenceDoa = new AgencesDOA(DatabaseHelper.getInstance());
        try {
            ArrayList<Agences> agences = agenceDoa.getListAgence();
            for(int i =0 ; i < agences.size(); i++)
            {
                /*On creer notre liste de noms d'agences*/
                listAgence.getItems().add(agences.get(i));
            }
             /* permet de definir le paramettre par defaut du combobox listAgence, on le donne la premiere valeur du list*/
            listAgence.setValue(agences.get(0));
            listProfil.getItems().addAll("Admin","Caissier","Gestionnaire de compte","Chef d'agence");
             /* permet de definir le paramettre par defaut du combobox listProfil*/
             //gestionnaire agence consulter les agence
            //chef d'agence a
            listProfil.setValue("Admin");
            refresh();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    ////LISTER USER////////
    @FXML
    private TableView<Users> userTableView;

    @FXML
    private TableColumn<Users, String> nomTV;

    @FXML
    private TableColumn<Users, String> logintv;

    @FXML
    private TableColumn<Users, String> passwordTV;

    @FXML
    private TableColumn<Users, String> profileTV;

    @FXML
    private TableColumn<Users, String> nomAgenceTV;
    @FXML
    private TableColumn<Users, String> etat;

    @FXML
    void clickDeleteUser(ActionEvent event) {

        UsersDOA userdoa = new UsersDOA(DatabaseHelper.getInstance());
        Users userdel = userTableView.getSelectionModel().getSelectedItem();
        if(Helper.confirm("Voulez Vous suprimer le user : " + userdel.getNoms()))
        {
            try {
                int res = userdoa.delUser(userdel.getId());
                if(res == 1)
                {
                    Helper.valide("Supression Reussit Avec succes");
                    valideUser.setText("Valider");
                    resetCamp.setText("reset");
                    activeAcount.setText("Action");
                    activeAcount.setDisable(true);
                    deleteUser.setDisable(true);
                    effacer();
                    refresh();
                }
                else
                {
                    Helper.valide("Echec Supression");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void clickeActiveAcout(ActionEvent event) {
        etatCompte = (etatCompte==1)? 0 : 1;
        if(etatCompte==1)
        {
            activeAcount.setText("desactiver");
        }else
        {
            activeAcount.setText("activer");
        }

    }

    private int etatCompte;
    private void refresh()
    {
        UsersDOA userDoa = new UsersDOA(DatabaseHelper.getInstance());
        try {
            ArrayList<Users> listUsers = userDoa.getListUsers();
            ObservableList<Users> obListUsers = FXCollections.observableArrayList(listUsers);
            userTableView.setItems(obListUsers);
            nomTV.setCellValueFactory(new PropertyValueFactory<>("noms"));
            logintv.setCellValueFactory(new PropertyValueFactory<>("login"));
            passwordTV.setCellValueFactory(new PropertyValueFactory<>("password"));
            profileTV.setCellValueFactory(new PropertyValueFactory<>("profile"));
            nomAgenceTV.setCellValueFactory(new PropertyValueFactory<>("agence"));
            etat.setCellValueFactory(new PropertyValueFactory<>("etat"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void evenTableview(MouseEvent event)
    {
        TableView.TableViewSelectionModel<Users> row = userTableView.getSelectionModel();

        Users selectedUser = row.getSelectedItem();
        nomcomComplet.setText(selectedUser.getNoms());
        password.setText(selectedUser.getPassword());
        login.setText(selectedUser.getLogin());
        confirmPassword.setText(selectedUser.getPassword());
        listAgence.setValue(selectedUser.getAgence());
        listProfil.setValue(selectedUser.getProfile());
        editingUserId = selectedUser.getId();
        editing = true;
        etatCompte = selectedUser.getEtat();
        activeAcount.setDisable(false);
        deleteUser.setDisable(false);
        valideUser.setText("Modifier");
        if(selectedUser.getEtat() == 1)
        {
            activeAcount.setText("desactiver");
        }else
        {
            activeAcount.setText("activer");
        }
        resetCamp.setText("Nouveau");

    }

}
