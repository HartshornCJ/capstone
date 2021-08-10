/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.View_Controller;

import hsapp.Model.Appointment;
import hsapp.Model.Calander;
import hsapp.Model.Contacts;
import hsapp.Model.Customer;
import static hsapp.View_Controller.ViewCustomersListController.contacts;
//import static hsapp.View_Controller.ViewCustomersListController.entered;
import hsapp.utils.viewHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author christina joy hartshorn
 */
public class ViewAppointmentsController implements Initializable {

    
    viewHelper vh = new viewHelper();
    
    static Calander calander;
    static boolean entered; 
    
    @FXML
    private Label calMonthHeaderTxt;
    @FXML
    private RadioButton weeik;
    @FXML
    private ToggleGroup toggleCalendar;
    @FXML
    private RadioButton month;
    @FXML
    private RadioButton all;
    @FXML
    private TableView<Appointment> caendarView;

    @FXML
    private TableColumn<Appointment, String> titleCol;
    @FXML
    private TableColumn<Appointment, String> customerCol;
    @FXML
    private TableColumn<Appointment, String> locationCol;
    @FXML
    private TableColumn<Appointment, String> urlCol;
    @FXML
    private Button newATxt;
    @FXML
    private Button editATxt;
    @FXML
    private Button viewDetailsTxt;
    @FXML
    private Button backTxt;
    @FXML
    private TableColumn<Appointment, Date> startDateCol;
    @FXML
    private TableColumn<Appointment, Time> startTimeCol;
    @FXML
    private TableColumn<Appointment, Date> endDateCol;
    @FXML
    private TableColumn<Appointment, Time> endTimeCol;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //if(!entered){
            calander = new Calander();
            entered=true;
        //}
        
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime")); //check
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime")); //check
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate")); //check
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        customerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location")); //check
        urlCol.setCellValueFactory(new PropertyValueFactory<>("url")); //check

        caendarView.setItems(calander.ObsListAppointment());
    }    

    @FXML
    private void onActionNewAppointment(ActionEvent event) throws IOException {
        try
        {
            vh.changeView("/hsapp/View_Controller/newAppointment.fxml", event);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        //vh.changeView("/hsapp/View_Controller/addAppointment.fxml", event);
    }

    @FXML
    private void onActionEditAppointment(ActionEvent event) {
        try
        {
            Appointment appointment=caendarView.getSelectionModel().getSelectedItem();
        
            if(appointment!=null){
             Stage stage; 
            Parent root;       
            stage=(Stage) viewDetailsTxt.getScene().getWindow();
            //load up OTHER FXML document
            FXMLLoader loader=new FXMLLoader(getClass().getResource(
                "editAppointment.fxml"));
            root =loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
            EditAppointmentController controller = loader.getController();
             controller.setAppointment(appointment);
            }
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }

    @FXML
    private void onActionViewDetails(ActionEvent event) throws IOException {
        Appointment appointment=caendarView.getSelectionModel().getSelectedItem();
        
        if(appointment!=null){
            //vh.changeView("/hsapp/View_Controller/viewAppointment.fxml", event);
            Stage stage; 
            Parent root;       
            stage=(Stage) viewDetailsTxt.getScene().getWindow();
            //load up OTHER FXML document
            FXMLLoader loader=new FXMLLoader(getClass().getResource(
                "viewAppointment.fxml"));
            root =loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            
            ViewAppointmentController controller = loader.getController();
            //controller.setPart(part);
            controller.setAppointment(appointment);
        }
    }

    @FXML
    private void onBack(ActionEvent event) throws IOException {
        vh.changeView("/hsapp/View_Controller/mainMenu.fxml", event);
    }

    @FXML
    private void onActionWeek(ActionEvent event) {
        caendarView.setItems(calander.ObsListAppointmentWeek());
    }

    @FXML
    private void onActionMonth(ActionEvent event) {
        caendarView.setItems(calander.ObsListAppointmentMonth());
    }

    @FXML
    private void onActionAll(ActionEvent event) {
        caendarView.setItems(calander.ObsListAppointment());
    }

    @FXML
    private void onActionDelete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Your are about to Delete the selected Appointments.");
        alert.setContentText("Is this ok?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            //contacts.removeCustomer(customerView.getSelectionModel().getSelectedItem());
            //customerView.setItems(contacts.ObsListCustomer());
            calander.removeAppointment(caendarView.getSelectionModel().getSelectedItem());
            caendarView.setItems(calander.ObsListAppointment());
        }
    }
    
}
