/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;
/**
 *
 * @author LAKSHESH
 */
public class parseJSON {
    private String data;
    
    public parseJSON(){}
    
    public parseJSON(String data){
        this.data = data;
        try {
            JSONObject obj = new JSONObject(data);
            String text = obj.getString("text");
            String name = obj.getJSONObject("user").getString("name");
            String country = obj.getJSONObject("user").getString("location");
            System.out.println(text);
            System.out.println(name);
            System.out.println(country);
            
            
        } catch (JSONException ex) {
            Logger.getLogger(parseJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
