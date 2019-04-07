/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;



import Model.Personne;



import Utils.SingletonCnx;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author florent
 */
public class HomeController implements Initializable {

    SingletonCnx cnx;
    Personne personne;
    

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField telephones;

    @FXML
    private TextField adresse;

    @FXML
    private TextField codep;

    @FXML
    private TextField ville;

    @FXML
    private TextField email;

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<Personne> tblData;

    @FXML
    private TableColumn<Personne, String> col_nom;

    @FXML
    private TableColumn<Personne, String> col_prenom;

    @FXML
    private TableColumn<Personne, String> col_tel;

    @FXML
    private TableColumn<Personne, String> col_adress;

    @FXML
    private TableColumn<Personne, String> col_code;

    @FXML
    private TableColumn<Personne, String> col_vil;

    @FXML
    private TableColumn<Personne, String> col_mail;

    @FXML
    private Button printButton;
    
    ObservableList<Personne> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Connection con = SingletonCnx.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM contact");
            
                        
            while(rs.next()){
                oblist.add(new Personne(rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("adresse"),
                rs.getString("cpt"),
                rs.getString("ville")));

 
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("phones")); 
        col_adress.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col_code.setCellValueFactory(new PropertyValueFactory<>("cpt")); 
        col_vil.setCellValueFactory(new PropertyValueFactory<>("ville"));
        col_mail.setCellValueFactory(new PropertyValueFactory<>("emails"));

        tblData.setItems(oblist);
    }

    @FXML
    void deleteContact(ActionEvent event) {

    }

    @FXML
    void editContact(ActionEvent event) {

    }

    @FXML
    void saveContact(ActionEvent event) throws SQLException {
        String name = nom.getText();
        String pren = prenom.getText();
        String tel = telephones.getText();
        String adress = adresse.getText();
        String cpt = codep.getText();
        String vil = ville.getText();
        String mail = email.getText();
        if (name.isEmpty() || pren.isEmpty() || tel.isEmpty() || adress.isEmpty() || cpt.isEmpty() || vil.isEmpty() || mail.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Remplir les champs obligatoires!");
            alert.showAndWait();
        } else {
            // insert dans bdd
            String query = "INSERT INTO contact(nom,prenom,telephone,adresse,cpt,ville,email) VALUES(?,?,?,?,?,?,?) ";
            try {
                PreparedStatement state = SingletonCnx.getConnection().prepareStatement(query);
                state.setString(1, name);
                state.setString(2, pren);
                state.setString(3, tel);
                state.setString(4, adress);
                state.setString(5, cpt);
                state.setString(6, vil);
                state.setString(7, mail);
                state.executeUpdate();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Success added!");
                alert.showAndWait();
            } catch (SQLException ex) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Operation failed!");
                alert.showAndWait();
                System.out.println(ex);
            }
        }
    }

    @FXML
    void newContact(ActionEvent event) {
        nom.clear();
        prenom.clear();
        telephones.clear();
        adresse.clear();
        codep.clear();
        ville.clear();
        email.clear();
    }
}
