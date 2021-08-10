/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp;


import hsapp.DAO.DBConnection;
import hsapp.DAO.Query;
import hsapp.Model.Customer;
import hsapp.Model.City;
import hsapp.Model.Country;
import hsapp.Model.Appointment;
import hsapp.tables.CustomerTB;
import hsapp.tables.CityTB;
import hsapp.tables.CountryTB;
import hsapp.tables.AppointmentTB;
import hsapp.utils.ConvertTime;
import hsapp.utils.WriteFile;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.util.Pair;

/**
 *
 * @author christina joy hartshorn
 */
public class HSApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View_Controller/LoginScreen.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("View_Controller/viewCustomersList.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("View_Controller/mainMenu.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("View_Controller/addAppointment.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException {
        //Connect to the database
        Connection conn = DBConnection.startConnection();
        Query.setConnection(conn);
        
        launch(args);
        //WriteFile.writeUsertTest();
       
        //close connection to the database
        DBConnection.closeConnection();
    }
    
}
