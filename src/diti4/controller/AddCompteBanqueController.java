package diti4.controller;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import diti4.dao.ClientDOA;
import diti4.dao.ComptBanqueDOA;
import diti4.dao.DatabaseHelper;
import diti4.helper.Helper;
import diti4.model.Agences;
import diti4.model.Client;
import diti4.model.CompteBanque;
import diti4.model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddCompteBanqueController implements Initializable{

    @FXML
    private AnchorPane addCompteBanque;
    @FXML
    private ComboBox<String> cbxTypeCompte;

    @FXML
    private ComboBox<Double> cbxDecouvert;

    @FXML
    private ComboBox<Client> cbxClient;

    @FXML ComboBox<Double> cbxTauxRenu ;
    @FXML
    private TextField txtNumcompt;

    @FXML
    private TextField txtSolde;

    @FXML
    private TableView<CompteBanque> tableViewComptBanque;

    @FXML
    private TableColumn<CompteBanque, Integer> idTV;

    @FXML
    private TableColumn<CompteBanque, String> typeTV;

    @FXML
    private TableColumn<CompteBanque, Double> tauxTV;

    @FXML
    private TableColumn<CompteBanque, Double> soldeTV;

    @FXML
    private TableColumn<CompteBanque, String> numComptTV;

    @FXML
    private TableColumn<CompteBanque, Double> decouvertTV;

    @FXML
    private TableColumn<CompteBanque, Agences> AgenceCreatTV;

    @FXML
    private TableColumn<CompteBanque,Client> clientTv ;

    @FXML
    private Button addCompte;

    @FXML
    private Button delete;

    @FXML
    private Button effacherChamp;

    @FXML
    private ImageView retour;


    @FXML
    private TableColumn<CompteBanque, Users> userCreatTV;

    ClientDOA clientDOA =  new ClientDOA(DatabaseHelper.getInstance());
    ComptBanqueDOA comptBanqueDOA = new ComptBanqueDOA(DatabaseHelper.getInstance());
    int idAgence = Helper.CURENAGENCE.getId() ,idUser= Helper.CURENUSER.getId() ;
    Helper helper =  new Helper();

    @FXML
    void retourClick(MouseEvent event) {
        try {
            helper.redirect(addCompteBanque,"profil.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addCompteClick(ActionEvent event) {
        try {
            int res = comptBanqueDOA.addCompteBanque(Double.parseDouble(txtSolde.getText()),
                    cbxDecouvert.getSelectionModel().getSelectedItem(),cbxTypeCompte.getSelectionModel().getSelectedItem(),
                    cbxClient.getSelectionModel().getSelectedItem().getId(),cbxTauxRenu.getSelectionModel().getSelectedItem(),
                    txtNumcompt.getText(),idAgence,idUser);
            if(res==1)
            {
                Helper.valide("Compte ouvert avec succes");
                init();
                effacer();
            }
            else
            {
                Helper.error("Erreur ouverure Compte");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void deleteClick(ActionEvent event) {

    }

    @FXML
    void effacerChampClick(ActionEvent event) {
        effacer();
    }
    private void effacer()
    {
        txtSolde.setText("");
        txtNumcompt.setText("");
        cbxTypeCompte.getSelectionModel().clearSelection();
        cbxDecouvert.setValue(null);
        cbxTauxRenu.setValue(null);
        cbxClient.getSelectionModel().clearSelection();


    }

    @FXML
    void ClickTypeCompte(ActionEvent event) {
            if(cbxTypeCompte.getSelectionModel().getSelectedItem()=="simple")
            {
                cbxDecouvert.setValue(50000.0);
                cbxTauxRenu.setValue(6.0);
                txtSolde.setText("10000.0");

            }else if(cbxTypeCompte.getSelectionModel().getSelectedItem()=="kheweul")
            {
                cbxDecouvert.setValue(100000.0);
                cbxTauxRenu.setValue(11.0);
                txtSolde.setText("50000.0");
            }
    }

    @FXML
    void cbxTauxRenu(ActionEvent event) {

    }

    @FXML
    void tableViewClickRow(MouseEvent event) {


    }
    private void init()
    {

        try {



            /////table view
            ArrayList<CompteBanque> compteBanques = comptBanqueDOA.getListCompteBanque();
            ObservableList<CompteBanque> obCompteBanques = FXCollections.observableArrayList(compteBanques);
            tableViewComptBanque.setItems(obCompteBanques);
            idTV.setCellValueFactory(new PropertyValueFactory<>("id"));
            soldeTV.setCellValueFactory(new PropertyValueFactory<>("solde"));
            decouvertTV.setCellValueFactory(new PropertyValueFactory<>("decouvert"));
            numComptTV.setCellValueFactory(new PropertyValueFactory<>("numeroCompte"));
            typeTV.setCellValueFactory(new PropertyValueFactory<>("typecompte"));
            tauxTV.setCellValueFactory(new PropertyValueFactory<>("tauxrenumeration"));
            userCreatTV.setCellValueFactory(new PropertyValueFactory<>("user"));
            AgenceCreatTV.setCellValueFactory(new PropertyValueFactory<>("agence"));
            clientTv.setCellValueFactory(new PropertyValueFactory<>("client"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //cbxTypeCompte.getItems().removeAll();
        ArrayList<Client> clients = null;
        try {
            clients = clientDOA.getListClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
        cbxClient.getItems().addAll(clients);
        cbxClient.setValue(clients.get(0));
        cbxTypeCompte.getItems().addAll("simple","kheweul");
        init();
    }
}
