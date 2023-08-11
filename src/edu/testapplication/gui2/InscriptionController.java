/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import edu.esprit.entities.Personne;
import edu.esprit.entities.PersonneCrud;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

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
     

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
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

        if(tfId.getText().isEmpty()||!isNumber(tfId.getText())){
   Alert alert;
            alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Input faild");
                alert.setContentText("Please check the Id field");

                alert.showAndWait();            
        
        }else{
              int id = Integer.parseInt(tfId.getText());
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        Personne p = new Personne(nom, prenom, id);
        PersonneCrud pc = new PersonneCrud();
        pc.ajouterPersonne2(p);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsWindow.fxml"));
        try {
            Parent root = loader.load();
            DetailsWindowController dwc= loader.getController();
            dwc.setTextID(""+ p.getId());
            dwc.setTextNOM(p.getNom());
            dwc.setTextPRENOM(p.getPrenom());
              
            tfNom.getScene().setRoot(root);
            
            
            
            
            
        } catch (IOException ex) {
            System.out.println("Error:" + ex.getMessage());
        }}
    }
    

   
}
