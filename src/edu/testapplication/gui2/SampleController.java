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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.table.TableModel;

/**
 * FXML Controller class
 *
 * @author msi
 */
// ... (imports and other code)
public class SampleController implements Initializable {

    private TableView<Personne> table;
    private TableColumn<TableModel, String> nom;
    private TableColumn<TableModel, String> prenom;
    private TableColumn<TableModel, String> id;
    public ObservableList<Personne> listView = FXCollections.observableArrayList();
    @FXML
    private Button addnew;
    @FXML
    private Button addnew1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show();
    }

    void show() {
        try {
            Connection cnx2 = MyConnection.getInstance().getCnx();
            Alert alert;
            if (cnx2 == null || cnx2.isClosed()) {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Test Connection");
                alert.setContentText("Connect to the database failed!");

                alert.showAndWait();

            } else {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Test Connection");
                alert.setContentText("Connect to the database successfully!");

                alert.showAndWait();

                PersonneCrud pcd = new PersonneCrud();

                String requet2 = "SELECT * FROM personne";
                Statement st = cnx2.createStatement();
                ResultSet rs = st.executeQuery(requet2);
                while (rs.next()) {
                    Personne p = new Personne();
                    p.setId(rs.getInt(1));
                    p.setNom(rs.getString("nom"));
                    p.setPrenom(rs.getString("prenom"));
                    listView.add(p);
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // Handle the exception gracefully, e.g., show a message to the user
            // and continue with other parts of your application.
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

    @FXML
    private void sendEmail(ActionEvent event) {
    }
}
