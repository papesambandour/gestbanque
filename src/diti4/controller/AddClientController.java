package diti4.controller;

import diti4.dao.ClientDOA;
import diti4.dao.DatabaseHelper;
import diti4.helper.Helper;
import diti4.model.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddClientController implements Initializable{

    @FXML
    private AnchorPane addClient;

    @FXML
    private TextField txtNoms;

    @FXML
    private TextField txtAdresse;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtEmail;

    @FXML
    private Button valider;

    @FXML
    private Button effacherChamp;

    @FXML
    private Button deleteClient;

    @FXML
    private TableView<Client> tableViewClient;

    @FXML
    private TableColumn<Client, Integer> idTV;

    @FXML
    private TableColumn<Client, String> nomTV;

    @FXML
    private TableColumn<Client, String> adresseTV;

    @FXML
    private TableColumn<Client, String> telTV;

    @FXML
    private TableColumn<Client, String> emailTV;

    @FXML
    private ImageView retour;


    @FXML
    void addClientClick(ActionEvent event) {
        ///////AJOUT CLIENT
        if(!editing)
        {
            try {
                int res = clientDOA.addClient(txtNoms.getText(),txtAdresse.getText(),txtTel.getText(),txtEmail.getText());
                if(res == 1)
                {
                    Helper.valide("Client Ajouter avec Succes");
                    init();
                    effacer();

                }
                else
                {
                    Helper.error("Erreur d'ajout Client");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //////UPDATE CLIENT
        else
        {
            try {
                int res = clientDOA.updateClient(editingId,txtNoms.getText(),txtAdresse.getText(),txtTel.getText(),txtEmail.getText());
                if(res == 1)
                {
                    Helper.valide("Client Mise a jour avec Succes");
                    init();
                    effacer();

                }
                else
                {
                    Helper.error("Erreur de mise a jour Client");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void clickeffacer(ActionEvent event) {
          effacer();

    }
    private void effacer()
    {
        txtNoms.setText("");
        txtAdresse.setText("");
        txtTel.setText("");
        txtEmail.setText("");
        effacherChamp.setText("reset");
        valider.setText("Valider");
        editingId = 0 ;
        editing = false ;
        deleteClient.setDisable(true);
    }

    @FXML
    void clientDeleteClick(ActionEvent event) {
        if(Helper.confirm("Voulez vous suprimer le client"))
        {
            try {
                int res = clientDOA.deleteClient(editingId);
                if(res == 1)
                {
                    Helper.valide("Client Supprimer avec Succes");
                    init();
                    effacer();

                }
                else
                {
                    Helper.error("Erreur de supression Client");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void retourClick(MouseEvent event) {
        try {
            helper.redirect(addClient,"profil.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void tableViewClickRow(MouseEvent event) {
        Client client = tableViewClient.getSelectionModel().getSelectedItem();
            txtNoms.setText(client.getNoms());
            txtAdresse.setText(client.getAdresse());
            txtEmail.setText(client.getEmail());
            txtTel.setText(client.getTel());
            editing = true ;
            editingId = client.getId();
            effacherChamp.setText("Nouveau");
            valider.setText("Modifier");
            deleteClient.setDisable(false);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    private ClientDOA clientDOA = new ClientDOA(DatabaseHelper.getInstance());
    private Helper helper = new Helper();
    private ObservableList<Client> obClient ;
    int editingId;
    boolean editing = false ;

    public void init()
    {

        try {
            ArrayList<Client> clients = clientDOA.getListClient();
            obClient = FXCollections.observableArrayList(clients);
            tableViewClient.setItems(obClient);
            idTV.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomTV.setCellValueFactory(new PropertyValueFactory<>("noms"));
            adresseTV.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            telTV.setCellValueFactory(new  PropertyValueFactory<>("tel"));
            emailTV.setCellValueFactory(new PropertyValueFactory<>("email"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
