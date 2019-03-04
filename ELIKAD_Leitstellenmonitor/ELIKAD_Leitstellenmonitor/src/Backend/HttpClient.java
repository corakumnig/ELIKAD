/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Data.Department;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author rajick
 */
public class HttpClient {
    private final String target = "http://elikadweb.herokuapp.com/api/";
    private Client client = null;
    private Gson gson;
    
    public HttpClient(){
        client = Client.create();
        gson = new Gson();
    }
    
    public void TestDeps(){
        WebResource resource = client.resource(target + "departments");

        String response = resource.type(MediaType.APPLICATION_JSON)
                .header("Token", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwibmFtZSI6IkZGIEhpbW1lbGJlcmciLCJncm91cCI6ImRlcGFydG1lbnQiLCJpYXQiOjE1NTE2ODYzMTd9.ydXNfWydItVvEcU7eLJ6PSdBO_4Y8vGJ65BJmNJSCCU")
                .get(String.class);
        
        System.out.println(response);
    }
}
