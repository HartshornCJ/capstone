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
import static hsapp.View_Controller.NewAppointmentController.calander;
import static hsapp.View_Controller.NewAppointmentController.entered;
import hsapp.utils.TimeCheck;
import hsapp.utils.viewHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author christina joy hartshorn
 */
public class EditAppointmentController implements Initializable {

    @FXML
    private GridPane customerTwoTF;
    @FXML
    private Label idTxt;
    @FXML
    private Label customerTxt;
    @FXML
    private Label customerTwoTxt;
    @FXML
    private Label cityTxt;
    @FXML
    private Label countryTxt;
    @FXML
    private Label zipTxt;
    @FXML
    private Label phoneTxt;
    @FXML
    private Label activetxt;
    @FXML
    private TextField idTF;
    @FXML
    private TextField titleTF;
    @FXML
    private TextField descTF;
    @FXML
    private TextField locationTF;
    @FXML
    private TextField contactTF;
    @FXML
    private TextField typeTF;
    @FXML
    private TextField urlTF;
    @FXML
    private TableView<Customer> customerView;
    @FXML
    private TableColumn<Customer, Integer> idCol;
    @FXML
    private TableColumn<Customer, String> nameCol;
    @FXML
    private TableColumn<Customer, String> addressCol;
    @FXML
    private TableColumn<Customer, String> addressTwoCol;
    @FXML
    private TableColumn<Customer, String> cityCol;
    @FXML
    private TableColumn<Customer, String> countryCol;
    @FXML
    private TableColumn<Customer, String> pcodeCol;
    @FXML
    private TableColumn<Customer, String> phoneCol;
    @FXML
    private Label custIdLbl;
    @FXML
    private Label custNameLbl;
    
    Appointment appointment;
    static Contacts contacts = new Contacts();
    static boolean entered;
    static Calander calander = new Calander();
    viewHelper vh = new viewHelper();
    @FXML
    private TextField startDateTF;
    @FXML
    private TextField endDateTF;
    @FXML
    private TextField startTimeTF;
    @FXML
    private TextField endTimeTF;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(!entered){
            calander = new Calander();
            entered=true;
        }
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("customerId")); //check
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName")); //check
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressTwoCol.setCellValueFactory(new PropertyValueFactory<>("address2"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("cityName")); //check
        countryCol.setCellValueFactory(new PropertyValueFactory<>("countryName")); //check
        pcodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        customerView.setItems(contacts.ObsListCustomer());
    }    

    @FXML
    private void onActionSelect(ActionEvent event) {
        Customer customer = customerView.getSelectionModel().getSelectedItem();
        custIdLbl.setText(Integer.toString(customer.getCustomerId()));
        custNameLbl.setText(customer.getCustomerName());
    }

    @FXML
    private void onActionSave(ActionEvent event) throws IOException {
        try{
            int appointmentId = Integer.parseInt(idTF.getText());
            int customerId = Integer.parseInt(custIdLbl.getText());
            String customerName = custNameLbl.getText();
            String title = titleTF.getText();
            String description = descTF.getText();
            String location = locationTF.getText();
            String contact = contactTF.getText();
            String type = typeTF.getText();
            String url = urlTF.getText();
        
            Date startDate = Date.valueOf(startDateTF.getText());
            Time startTime = Time.valueOf(startTimeTF.getText());
            Date endDate = Date.valueOf(endDateTF.getText());
            Time endTime = Time.valueOf(endTimeTF.getText());
            Appointment appointment =new Appointment(appointmentId, customerId, customerName, title, description, location, contact, type, url, startDate, startTime, endDate, endTime);
            
            if(calander.checkForOverLap(appointment))
            {
               Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Data Issue");
                alert.setHeaderText("Please double check the data");
                alert.setContentText("This appointment overlaps with an appointment already scheduled. Please correct this to contiue");            
                alert.showAndWait();
                return; 
            }
            else if(!TimeCheck.timeBusHours(startTime) || !TimeCheck.timeBusHours(endTime))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Data Issue");
                alert.setHeaderText("Outside of Bussiness Hours");
                alert.setContentText("Please enter appointment times between 07:00:00 and 17:30:00");            
                alert.showAndWait();
                return;  
            }
            
            calander.updateAppointment(appointmentId, appointment);
        }
        catch(IllegalArgumentException e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Data Issue");
            alert.setHeaderText("Please double check the data");
            alert.setContentText("Make sure all feilds filled in correcting and a Customer has been selected");
            
            alert.showAndWait();
            //return;
        }
        vh.changeView("/hsapp/View_Controller/viewAppointments.fxml", event);
        
        
        
    }

    @FXML
    private void onActionCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Your are about to Cancel adding the new appointment.");
        alert.setContentText("Is this ok?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
           vh.changeView("/hsapp/View_Controller/viewAppointments.fxml", event);
        }
    }
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
        
        idTF.setText(Integer.toString(appointment.getAppointmentId()));
        custNameLbl.setText(appointment.getCustomerName());
        titleTF.setText(appointment.getTitle());
        descTF.setText(appointment.getDescription());
        locationTF.setText(appointment.getLocation());
        contactTF.setText(appointment.getContact());
        typeTF.setText(appointment.getType());
        urlTF.setText(appointment.getUrl());
        startDateTF.setText(appointment.getStartDate().toString());
        startTimeTF.setText(appointment.getStartTime().toString());
        endDateTF.setText(appointment.getEndDate().toString());
        endTimeTF.setText(appointment.getEndTime().toString());
        custIdLbl.setText(Integer.toString(appointment.getCustomerId()));
     }
}
