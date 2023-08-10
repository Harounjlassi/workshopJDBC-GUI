/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.test;

import edu.esprit.entities.*;
import edu.esprit.utils.*;


/**
 *
 * @author msi
 */
public class MainClass {

    public static void main(String[] args) {
        MyConnection mc =  MyConnection.getInstance();
                MyConnection mc2 =  MyConnection.getInstance();
        System.out.println(mc.hashCode()+"---"+mc.hashCode());
        
        PersonneCrud pcd = new PersonneCrud();
        //pcd.ajouterPersonne();
        //Personne p2 = new Personne("harujn","jlassi");
        // pcd.ajouterPersonne2(p2);

//        try {
//            System.out.println(pcd.afficherPersonnes().getString("nom"));
//        } catch (SQLException ex) {
//        }
        System.out.println(pcd.afficherPersonnes());

    }

}
