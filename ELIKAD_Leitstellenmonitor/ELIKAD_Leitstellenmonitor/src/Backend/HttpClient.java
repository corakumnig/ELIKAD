/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Data.Department;
import Data.Location;
import Data.LoginObject;
import Data.Operation;
import Data.OperationObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author rajick
 */
public class HttpClient {
    private final String target = "http://elikadweb.herokuapp.com/api/";
    private Client client = null;
    private String Token;
    private int idControlcenter;
    private Gson gson;
    
    public HttpClient(){
        client = Client.create();
        gson = new Gson();
    }
    
    public int getControlId(){
        return this.idControlcenter;
    }
    public void TestDeps(){
        WebResource resource = client.resource(target + "departments");

        String response = resource.type(MediaType.APPLICATION_JSON)
                .header("Token", Token)
                .get(String.class);
        
        System.out.println(response);
    }
    
    public ArrayList<Department> getAllDepartements(){
        WebResource resource = client.resource(target + "departments");
        
        String response = resource.type(MediaType.APPLICATION_JSON)
                .header("Token", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwibmFtZSI6IkZGIEhpbW1lbGJlcmciLCJncm91cCI6ImRlcGFydG1lbnQiLCJpYXQiOjE1NTE2ODYzMTd9.ydXNfWydItVvEcU7eLJ6PSdBO_4Y8vGJ65BJmNJSCCU")
                .get(String.class);
        
        return gson.fromJson(response, new TypeToken<ArrayList<Department>>(){}.getType());
    }
    
    public ArrayList<Operation> getCurrentOperations(){
        WebResource resource = client.resource(target + "operations/current");
        
        String response = resource.type(MediaType.APPLICATION_JSON)
                .header("Token", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwibmFtZSI6IkZGIEhpbW1lbGJlcmciLCJncm91cCI6ImRlcGFydG1lbnQiLCJpYXQiOjE1NTE2ODYzMTd9.ydXNfWydItVvEcU7eLJ6PSdBO_4Y8vGJ65BJmNJSCCU")
                .get(String.class);
        
        return gson.fromJson(response, new TypeToken<ArrayList<Operation>>(){}.getType());
    }
    
    public void Login(LoginObject loginObject) throws Exception{
        WebResource resource = client.resource(target + "login/operator");
        
        ClientResponse response = resource.type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, gson.toJson(loginObject, LoginObject.class));
        
        System.out.println(response.getStatus());
        if (response.getStatus() != 200) { //201=created     
            throw new Exception("Login failed, check Password and Username");
        }
        
        this.Token = response.getHeaders().getFirst("Token");
        //ToDo: Unauthorized
        System.out.println(Token);
        this.idControlcenter = Integer.parseInt(response.getHeaders().getFirst("idControlcenter"));
    }
    
    public int insertLocation(Location location) throws Exception{
        int LocationId;
        WebResource resource = client.resource(target + "locations");
        
        ClientResponse response = resource.type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, gson.toJson(location, Location.class));
        
        if (response.getStatus() != 200) { //201=created     
            throw new Exception("Failed to insert");
        }
        
        LocationId = Integer.parseInt(response.getHeaders().getFirst("LocationId"));
        
        return LocationId;
    }
    
    public int insertOperation(OperationObject opOb) throws Exception{
        int idOperation;
        WebResource resource = client.resource(target + "operations");
        
        ClientResponse response = resource.type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, gson.toJson(opOb, OperationObject.class));
        
        if (response.getStatus() != 200) { //201=created     
            throw new Exception("Failed to insert");
        }
        
        idOperation = Integer.parseInt(response.getHeaders().getFirst("LocationId"));
        
        return idOperation;
    }
    
    public void insertDepartments(int idDep, int idOp) throws Exception{
        int idOperation;
        WebResource resource = client.resource(target + "operations/" + idOp + "/departments/" + idDep);
        
        ClientResponse response = resource.type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, "{'ok':'200'}");
        
        if (response.getStatus() != 200) { //201=created     
            throw new Exception("Failed to insert");
        }
    }
}
