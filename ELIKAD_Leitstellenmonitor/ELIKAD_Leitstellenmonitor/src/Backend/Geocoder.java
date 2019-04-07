/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.apache.commons.io.IOUtils;
/**
 *
 * @author rajick
 */
public class Geocoder {
    private static final String URL = "https://maps.googleapis.com/maps/api/geocode/json"; 
    
    public String getJSONByGoogle(String address) throws Exception
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
        URL url = new URL(URL + "?address=" + URLEncoder.encode(address, "UTF-8")+ "&sensor=false" + "&key=AIzaSyDeB_H4uW4RLnwrzcTrlL7icE5pN2UefRc");
        URLConnection conn = url.openConnection();
        IOUtils.copy(conn.getInputStream(), output);
        output.close();
        return output.toString();
    }
}
