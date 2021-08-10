/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.View_Controller;

import hsapp.Model.Client;
import hsapp.Model.Contact;
import hsapp.Model.Customer;
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
public class ViewCustomerController implements Initializable {


    
    Customer customer;
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
    private Label idLbl;
    @FXML
    private Label nameLbl;
    @FXML
    private Label addressrLbl;
    @FXML
    private Label addressTwoLbl;
    @FXML
    private Label cityLbl;
    @FXML
    private Label countryLbl;
    @FXML
    private Label zipLbl;
    @FXML
    private Label phoneLbl;
    @FXML
    private Label activetxt;
    @FXML
    private Label activeLbl;
    @FXML
    private Label optionLbl;
    @FXML
    private Label LabelOne;
    @FXML
    private Label LabelTwo;
    @FXML
    private Label oneLbl;
    @FXML
    private Label twoLbl1;


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
        vh.changeView("/hsapp/View_Controller/viewCustomersList.fxml", event);
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
        
        idLbl.setText(Integer.toString(customer.getCustomerId()));
        nameLbl.setText(customer.getCustomerName());
        addressrLbl.setText(customer.getAddress());
        addressTwoLbl.setText(customer.getAddress2());
        cityLbl.setText(customer.getCityName());
        countryLbl.setText(customer.getCountryName());
        zipLbl.setText(customer.getPostalCode());
        phoneLbl.setText(customer.getPhone());
        activeLbl.setText(Integer.toString(customer.getActive()));
        
        
        if(customer instanceof Client){
            LabelOne.setText("Client Type");
            LabelTwo.setText("Contact Method");
            optionLbl.setText("Client");
            oneLbl.setText(((Client) customer).getType());
            twoLbl1.setText(((Client) customer).getContactMethod());
        }
        else if(customer instanceof Contact){
            LabelOne.setText("Company");
            LabelTwo.setText("Is Primary");
            optionLbl.setText("Contact");
            oneLbl.setText(((Contact) customer).getCompany());
            twoLbl1.setText(Integer.toString(((Contact) customer).getIsPrimary()));
        }
        else{
            System.out.println("error");
        }
        
     }
    
}
