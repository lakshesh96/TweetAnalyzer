package test2;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import org.json.*;
/**
 *
 * @author LAKSHESH
 */
public class parseJSON {
    private String data;
    private static String user,pass,to,subject,body,additional;
    
    public parseJSON(){}
    
    public parseJSON(String data){
        this.data = data;
        try {
            JSONObject obj = new JSONObject(data);
            String text = obj.getString("text");
            String name = obj.getJSONObject("user").getString("name");
            String location = obj.getJSONObject("user").getString("location");
            String lang = obj.getJSONObject("user").getString("lang");
            if(lang.equalsIgnoreCase("en")){
            try {
                new luis(text,name,location);
            } catch (IOException ex) {
                Logger.getLogger(parseJSON.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
            
        } catch (JSONException ex) {
            Logger.getLogger(parseJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public parseJSON(JSONObject object,String text,String name, String location) throws JSONException{
        String intent = object.getJSONObject("topScoringIntent").getString("intent");

    user = "sbihackathon2017";
    pass = "hackathon2017";
    to = "sbihackathon2017@gmail.com";
    subject = "SBI Hackathon: Issue Regarding: " + intent;
    additional = "Query forward to Department: "+intent+System.lineSeparator()+System.lineSeparator()+"Tweet: "+System.lineSeparator()+text+System.lineSeparator()+System.lineSeparator()+"Raised By: "+name+System.lineSeparator()+System.lineSeparator();
    if(location != null && !location.equalsIgnoreCase("null")){
        body = additional + "Location: " + location;
    }
    else{
        body = additional;
    }
    System.out.println("");
    System.out.println("Mail of: " + name + " sent successfully to Department: " + intent);
    GoogleMail sendMsg = new GoogleMail();
        try {
            sendMsg.Send(user,pass,to,subject,body);
        } catch (MessagingException ex) {
            Logger.getLogger(FilterStreamExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
