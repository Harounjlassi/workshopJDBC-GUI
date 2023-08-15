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
import edu.esprit.utils.MyConnection;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.geometry.Insets;

import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.table.TableModel;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author msi
 */
// ... (imports and other code)
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
    @FXML
    private Button sendmail;
    @FXML
    private Button btnexcel;
    @FXML
    private TableColumn<TableModel, String> editIcon;

    Personne p = null;
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    @FXML
    private Button close;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        close.setStyle("-fx-background-color: transparent;");

     
        FontAwesomeIconView backtop = new FontAwesomeIconView(FontAwesomeIcon.CLOSE);
        backtop.setStyle(
                " -fx-cursor: hand ;"
                + "-glyph-size:28px;"
                + "-fx-fill:#ffffff;"
        );
       
        close.setGraphic(backtop);
        show();
    }
    void display(){
      try {
                      listView.clear();

           Connection cnx2 = MyConnection.getInstance().getCnx();
//            Alert alert;
//            if (cnx2 == null || cnx2.isClosed()) {
//                alert = new Alert(Alert.AlertType.WARNING);
//                alert.setTitle("Test Connection");
//                alert.setContentText("Connect to the database failed!");
//
//                alert.show();
//
//            } else {
//                alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Test Connection");
//                alert.setContentText("Connect to the database successfully!");
//
//                alert.show();

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
//            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                table.setItems(listView);
    }
    
    
   

    void show() {
        try {
            Connection cnx2 = MyConnection.getInstance().getCnx();
            Alert alert;
            if (cnx2 == null || cnx2.isClosed()) {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Test Connection");
                alert.setContentText("Connect to the database failed!");

                alert.show();

            } else {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Test Connection");
                alert.setContentText("Connect to the database successfully!");

                alert.show();

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

        }
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        //add cell of button edit 
        Callback<TableColumn<TableModel, String>, TableCell<TableModel, String>> cellFoctory = (TableColumn<TableModel, String> param) -> {
            // make cell containing buttons
            final TableCell<TableModel, String> cell = new TableCell<TableModel, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                p = table.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `personne` WHERE id  =" + p.getId();
                                Connection cnx2 = MyConnection.getInstance().getCnx();

                                preparedStatement = cnx2.prepareStatement(query);
                                preparedStatement.execute();
                                display();

                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            p = table.getSelectionModel().getSelectedItem();
//                            FXMLLoader loader = new FXMLLoader ();
//                            loader.setLocation(getClass().getResource("/tableView/addStudent.fxml"));
//                            try {
//                                loader.load();
//                            } catch (IOException ex) {
//                                Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                            
//                            AddStudentController addStudentController = loader.getController();
//                            addStudentController.setUpdate(true);
//                            addStudentController.setTextField(student.getId(), student.getName(), 
//                                    student.getBirth().toLocalDate(),student.getAdress(), student.getEmail());
//                            Parent parent = loader.getRoot();
//                            Stage stage = new Stage();
//                            stage.setScene(new Scene(parent));
//                            stage.initStyle(StageStyle.UTILITY);
//                            stage.show();
//                            

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        editIcon.setCellFactory(cellFoctory);

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
    private void sendmail(ActionEvent event) {
        double x, y = 0;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sendemail.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root, 910, 602); // Create the scene with the desired dimensions

            Stage stage = (Stage) table.getScene().getWindow(); // Get the current stage
            stage.setScene(scene); // Set the new scene
            //Scene scene=table.getScene();

            scene.setRoot(root);
        } catch (IOException ex) {
            System.out.println("Error:" + ex.getMessage());
        }
    }

    @FXML
    private void excelexport(ActionEvent event) {

        try {
            Connection cnx2 = MyConnection.getInstance().getCnx();

            String requet2 = "SELECT * FROM personne";
            PreparedStatement st = cnx2.prepareStatement(requet2);
            ResultSet rs = st.executeQuery();
            XSSFWorkbook wb = new XSSFWorkbook();//for earlier version use HSSF
            XSSFSheet sheet = wb.createSheet("user deatails");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("Id");
            header.createCell(1).setCellValue("Name");
            header.createCell(2).setCellValue("surname");
            int index = 1;
            while (rs.next()) {
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString("id"));
                row.createCell(1).setCellValue(rs.getString("nom"));
                row.createCell(2).setCellValue(rs.getString("prenom"));
                index++;

            }
            FileOutputStream fileOut = null;
            try {
                fileOut = new FileOutputStream("user.xlsx");
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                wb.write(fileOut);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Excel");
            alert.setContentText("Excel created successfully!");

            alert.show();
            cnx2.close();
            rs.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Excel");
            alert.setContentText("failed  create !");

            alert.show();

        }

    }

    @FXML
    private void closemethod(ActionEvent event) {
        Stage stage = (Stage) addnew.getScene().getWindow();
    stage.close();
    }

}
