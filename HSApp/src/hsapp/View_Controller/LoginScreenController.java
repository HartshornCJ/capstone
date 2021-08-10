/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.View_Controller;

import hsapp.tables.User;
import hsapp.utils.viewHelper;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author christina joy hartshorn
 */
public class LoginScreenController implements Initializable {

    @FXML
    private Label titleTxt;
    @FXML
    private Label usernameTxt;
    @FXML
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private Label passwordTxt;
    @FXML
    private Label errorMessageTxt;
    @FXML
    private Button loginBtn;
    
    ResourceBundle lb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // TODO
         Locale english = Locale.US;
         //Locale english = Locale.FRANCE;
          if(Locale.getDefault().getLanguage().equals("fr"))
              lb = ResourceBundle.getBundle("hsapp/utils/Nat", Locale.getDefault());
          else
              lb = ResourceBundle.getBundle("hsapp/utils/Nat", english);
         
        titleTxt.setText(lb.getString("Signin"));
        usernameTxt.setText(lb.getString("Username"));       
        passwordTxt.setText(lb.getString("Password"));       
        loginBtn.setText(lb.getString("Login"));
    }    

    @FXML
    private void onActionLogin(ActionEvent event) throws SQLException, IOException {
        //System.out.println(usernameTF.getText() + " - " + passwordTF.getText());
        if(User.verifyAccount(usernameTF.getText(), passwordTF.getText()))
        {
            viewHelper vh = new viewHelper();
            vh.changeView("/hsapp/View_Controller/mainMenu.fxml", event);

        }

        else
        {
        errorMessageTxt.setText(lb.getString("errorUP"));
        }
    }
    
}
