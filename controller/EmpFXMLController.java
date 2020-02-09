/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Table.Table;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author abddul haleem
 */
public class EmpFXMLController implements Initializable {
    @FXML
    private TableView<Table> table_view;
    @FXML
    private TableColumn<Table, Integer> emp_id_col;
    
    @FXML
    private TableColumn<Table, String> first_name_col;
    @FXML 
    private TableColumn<Table, String> last_name_col;
    @FXML
    private TableColumn<Table, String> gender_col;
    
    @FXML
    private TableColumn<Table, Integer> age_col;
    @FXML
    private TableColumn<Table, String> salary_col;
    @FXML
    private JFXTextField emp_id;
    
    @FXML
    private JFXTextField firstName;
    @FXML
    private JFXTextField lastName;
    @FXML
    private JFXTextField gender;
    
    @FXML
    private JFXTextField age;
    @FXML
    private JFXTextField salary;
    @FXML
    private JFXButton add;
    @FXML
    private JFXButton clear;
    @FXML
    private JFXButton exit;
    
    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst;
    Statement st;
    
    
    
    ObservableList<Table> data = FXCollections.observableArrayList();
    @FXML
    private JFXButton avgSal;
    @FXML
    private JFXButton sortAscid;
    @FXML
    private JFXButton sortDescId;
    @FXML
    private JFXButton sortAlphfname;
    @FXML
    private JFXButton sortAlphlname;
    @FXML
    private JFXButton remove;
    @FXML
    private JFXTextField job;
    @FXML
    private DatePicker hiredate;
    @FXML
    private TableColumn<Table, String> job_col;
    @FXML
    private TableColumn<Table, Date> hiredate_col;
    @FXML
    private JFXButton refresh;
    @FXML
    private JFXButton annualSal;
   
            
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        
        
        
        emp_id_col.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        job_col.setCellValueFactory(new PropertyValueFactory<>("job"));
        first_name_col.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        last_name_col.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        gender_col.setCellValueFactory(new PropertyValueFactory<>("gender"));
        hiredate_col.setCellValueFactory(new PropertyValueFactory<>("hiredate"));
        age_col.setCellValueFactory(new PropertyValueFactory<>("age"));
        salary_col.setCellValueFactory(new PropertyValueFactory<>("salary"));
        showTableData();
        
    }   
    public void showTableData(){
        try{
         Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:UZAIR","user1","pass1");
            System.out.println("Connected Successfully");
        st = conn.createStatement();
            rs = st.executeQuery("select * from employee");
            while(rs.next()){
            data.add(new Table(rs.getInt("employee_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("gender"), rs.getString("hiredate"), rs.getInt("age"), rs.getInt("salary"), rs.getString("job"))); 
            }
            table_view.setItems(data);
        }catch(ClassNotFoundException | SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @FXML
    private void addMethod(ActionEvent event) throws ClassNotFoundException, SQLException {
       
        try{
            int emp_id_text =Integer.parseInt(emp_id.getText());
                String job_text = job.getText();
                String first_name_text = firstName.getText();
                String last_name_text = lastName.getText();
                String gender_text = gender.getText();
              

                int age_text = Integer.parseInt(age.getText());
                int salary_text = Integer.parseInt(salary.getText());
                
            Class.forName("oracle.jdbc.OracleDriver");
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:UZAIR","user1","pass1");
                System.out.println("Connected Successfully");
            if(gender.getText().equalsIgnoreCase("male") || gender.getText().equalsIgnoreCase("female") || gender.getText().equalsIgnoreCase("other"))
            {
                
                pst = conn.prepareStatement("insert into employee (employee_id, first_name, last_name , gender , hiredate , age, salary, job) values(?,?,?,?,?,?,?,?)");
                pst.setInt(1, emp_id_text);
                pst.setString(2, first_name_text);
                pst.setString(3, last_name_text);
                pst.setString(4, gender_text);
                pst.setString(5, hiredate.getEditor().getText());
                pst.setInt(6, age_text);
                pst.setInt(7, salary_text);
                pst.setString(8, job_text);
                pst.executeUpdate();


                ObservableList<Table> d = FXCollections.observableArrayList();
                st = conn.createStatement();
                rs = st.executeQuery("select * from employee");
                while(rs.next()){
                d.add(new Table(rs.getInt("employee_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("gender"), rs.getString("hiredate"), rs.getInt("age"), rs.getInt("salary"), rs.getString("job"))); 
                }
                table_view.setItems(d);
            }
            else{
                JOptionPane.showMessageDialog(null,"Please write gender correctly");
            }
            
        }catch(ClassNotFoundException | SQLException | NumberFormatException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            
        }    
        
    }

    

    @FXML
    private void clearMethod(ActionEvent event) {
        emp_id.setText(null);
        job.setText(null);
        firstName.setText(null);
        lastName.setText(null);
        gender.setText(null);
        
        hiredate.setValue(null);
        age.setText(null);
        salary.setText(null);
        
        
    }

    @FXML
    private void exitMethod(ActionEvent event) {
        JFrame frame = new JFrame("EXIT");
        
        if(JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit the program","Employee Database Management System", JOptionPane.YES_NO_OPTION )== JOptionPane.YES_NO_OPTION){
           System.exit(0);
        }
        
    }

    @FXML
    private void avgSalMethod(ActionEvent event) {
       try{
           
           rs = st.executeQuery("select avg(salary) from employee");
            ObservableList<Table> data1 = FXCollections.observableArrayList();
           while(rs.next()){
            data1.add(new Table(0,"","","","",0,rs.getInt("avg(salary)"),""));
            
           }
            
           table_view.setItems(data1);
       }catch(Exception e)
       {
        
           JOptionPane.showMessageDialog(null,e.getMessage());
       }
    }

    @FXML
    private void sortAscidMethod(ActionEvent event) {
        try{
            
            ObservableList<Table> data2 = FXCollections.observableArrayList();
            rs = st.executeQuery("select * from employee order by employee_id");
            while(rs.next())
            {
                data2.add(new Table(rs.getInt("employee_id") ,rs.getString("first_name"), rs.getString("last_name"), rs.getString("gender"), rs.getString("hiredate"), rs.getInt("age"), rs.getInt("salary"),rs.getString("job")));
            }
            table_view.setItems(data2);
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    @FXML
    private void sortDescIdMethod(ActionEvent event) {
        try{
            
            ObservableList<Table> data3 = FXCollections.observableArrayList();
            rs = st.executeQuery("select * from employee order by employee_id desc");
            while(rs.next())
            {
                data3.add(new Table(rs.getInt("employee_id") ,rs.getString("first_name"), rs.getString("last_name"), rs.getString("gender"), rs.getString("hiredate"), rs.getInt("age"), rs.getInt("salary"),rs.getString("job")));
            }
            table_view.setItems(data3);
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    @FXML
    private void sortAlphfnameMethod(ActionEvent event) {
        try{
            
            ObservableList<Table> data4 = FXCollections.observableArrayList();
            rs = st.executeQuery("select * from employee order by first_name");
            while(rs.next())
            {
                data4.add(new Table(rs.getInt("employee_id") ,rs.getString("first_name"), rs.getString("last_name"), rs.getString("gender"), rs.getString("hiredate"), rs.getInt("age"), rs.getInt("salary"),rs.getString("job")));
            }
            table_view.setItems(data4);
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    @FXML
    private void sortAlphlnameMethod(ActionEvent event) {
        try{
            
            ObservableList<Table> data5 = FXCollections.observableArrayList();
            rs = st.executeQuery("select * from employee order by last_name");
            while(rs.next())
            {
                data5.add(new Table(rs.getInt("employee_id") ,rs.getString("first_name"), rs.getString("last_name"), rs.getString("gender"), rs.getString("hiredate"), rs.getInt("age"), rs.getInt("salary"),rs.getString("job")));
            }
            table_view.setItems(data5);
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    @FXML
    private void removeMethod(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/RemoveFXML.fxml"));
        Stage stage = (Stage)remove.getScene().getWindow();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    @FXML
    private void refreshMethod(ActionEvent event) {
        try{
            
            ObservableList<Table> data6 = FXCollections.observableArrayList();
            rs = st.executeQuery("select * from employee");
            while(rs.next())
            {
                data6.add(new Table(rs.getInt("employee_id") ,rs.getString("first_name"), rs.getString("last_name"), rs.getString("gender"), rs.getString("hiredate"), rs.getInt("age"), rs.getInt("salary"),rs.getString("job")));
            }
            table_view.setItems(data6);
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    @FXML
    private void annualSalMethod(ActionEvent event) {
        try{
            
            ObservableList<Table> data7 = FXCollections.observableArrayList();
            rs = st.executeQuery("select employee_id , first_name, last_name, gender, hiredate, age, salary*12 , job from employee ");
            while(rs.next())
            {
                data7.add(new Table(rs.getInt("employee_id") ,rs.getString("first_name"), rs.getString("last_name"), rs.getString("gender"), rs.getString("hiredate"), rs.getInt("age"), rs.getInt("salary*12"),rs.getString("job")));
            }
            table_view.setItems(data7);
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

   

   

   
    
}
