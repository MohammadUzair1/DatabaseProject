/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import Table.Table;
import java.sql.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author abddul haleem
 */
public class RemoveFXMLController implements Initializable {
    
    
    @FXML
    private JFXButton remove;
    
    Connection conn=null;
    PreparedStatement pst = null;
    ResultSet rs=null;
    Statement st=null;
    @FXML
    private JFXButton Back;
    @FXML
    private JFXTextField EmpId;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void removeMethod(ActionEvent event) {
        if( EmpId.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please fill field first");
        }
        else{
            int empid = Integer.parseInt(EmpId.getText());
            try{
                Class.forName("oracle.jdbc.OracleDriver");
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:UZAIR","user1","pass1");
                System.out.println("Connected Successfully");
                pst = conn.prepareStatement("delete from employee where employee_id="+empid);
                int i =pst.executeUpdate();
                if(i>0)
                {
                    JOptionPane.showMessageDialog(null,"Success!");
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"id not found");
                }
            }
            
            catch(ClassNotFoundException | SQLException | NumberFormatException | HeadlessException e)
            {
                
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
        
    }

    @FXML
    private void backMethod(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/EmpFXML.fxml"));
        Stage stage = (Stage)remove.getScene().getWindow();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
       
    }
    
}
