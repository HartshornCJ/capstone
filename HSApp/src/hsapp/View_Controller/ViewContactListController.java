/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.View_Controller;

import hsapp.Model.Contact;
import hsapp.Model.Contacts;
import hsapp.Model.Customer;
import static hsapp.View_Controller.ViewCustomersListController.entered;
import hsapp.utils.viewHelper;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Elicea
 */
public class ViewContactListController implements Initializable {

    @FXML
    private Label headerTxt;
    @FXML
    private TableView<Contact> customerView;
    @FXML
    private TableColumn<Contact, Integer> idCol;
    @FXML
    private TableColumn<Contact, String> nameCol;
    @FXML
    private TableColumn<Contact, String> phoneCol;
    @FXML
    private TableColumn<Contact, Integer> activeCol;
    @FXML
    private TableColumn<Contact, String> companyCol;
    @FXML
    private TableColumn<Contact, Integer> isPrimaryCol;
    @FXML
    private Button newCTxt;
    @FXML
    private Button editCTxt;
    @FXML
    private Button viewDetailsTxt;
    @FXML
    private Button mainMenuTxt;
    @FXML
    private TextField searchTF;
    
    viewHelper vh = new viewHelper();
    
    static Contacts contacts;
    static boolean entered;
    
    protected ObservableList<Contact> data;
    protected ObservableList<Contact> search;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if(!entered){
            contacts = new Contacts();
            entered=true;
        }
        data = FXCollections.observableArrayList();
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("customerId")); //check
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName")); //check
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        activeCol.setCellValueFactory(new PropertyValueFactory<>("active"));
        companyCol.setCellValueFactory(new PropertyValueFactory<>("company"));
        isPrimaryCol.setCellValueFactory(new PropertyValueFactory<>("isPrimary"));

        for(Customer customer: contacts.ObsListCustomer()){
            if(customer instanceof Contact){
                data.add((Contact) customer);            
            }
        }
        
        customerView.setItems(data);
    }    

    @FXML
    private void onActionNewCustomer(ActionEvent event) throws IOException {
        vh.changeView("/hsapp/View_Controller/newCustomer.fxml", event);
    }

    @FXML
    private void onActionEditCustomer(ActionEvent event) throws IOException {
        Customer customer=customerView.getSelectionModel().getSelectedItem();
        
        if(customer!=null){
            //vh.changeView("/hsapp/View_Controller/viewCustomer.fxml", event);
            Stage stage; 
            Parent root;       
            stage=(Stage) editCTxt.getScene().getWindow();
            //load up OTHER FXML document
            FXMLLoader loader=new FXMLLoader(getClass().getResource(
                "editCustomer.fxml"));
            root =loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            
            EditCustomerController controller = loader.getController();
            //controller.setPart(part);
            controller.setCustomer(customer);
        }
    }

    @FXML
    private void onActionViewDetails(ActionEvent event) throws IOException {
        Customer customer=customerView.getSelectionModel().getSelectedItem();
        
        if(customer!=null){
            //vh.changeView("/hsapp/View_Controller/viewCustomer.fxml", event);
            Stage stage; 
            Parent root;       
            stage=(Stage) viewDetailsTxt.getScene().getWindow();
            //load up OTHER FXML document
            FXMLLoader loader=new FXMLLoader(getClass().getResource(
                "viewCustomer.fxml"));
            root =loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            
            ViewCustomerController controller = loader.getController();
            //controller.setPart(part);
            controller.setCustomer(customer);
        }
    }

    @FXML
    private void onActionDeleteCustomer(ActionEvent event) {
        //inventroy.removePart(partTable.getSelectionModel().getSelectedItem());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Your are about to Delete the selected Customer.");
        alert.setContentText("Is this ok?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            contacts.removeCustomer(customerView.getSelectionModel().getSelectedItem());
            data.remove(customerView.getSelectionModel().getSelectedItem());
            customerView.setItems(data);
            
        }
    }

    @FXML
    private void onActionMainMenu(ActionEvent event) throws IOException {
        vh.changeView("/hsapp/View_Controller/mainMenu.fxml", event);
    }

    @FXML
    private void onActionSearch(ActionEvent event) {
        search = FXCollections.observableArrayList();
        String searchTerm = searchTF.getText();
        for(Contact contact: data){
            if(contact.getCompany().contains(searchTerm) || contact.getCustomerName().contains(searchTerm)){
                search.add(contact);            
            }
        }
        
        customerView.setItems(search);
    }
    
}
