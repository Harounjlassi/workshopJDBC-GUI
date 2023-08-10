/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.sql.Statement;
import java.util.List;
import edu.esprit.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author msi
 */
public class PersonneCrud {
    Connection cnx2;
    public PersonneCrud(){
    cnx2=MyConnection.getInstance().getCnx();
    }
    

    public void ajouterPersonne() {
        String requete = "INSERT INTO personne (nom , prenom) "
                + "VALUES('dupon','khkbbbblf')";
        try {
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("Personne ajouter avec succes");

            /*insert update delate =>st.executeUpdate 
            display => select=>executeQuery
             */
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void ajouterPersonne2(Personne p) {
        try {
            /*requete dynamique*/
            String requete = "INSERT INTO personne (nom , prenom) "
                    + "VALUES(?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getPrenom());
            pst.executeUpdate();
            System.out.println("Personne ajouter avec succes");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }

    public List<Personne> afficherPersonnes() {
        List<Personne> myList = new ArrayList<>();
        String requet2 = "SELECT * FROM personne";
        //ResultSet rs=null;

        try {
            Statement st = cnx2.createStatement();
            ResultSet rs=st.executeQuery(requet2);
             while(rs.next()){
                 Personne p= new Personne();
                 p.setId(rs.getInt(1));
                 p.setNom(rs.getString("nom"));
                 p.setPrenom(rs.getString("prenom"));
                 myList.add(p);
                 
             }
            

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        System.out.println(myList);
        return myList;

    }

}
