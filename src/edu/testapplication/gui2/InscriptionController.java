/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.esprit.entities.Personne;
import edu.esprit.entities.PersonneCrud;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private Button backMail;
    @FXML
    private Button close;
    @FXML
    private Button sendmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        backMail.setStyle("-fx-background-color: transparent;");
        close.setStyle("-fx-background-color: transparent;");

        FontAwesomeIconView sendIcon = new FontAwesomeIconView(FontAwesomeIcon.SEND);
        sendIcon.setStyle(
                " -fx-cursor: hand ;"
                + "-glyph-size:28px;"
                + "-fx-fill:#2196f3;"
        );

        FontAwesomeIconView cancelIcon = new FontAwesomeIconView(FontAwesomeIcon.CLOSE);
        cancelIcon.setStyle(
                " -fx-cursor: hand ;"
                + "-glyph-size:28px;"
                + "-fx-fill:#2196f3;"
        );
        FontAwesomeIconView back = new FontAwesomeIconView(FontAwesomeIcon.BACKWARD);
        back.setStyle(
                " -fx-cursor: hand ;"
                + "-glyph-size:28px;"
                + "-fx-fill:#ffffff;"
        );
        FontAwesomeIconView backtop = new FontAwesomeIconView(FontAwesomeIcon.CLOSE);
        backtop.setStyle(
                " -fx-cursor: hand ;"
                + "-glyph-size:28px;"
                + "-fx-fill:#ffffff;"
        );

        backMail.setGraphic(back);

        close.setGraphic(backtop);
    }

    private boolean isNumber(String input) {
        try {
            Double.parseDouble(input); // Try to parse the input as a double
            return true; // Parsing successful, input is a number
        } catch (NumberFormatException e) {
            return false; // Parsing failed, input is not a number
        }
    }

    @FXML
    private void addnewmember(ActionEvent event) {
        Alert alert;

        if (tfId.getText().isEmpty() || !isNumber(tfId.getText())) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input faild");
            alert.setContentText("Please check the Id field");

            alert.showAndWait();

        } else {
            try {
                int id = Integer.parseInt(tfId.getText());
                String nom = tfNom.getText();
                String prenom = tfPrenom.getText();
                Personne p = new Personne(nom, prenom, id);
                PersonneCrud pc = new PersonneCrud();
                pc.ajouterPersonne2(p);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Add new Personne ");
                alert.setContentText("Personne Add Successfully ");

                alert.showAndWait();
            } catch (Exception e) {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add new Personne ");
                alert.setContentText("fFailed to Add Personne  ");

                System.out.println("Failed to add person: " + e.getMessage());
                // Yo

            }
        }
    }

    @FXML
    private void backMailmethod(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        try {
            Parent root = loader.load();
            tfNom.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("Error:" + ex.getMessage());
        }
    }

    @FXML
    private void closemethod(ActionEvent event) {

        Stage stage = (Stage) tfNom.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void sendmail(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sendemail.fxml"));
        try {
            Parent root = loader.load();
            tfNom.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("Error:" + ex.getMessage());
        }
    }
}
