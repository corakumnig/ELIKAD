/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elikad_leitstellenmonitor;

import Backend.HttpClient;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 *
 * @author rajick
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtNumber;

    @FXML
    private ListView<?> listSelected;

    @FXML
    private TextField txtObject;

    @FXML
    private TextField txtAlaramLevel;

    @FXML
    private Button btnCalculate;

    @FXML
    private TextField txtPost;

    @FXML
    private ListView<?> listNear;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtPlace;

    @FXML
    private TextField txtReason;

    @FXML
    private TextField txtCaller;

    @FXML
    void onButtonClicked(ActionEvent event) {
        try{
            HttpClient client = new HttpClient();
            client.TestDeps();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }     
}
