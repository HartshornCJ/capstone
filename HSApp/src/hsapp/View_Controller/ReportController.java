/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.View_Controller;

import hsapp.Model.Report;
import hsapp.Model.ReportObject;
import static hsapp.View_Controller.ViewAppointmentsController.calander;
import hsapp.utils.viewHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author christina joy hartshorn
 */
public class ReportController implements Initializable {


    @FXML
    private TableView<ReportObject> outputView;
    @FXML
    private TableColumn<ReportObject, String> oneCol;
    @FXML
    private TableColumn<ReportObject, String> twoCol;
    @FXML
    private TableColumn<ReportObject, String> threeCol;
    @FXML
    private TableColumn<ReportObject, String> fourCol;
    @FXML
    private TableColumn<ReportObject, String> fiveCol;
    @FXML
    private TableColumn<ReportObject, String> sixCol;
    @FXML
    private TableColumn<ReportObject, String> sevenCol;
    @FXML
    private TableColumn<ReportObject, String> eightCol;
    @FXML
    private ToggleGroup type;
    
    viewHelper vh = new viewHelper();
    Report report; 
    @FXML
    private RadioButton typeR;
    @FXML
    private RadioButton consultantR;
    @FXML
    private RadioButton clientR;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            report = new Report();
        } catch (SQLException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        oneCol.setCellValueFactory(new PropertyValueFactory<>("one")); //check
        twoCol.setCellValueFactory(new PropertyValueFactory<>("two")); //check
        threeCol.setCellValueFactory(new PropertyValueFactory<>("three")); //check
        fourCol.setCellValueFactory(new PropertyValueFactory<>("four"));
        fiveCol.setCellValueFactory(new PropertyValueFactory<>("five"));
        sixCol.setCellValueFactory(new PropertyValueFactory<>("six"));
        sevenCol.setCellValueFactory(new PropertyValueFactory<>("seven")); //check
        eightCol.setCellValueFactory(new PropertyValueFactory<>("eight"));
    }    


    @FXML
    private void onActionType(ActionEvent event) {
        oneCol.setText("Count");
        twoCol.setText("Type");
        threeCol.setText("Year");
        fourCol.setText("Month");
        fiveCol.setText("");
        sixCol.setText("");
        sevenCol.setText("");
        eightCol.setText("");
        outputView.setItems(report.ObsListTypeReport());
    }

    @FXML
    private void onActionConsultant(ActionEvent event) {
        oneCol.setText("User");
        twoCol.setText("Start Date");
        threeCol.setText("Start Time");
        fourCol.setText("End Date");
        fiveCol.setText("End Time");
        sixCol.setText("Title");
        sevenCol.setText("Location");
        eightCol.setText("Client");
        outputView.setItems(report.ObsListConsultantReport());
        
    }
    

    @FXML
    private void onActionClient(ActionEvent event) {
        //set up display that shows the amount of meetins a client has had each month
        oneCol.setText("Count");
        twoCol.setText("Customer");
        threeCol.setText("Year");
        fourCol.setText("Month");
        fiveCol.setText("");
        sixCol.setText("");
        sevenCol.setText("");
        eightCol.setText("");
        //System.out.println("on Action");
        outputView.setItems(report.ObsListClientReport());

    }

    @FXML
    private void onActionMain(ActionEvent event) throws IOException {
        vh.changeView("/hsapp/View_Controller/mainMenu.fxml", event);
    }
    
}
