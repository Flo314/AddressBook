/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Model.Personne;
import Utils.SingletonCnx;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author florent losada
 */
public class Controller implements Initializable {

    SingletonCnx cnx;
    ObservableList<Personne> data = FXCollections.observableArrayList();

    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

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
    private Button printButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("phones"));
        col_adress.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col_code.setCellValueFactory(new PropertyValueFactory<>("cpt"));
        col_vil.setCellValueFactory(new PropertyValueFactory<>("ville"));
        col_mail.setCellValueFactory(new PropertyValueFactory<>("emails"));

        try {
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadData() throws SQLException {

        String query = "select * from contact";

        try {
            Connection con = SingletonCnx.getConnection();
            preparedStatement = con.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                data.add(new Personne(rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getString("cpt"),
                        rs.getString("ville")));

                tblData.setItems(data);
            }
            preparedStatement.close();
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Remplir les champs obligatoires!");
            alert.showAndWait();
        } else {
            // insert dans bdd
            String query = "INSERT INTO contact(nom,prenom,telephone,adresse,cpt,ville,email) VALUES(?,?,?,?,?,?,?) ";
            preparedStatement = null;
            try {
                preparedStatement = SingletonCnx.getConnection().prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, pren);
                preparedStatement.setString(3, tel);
                preparedStatement.setString(4, adress);
                preparedStatement.setString(5, cpt);
                preparedStatement.setString(6, vil);
                preparedStatement.setString(7, mail);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Success Added!");
                alert.showAndWait();
            } catch (SQLException ex) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Operation failed!");
                alert.showAndWait();
                System.out.println(ex);
            } finally {
                preparedStatement.execute();
                preparedStatement.close();
            }
            data.clear();
            loadData();
        }
    }

    @FXML
    void newContact(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Remplir tous les champs");
        alert.showAndWait();
        nom.clear();
        prenom.clear();
        telephones.clear();
        adresse.clear();
        codep.clear();
        ville.clear();
        email.clear();
    }

    @FXML
    void editContact(ActionEvent event) throws SQLException {

        String query = "update contact set nom=?, prenom=?,telephone=?, adresse=?, cpt=?, ville=?, email=? where nom='"+tempUsername+"'";

        try {
            preparedStatement = SingletonCnx.getConnection().prepareStatement(query);
            preparedStatement.setString(1, nom.getText());
            preparedStatement.setString(2, prenom.getText());
            preparedStatement.setString(3, telephones.getText());
            preparedStatement.setString(4, adresse.getText());
            preparedStatement.setString(5, codep.getText());
            preparedStatement.setString(6, ville.getText());
            preparedStatement.setString(7, email.getText());
            preparedStatement.execute();
            preparedStatement.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Success Updated!");
            alert.showAndWait();
            data.clear();
            loadData();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    void deleteContact(ActionEvent event) {

    }

    static String tempUsername;
    @FXML
    public void showOnClick() throws SQLException {

        try {
            Personne person = tblData.getSelectionModel().getSelectedItem();
            String query = "Select * from contact";
            preparedStatement = SingletonCnx.getConnection().prepareStatement(query);
            
            tempUsername = person.getNom();
            nom.setText(person.getNom());
            prenom.setText(person.getPrenom());
            telephones.setText(person.getPhones().toString());
            adresse.setText(person.getAdresse());
            codep.setText(person.getCpt());
            ville.setText(person.getVille());
            email.setText(person.getEmails().toString());

            preparedStatement.close();
            rs.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
