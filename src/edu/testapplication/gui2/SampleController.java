/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import edu.esprit.entities.Personne;
import edu.esprit.entities.PersonneCrud;
import edu.esprit.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.table.TableModel;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class SampleController implements Initializable {

    @FXML
    private TableView<Personne> table;
    @FXML
    private TableColumn<TableModel, String> nom;
    @FXML
    private TableColumn<TableModel, String> prenom;

    @FXML
    private TableColumn<TableModel, String> id;
            public ObservableList<Personne> listView = FXCollections.observableArrayList();
    @FXML
    private Button addnew;


    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        nom.setCellFactory(new PropertyValueFactory<>("nom"));
//        mobile.setCellFactory(new PropertyValueFactory<>("mobile"));
//        email.setCellFactory(new PropertyValueFactory<>("email"));

        show();

    }
   ObservableList<Personne> Personne ;

    void show() {

        try {
            Connection cnx2;

            cnx2 = MyConnection.getInstance().getCnx();
            PersonneCrud pcd = new PersonneCrud();

            String requet2 = "SELECT * FROM personne";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requet2);
            while (rs.next()) {
                Personne p = new Personne();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                listView.add(p 
                );

            }

        } catch (SQLException  ex) {
            System.out.println(ex.getMessage());
        }
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
                table.setItems(listView);


    }

    @FXML
    private void addnewmember(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("inscription.fxml"));
        try {
            Parent root = loader.load();
            
              
            table.getScene().setRoot(root);
            
            
            
            
            
        } catch (IOException ex) {
            System.out.println("Error:" + ex.getMessage());
        }
        
        
    }

}