/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
        


/**
 *
 * @author LAKSHESH
 */
public class Test2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HttpClient httpclient = HttpClients.createDefault();
        try{
            URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/luis/v1.0/prog/apps/75c2f6ab-4642-4049-b229-f4f4f58245db/actionChannels");
            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", "{subscription key}");


            // Request body
            StringEntity reqEntity = new StringEntity("{body}");
            //request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
                //System.out.println(EntityUtils.toString(entity));
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
}
