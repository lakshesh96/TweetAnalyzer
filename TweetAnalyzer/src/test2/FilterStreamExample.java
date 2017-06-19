/**
 * Copyright 2013 Twitter, Inc.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

package test2;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

public class FilterStreamExample {
    private static String user,pass,to,subject,body;

  public static void run(String consumerKey, String consumerSecret, String token, String secret) throws InterruptedException {
    BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10000);
    StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
    // add some track terms
    endpoint.trackTerms(Lists.newArrayList("twitterapi","sbi"));

    Authentication auth = new OAuth1(consumerKey, consumerSecret, token, secret);
    // Authentication auth = new BasicAuth(username, password);

    // Create a new BasicClient. By default gzip is enabled.
    Client client = new ClientBuilder()
            .hosts(Constants.STREAM_HOST)
            .endpoint(endpoint)
            .authentication(auth)
            .processor(new StringDelimitedProcessor(queue))
            .build();

    // Establish a connection
    client.connect();

    // Do whatever needs to be done with messages
    for (int msgRead = 0; msgRead < 1000; msgRead++) {
      String msg = queue.take();
      //SEND MESSAGE TO MICROSOFT LUIS API HERE.
      new parseJSON(msg);
      //System.out.println(msg);
    }

    client.stop();

  }

  public static void main(String[] args) {
    
/*
    //Code for Sending Mail.
    user = "laksheshgirdhar";
    pass = "mostluckiest";
    to = "sglgsgpg@hotmail.com";
    subject = "SBI Hackathon";
    body = "Email Sent using JAVA";
    GoogleMail sendMsg = new GoogleMail();
        try {
            sendMsg.Send(user,pass,to,subject,body);
        } catch (MessagingException ex) {
            Logger.getLogger(FilterStreamExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    //Sending Mail Ends
*/
    //Code for Starting Twitter API
    try {  
      FilterStreamExample.run("2Dhc9bOXZUdcAG9eRRw4zaJYr", "6p5cidKBw9Y3OAm9CEQwJniylQw2ryLYpTW3X20o8chj2PraLt", "196983290-DAfgL2iyTJmeTJ9hb4JhSTgYouGHyDSJEe598kmD", "KAMOAd3cMM7iCejmdvbGq5ivryxhPsPty2WGXZQdXOXZP");
    } catch (InterruptedException e) {
      System.out.println(e);
    }
  }
}
