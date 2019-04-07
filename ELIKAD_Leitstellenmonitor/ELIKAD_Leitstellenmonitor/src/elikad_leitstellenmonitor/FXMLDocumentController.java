/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elikad_leitstellenmonitor;

import Backend.Geocoder;
import Backend.HttpClient;
import Data.Department;
import Data.Location;
import Data.Operation;
import Data.OperationObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
/**
 *
 * @author rajick
 */
public class FXMLDocumentController implements Initializable{
    private Geocoder geocoder;
    private ArrayList<Department> allDeps = new ArrayList<>();
    private HttpClient client;
    
    @FXML
    private Label lblMessage;
    
    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtNumber;

    @FXML
    private TextField txtNTelNumber;
    
    @FXML
    private ListView<Department> listSelected;

    @FXML
    private TextField txtObject;

    @FXML
    private TextField txtAlaramLevel;

    @FXML
    private Button btnCalculate;

    @FXML
    private TextField txtPost;

    @FXML
    private ListView<Department> listNear;

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
            if(event.getSource() == this.btnCalculate){
                checkInputs();
                fillListNear();
                //getLocation();
                //System.out.println("Geocoder: " + geocoder.getJSONByGoogle(txtAddress.getText() + " " + txtNumber.getText() + ", " + txtPost.getText() + " " + txtPlace.getText()));
            }
            else if(event.getSource() == this.btnAdd){
                checkInputs();
                checkListSelected();
                insertOperation(createNewOperation());
            }
        }
        catch(Exception ex)
        {
            this.lblMessage.setText("Error: " + ex.getMessage());
        }
    }
    
    @FXML
    void onListSelected(MouseEvent event) {
        if(event.getSource() == listNear)
        {
            Department selected = listNear.getSelectionModel().getSelectedItem();
            if(!listSelected.getItems().contains(selected))
                listSelected.getItems().add(selected);
        }
        else if(event.getSource() == listSelected)
        {
            Department selected = listSelected.getSelectionModel().getSelectedItem();
            listSelected.getItems().remove(selected);
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            geocoder = new Geocoder();
            client = new HttpClient();
            allDeps = client.getAllDepartements();
            System.out.println(allDeps);
        }
        catch(Exception ex)
        {
            lblMessage.setText("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }     
    
    private void checkInputs() throws Exception{
        if(txtDescription.getText().trim().equals("") || txtDescription.getText() == null)
        {
            throw new Exception("You have to fill the field Description");
        }
        if(txtNumber.getText().trim().equals("") || txtNumber.getText() == null)
        {
            throw new Exception("You have to fill the field Number");
        }
        if(txtNTelNumber.getText().trim().equals("") || txtNTelNumber.getText() == null)
        {
            throw new Exception("You have to fill the field Tel Number");
        }
        if(txtObject.getText().trim().equals("") || txtObject.getText() == null)
        {
            throw new Exception("You have to fill the field Object");
        }
        if(txtAlaramLevel.getText().trim().equals("") || txtAlaramLevel.getText() == null)
        {
            throw new Exception("You have to fill the field Alarm Level");
        }
        if(txtPost.getText().trim().equals("") || txtPost.getText() == null)
        {
            throw new Exception("You have to fill the field Description");
        }
        if(txtAddress.getText().trim().equals("") || txtAddress.getText() == null)
        {
            throw new Exception("You have to fill the field Address");
        }
        if(txtPlace.getText().trim().equals("") || txtPlace.getText() == null)
        {
            throw new Exception("You have to fill the field Place");
        }
        if(txtReason.getText().trim().equals("") || txtReason.getText() == null)
        {
            throw new Exception("You have to fill the field Reason");
        }
        if(txtCaller.getText().trim().equals("") || txtCaller.getText() == null)
        {
            throw new Exception("You have to fill the field Caller");
        }
    }
    
    private void checkListSelected() throws Exception{
        if(listSelected.getItems().isEmpty()){
            throw new Exception("You have to select atleast one department");
        }
    }
    private void fillListNear(){
        listNear.getItems().clear();
        listNear.getItems().addAll(allDeps);
    }
    
    private Operation createNewOperation(){
        Location location = new Location(1, txtNumber.getText(), txtAddress.getText(), txtPost.getText(), txtPlace.getText());
        Operation newOperation = new Operation(1, Date.from(Instant.now()), txtCaller.getText(), txtDescription.getText(), Integer.parseInt(txtAlaramLevel.getText()), txtReason.getText(), location, 0);
        for(Department d : this.listSelected.getItems())
        {
            newOperation.addDepartment(d);
        }
        return newOperation;
    }

    private void getLocation() throws Exception{
        Gson gson = new Gson();
        String geoJsonString = geocoder.getJSONByGoogle(txtAddress.getText() + " " + txtNumber.getText() + ", " + txtPost.getText() + " " + txtPlace.getText());
        System.out.println(geoJsonString);
        JsonObject jobj = new Gson().fromJson(geoJsonString, JsonObject.class);
        
        System.out.println(gson.fromJson(jobj.get("results").getAsString(), JsonObject.class).get("geometry").getAsString());
    }
    
    private void insertOperation(Operation operation) throws Exception
    {
        int locationId;
        int operationId;
        OperationObject opOb;
        
        locationId = client.insertLocation(operation.getLocation());
        opOb = new OperationObject(client.getControlId(), operation.getDatetime(), operation.getCaller(), operation.getDescription(), operation.getAlarmlevel(), locationId);
        operationId = client.insertOperation(opOb);
        
        for(Department d : operation.getCollDepartements())
        {
            client.insertDepartments(d.getId(), operationId);
        }
    }
}
