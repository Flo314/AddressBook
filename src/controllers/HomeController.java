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
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


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
    private Button printButton;
    
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            cnx.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
