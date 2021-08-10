/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.View_Controller;

import hsapp.Model.Appointment;
import hsapp.utils.viewHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author christina joy hartshorn
 */
public class ViewAppointmentController implements Initializable {


    
    Appointment appointment;
    @FXML
    private GridPane customerTwoTF;
    @FXML
    private Label idTxt;
    @FXML
    private Label nameTxt;
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
    private Label idLbl;
    @FXML
    private Label nameLbl;
    @FXML
    private Label titleLbl;
    @FXML
    private Label descLbl;
    @FXML
    private Label locationLbl;
    @FXML
    private Label contactLbl;
    @FXML
    private Label typeLbl;
    @FXML
    private Label urlLbl;
    @FXML
    private Label activetxt;
    @FXML
    private Label startDateLbl;
    @FXML
    private Label endDateLbl;
    @FXML
    private Label activetxt1;
    @FXML
    private Label startTimeLbl;
    @FXML
    private Label endTimeLbl;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionBack(ActionEvent event) throws IOException {
        viewHelper vh = new viewHelper();
        vh.changeView("/hsapp/View_Controller/viewAppointments.fxml", event);
    }
    
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
        
        idLbl.setText(Integer.toString(appointment.getAppointmentId()));
        nameLbl.setText(appointment.getCustomerName());
        titleLbl.setText(appointment.getTitle());
        descLbl.setText(appointment.getDescription());
        locationLbl.setText(appointment.getLocation());
        contactLbl.setText(appointment.getContact());
        typeLbl.setText(appointment.getType());
        urlLbl.setText(appointment.getUrl());
        startDateLbl.setText(appointment.getStartDate().toString());
        startTimeLbl.setText(appointment.getStartTime().toString());
        endTimeLbl.setText(appointment.getEndTime().toString());
        endDateLbl.setText(appointment.getEndDate().toString());
     }
}
