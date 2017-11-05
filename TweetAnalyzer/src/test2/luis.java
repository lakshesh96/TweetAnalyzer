package test2;

/**
 *
 * @author LAKSHESH
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class luis {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      //BufferedReader reader = new BufferedReader(new InputStreamReader(((HttpURLConnection) (new URL(url)).openConnection()).getInputStream(), Charset.forName("UTF-8")));

      //String jsonText = readAll(rd);
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }

    public luis(String text,String name, String location) throws IOException,JSONException{
    String url = "https://westus.api.cognitive.microsoft.com/luis/v2.0/apps/[Input LUIS API Key]";
    String append = null;
    String encodedUrl = null;
    try {
          append = URLEncoder.encode(text, "UTF-8");
          encodedUrl = url+append;
    } catch (UnsupportedEncodingException ignored) {
    }
    JSONObject json = readJsonFromUrl(encodedUrl);
    new parseJSON(json,text,name,location);
  }
}
