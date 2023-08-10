/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import edu.esprit.entities.Personne;
import edu.esprit.entities.PersonneCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    @FXML
    private Button btnValider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void savePersonne(ActionEvent event) {
        int id = Integer.parseInt(tfId.getText());
        String nom= tfNom.getText();
        String prenom= tfPrenom.getText();
        Personne p = new Personne(nom, prenom, id);
        PersonneCrud pc =new PersonneCrud();
        pc.ajouterPersonne2(p);
        
        
    }
    
}
