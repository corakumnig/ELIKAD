/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;

import Backend.HttpClient;
import Data.LoginObject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author rajick
 */
public class LoginController implements Initializable {

    @FXML
    private Label lblMessage;

    @FXML
    private TextField txtUsername;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void onButtonAction(ActionEvent event) {
        HttpClient client = new HttpClient();
        try{
            client.Login(new LoginObject(txtUsername.getText(), txtPassword.getText()));
            lblMessage.setText("Logged in");
            
            Stage stage = new Stage();
            stage.setScene(new Scene(new FXMLLoader(getClass().getResource("/elikad_leitstellenmonitor/FXMLDocument.fxml")).load()));  
            stage.show();
            
            ((Stage)btnLogin.getScene().getWindow()).close();
        }
        catch(Exception ex)
        {
            lblMessage.setText("Failed: " + ex.getMessage());
        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
