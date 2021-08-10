/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.View_Controller;

import hsapp.Model.Client;
import hsapp.Model.Contact;
import hsapp.Model.Customer;
import static hsapp.View_Controller.ViewCustomersListController.contacts;
import hsapp.utils.viewHelper;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Elicea
 */
public class NewCustomerController implements Initializable {

    @FXML
    private GridPane customerTwoTF;
    @FXML
    private Label idTxt;
    @FXML
    private Label nameTxt;
    @FXML
    private Label addressTxt;
    @FXML
    private Label addressTwoTxt;
    @FXML
    private Label cityTxt;
    @FXML
    private Label countryTxt;
    @FXML
    private Label zipTxt;
    @FXML
    private Label phoneTxt;
    @FXML
    private TextField idTF;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField addressTF;
    @FXML
    private TextField addressTwoTF;
    @FXML
    private TextField cityTF;
    @FXML
    private TextField countryTF;
    @FXML
    private TextField zipTF;
    @FXML
    private TextField phoneTF;
    @FXML
    private Label activeTxt;
    @FXML
    private TextField activeTF;
    @FXML
    private Button save;
    
    viewHelper vh = new viewHelper();
    private Customer customer;
    private Client client;
    private Contact contact;
    
    @FXML
    private RadioButton ClientToggle;
    @FXML
    private RadioButton ContactToggle;
    @FXML
    private Label LabelOne;
    @FXML
    private Label LabelTwo;
    @FXML
    private TextField oneTF;
    @FXML
    private TextField twoTF;
    
    
    
    private ToggleGroup customerGroup;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //configuring the RadioButtons 
        customerGroup = new ToggleGroup();
        this.ClientToggle.setToggleGroup(customerGroup);
        this.ContactToggle.setToggleGroup(customerGroup);
    }    

    @FXML
    private void onActionSave(ActionEvent event) throws IOException {
        try{
            String name = nameTF.getText();
            String address = addressTF.getText();
            String address2 = addressTwoTF.getText();
            String city = cityTF.getText();
            String country = countryTF.getText();
            String zip = zipTF.getText();
            String phone = phoneTF.getText();
            int active = Integer.parseInt(activeTF.getText());



            if(active > 1){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Data Issue");
                alert.setHeaderText("Please double check the data");
                alert.setContentText("Active can only be 0 for inactive or 1 for active");

                alert.showAndWait();
                return;
            }
            else if(name.isEmpty() || address.isEmpty() || city.isEmpty() || country.isEmpty() || zip.isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Data Issue");
                alert.setHeaderText("Please double check the data");
                alert.setContentText("Please fill in all required customer data");

                alert.showAndWait();
                return;
            }
            
            
            if(this.customerGroup.getSelectedToggle().equals(this.ClientToggle)) {
                String type = oneTF.getText();
                String contactMethod = twoTF.getText();
                client = new Client(0, country, 0, city, 0, address, address2, zip, phone, 0, name, active, type, contactMethod);
                contacts.addClient(client);
            }
            if(this.customerGroup.getSelectedToggle().equals(this.ContactToggle)) {
                String company = oneTF.getText();
                int isPrimary = Integer.parseInt(twoTF.getText());
                if(isPrimary > 1){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Data Issue");
                    alert.setHeaderText("Please double check the data");
                    alert.setContentText("isPrimary can only be 0 for not primary or 1 for primary");

                    alert.showAndWait();
                    return;
                }
                contact = new Contact(0, country, 0, city, 0, address, address2, zip, phone, 0, name, active, company, isPrimary);
                contacts.addContact(contact);
            }
            
            
            //customer = new Customer(0, country, 0, city, 0, address, address2, zip, phone, 0, name, active);
            //contacts.addCustomer(customer);
            /*customer.setCustomerName(name);
            customer.setAddress(address);
            customer.setAddress2(address2);
            customer.setCityName(city);
            customer.setCountryName(country);
            customer.setPostalCode(zip);
            customer.setPhone(phone);
            System.out.println(phone);
            customer.setActive(active);
            //contacts.updateCustomer(id, customer);*/

            vh.changeView("/hsapp/View_Controller/viewCustomersList.fxml", event);
        }
        catch(NumberFormatException e)
        {
            //System.out.println("please valid vaules into the text feilds");
            System.out.println("Exception: "+ e);
            //System.out.println("Exception: "+ e.getMessage());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Data Issue");
            alert.setHeaderText("Please double check the data");
            alert.setContentText("Please enter Vaild Values into the Text Feilds");

            alert.showAndWait();
        }
        catch(NullPointerException e)
        {
            System.out.println(e);
        }
    }

    @FXML
    private void onActionCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Your are about to Cancel adding the new Customer.");
        alert.setContentText("Is this ok?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
           vh.changeView("/hsapp/View_Controller/viewCustomersList.fxml", event);
        }
    }

    @FXML
    private void customerStateToggle(ActionEvent event) {
        
        if(this.customerGroup.getSelectedToggle().equals(this.ClientToggle)) {
            LabelOne.setText("Client Type");
            LabelTwo.setText("Contact Method");
        }
        
        if(this.customerGroup.getSelectedToggle().equals(this.ContactToggle)) {
            LabelOne.setText("Company");
            LabelTwo.setText("Is Primary");
        }
        
    }
    
}
