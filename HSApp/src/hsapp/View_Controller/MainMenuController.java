/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.View_Controller;

import hsapp.Model.Calander;
import static hsapp.View_Controller.ViewAppointmentsController.calander;
import hsapp.tables.User;
import hsapp.utils.WriteFile;
import hsapp.utils.viewHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author christina joy hartshorn
 */
public class MainMenuController implements Initializable {

    @FXML
    private Label homePageTxt;
    @FXML
    private Label mMessageLbl;
    @FXML
    private Button custDataBtn;
    @FXML
    private Button signOutTxt;
    
    viewHelper vh = new viewHelper();
    @FXML
    private Button calBtn;

    Calander calander;
    @FXML
    private Button contactDataBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        calander = new Calander();
        mMessageLbl.setText(calander.checkForAppintmentShortly());
        
    }    


    @FXML
    private void onActionCustData(ActionEvent event) throws IOException {
        vh.changeView("/hsapp/View_Controller/viewCustomersList.fxml", event);
    }

    @FXML
    private void onActionSignOut(ActionEvent event) throws IOException {
        //WriteFile.writeUserLog(User.getUserId()+" "+User.getUser()+" Signed out");
        vh.changeView("/hsapp/View_Controller/LoginScreen.fxml", event);
    }

    @FXML
    private void OnActionCal(ActionEvent event) throws IOException {
        vh.changeView("/hsapp/View_Controller/viewAppointments.fxml", event);
    }

    @FXML
    private void onActionReports(ActionEvent event) throws IOException {
        vh.changeView("/hsapp/View_Controller/report.fxml", event);
    }

    @FXML
    private void onActionContactData(ActionEvent event) throws IOException {
        vh.changeView("/hsapp/View_Controller/ViewContactList.fxml", event);
    }
    
}
