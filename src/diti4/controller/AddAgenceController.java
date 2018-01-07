package diti4.controller;
import diti4.dao.AgencesDOA;
import diti4.dao.DatabaseHelper;
import diti4.helper.Helper;
import diti4.model.Agences;
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

public class AddAgenceController implements Initializable{

    @FXML
    private TableView<Agences> agenceTableView;

    @FXML
    private TableColumn<Agences, Integer> codeAgenceTV;

    @FXML
    private TableColumn<Agences, String> nomAgenceTV;

    @FXML
    private TableColumn<Agences, String> adresseAgenceTV;

    @FXML
    private TableColumn<Agences, String> telAgenceTV;


    @FXML
    private AnchorPane addAgent;

    @FXML
    private TextField codeAgence;

    @FXML
    private TextField nomAgence;

    @FXML
    private TextField adresseAgence;

    @FXML
    private TextField telAgence;

    @FXML
    private Button addAgence;

    @FXML
    private Button resetChamp;

    @FXML
    private ImageView retour;
    @FXML
    private Button deleteAgence ;

    @FXML
    void addAgenceClick(ActionEvent event) throws Exception {
        AgencesDOA agenceDoa = new AgencesDOA(DatabaseHelper.getInstance());
        //////ADD AGENCE
        if(!editing)
        {

            try {
                int res = agenceDoa.addAgence(Integer.parseInt(codeAgence.getText()),nomAgence.getText(),adresseAgence.getText(),telAgence.getText());
                if(res == 1)
                {
                    Helper.valide("Agence Ajouter Avec Succée");
                    effacer();
                    refresh();
                    editing = false ;
                    editingId = 0 ;

                }
                else
                {
                    Helper.error("Certaine Champs Sont Vide ou \nContiennent des Valeur invalide\nVeillez Reessayer");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ////EDITING AGENCE
        else
        {
            try {
                int res = agenceDoa.updateAgence(editingId,Integer.parseInt(codeAgence.getText()),nomAgence.getText(),adresseAgence.getText(),telAgence.getText());
                if(res==1)
                {
                    Helper.valide("Agence Mise a jour Avec Succée");
                    effacer();
                    refresh();
                    addAgence.setText("Valider");
                    deleteAgence.setDisable(true);
                }
                else
                {
                    Helper.error("Echec Mise a jour");
                    addAgence.setText("Valider");
                    deleteAgence.setDisable(true);
                }

            } catch (Exception e) {
                throw e ;
            }
        }

    }

    @FXML
    void resetChampClick(ActionEvent event)
    {
        effacer();
        deleteAgence.setDisable(true);
        resetChamp.setText("reset");
        editing = false ;
        editingId = 0 ;
    }

    @FXML
    void retourClick(MouseEvent event) {
        Helper h = new Helper();
        try {
            h.redirect(addAgent,"profil.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void effacer()
    {
        codeAgence.setText("");
        nomAgence.setText("");
        adresseAgence.setText("");
        telAgence.setText("");
        editingId = 0 ;
        editing = false ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();


    }
    public void refresh()
    {
        AgencesDOA agenceDoa = new AgencesDOA(DatabaseHelper.getInstance());
        try {
            ArrayList<Agences> listAgence = agenceDoa.getListAgence();
            ObservableList<Agences> obListAgence = FXCollections.observableArrayList(listAgence);
          //  agenceTableView.getItems().addAll(obListAgence);
            agenceTableView.setItems(obListAgence);
           /* for(int i = 0 ; i < listAgence.size() ;i++)
            {
                agenceTableView.getItems().add(listAgence.get(i));
            }*/
            codeAgenceTV.setCellValueFactory(new PropertyValueFactory<>("code"));//"code" corespond aux proprieté de l'atribut sur agence
            nomAgenceTV.setCellValueFactory(new PropertyValueFactory<>("nom"));
            adresseAgenceTV.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            telAgenceTV.setCellValueFactory(new PropertyValueFactory<>("tel"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    int editingId ;
    boolean editing = false ;

    @FXML
    void tableviewClick(MouseEvent event) {

       // TableView.TableViewSelectionModel<Agences> row = agenceTableView.getSelectionModel();
       // Agences selectedAgence = row.getSelectedItem();
        Agences selectedAgence = agenceTableView.getSelectionModel().getSelectedItem();
        nomAgence.setText(selectedAgence.getNom());
        codeAgence.setText(selectedAgence.getCode()+"");
        telAgence.setText(selectedAgence.getTel());
        adresseAgence.setText(selectedAgence.getAdresse());
        editing = true ;
        editingId = selectedAgence.getId();
        deleteAgence.setDisable(false);
        addAgence.setText("Modifier");
        resetChamp.setText("Nouveau");
    }

    @FXML
    void deleteAgencegeClick(ActionEvent event) {
        AgencesDOA agenceDoa = new AgencesDOA(DatabaseHelper.getInstance());
       Agences agence =  agenceTableView.getSelectionModel().getSelectedItem();
        try {
            if(Helper.confirm("Vouler vous suprimer lagence : "+ agence.getNom()) ==true)
            {
                int res = agenceDoa.deleteAgence(agence.getId());
                if (res==1)
                {
                    Helper.valide("Agence suprimer avec success");
                    deleteAgence.setDisable(true);
                    refresh();
                    effacer();
                }
                else
                {
                    Helper.error("Echec de la suppression de l'agence");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
