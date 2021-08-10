/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.View_Controller;

import hsapp.Model.Contact;
import hsapp.Model.Contacts;
import hsapp.Model.Customer;
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
 * @author christina joy hartshorn
 */
public class ViewCustomersListController implements Initializable {

    @FXML
    private Label headerTxt;
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
    private Button newCTxt;
    @FXML
    private Button editCTxt;
    @FXML
    private Button viewDetailsTxt;
    @FXML
    private Button mainMenuTxt;
    
    viewHelper vh = new viewHelper();
    
    static Contacts contacts;
    static boolean entered;
    @FXML
    private TextField searchTF;
    
    protected ObservableList<Customer> search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(!entered){
            contacts = new Contacts();
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
        //ObservableList<Part> parts = FXCollections.observableArrayList(inv.allParts);
        //ObservableList<Customer> data = FXCollections.observableArrayList();
        //data = Contacts.ObsListCustomer();
        customerView.setItems(contacts.ObsListCustomer());
        
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
    private void onActionMainMenu(ActionEvent event) throws IOException {
        vh.changeView("/hsapp/View_Controller/mainMenu.fxml", event);
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
            customerView.setItems(contacts.ObsListCustomer());
            
        }
    }

    @FXML
    private void onActionSearch(ActionEvent event) {
        search = FXCollections.observableArrayList();
        String searchTerm = searchTF.getText();
        for(Customer customer: contacts.ObsListCustomer()){
            if(customer.getCityName().contains(searchTerm) || customer.getCustomerName().contains(searchTerm) || customer.getCountryName().contains(searchTerm)
                    ||customer.getCityName().contains(searchTerm)|| customer.getCountryName().contains(searchTerm)){
                search.add(customer);            
            }
        }
        
        customerView.setItems(search);
    }
    
}
