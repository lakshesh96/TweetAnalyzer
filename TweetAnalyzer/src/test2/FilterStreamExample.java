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

  public static void run(String consumerKey, String consumerSecret, String token, String secret) throws InterruptedException {
    BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10000);
    StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
    endpoint.trackTerms(Lists.newArrayList("TheOfficialSBI","sbi"));

    Authentication auth = new OAuth1(consumerKey, consumerSecret, token, secret);

    Client client = new ClientBuilder()
            .hosts(Constants.STREAM_HOST)
            .endpoint(endpoint)
            .authentication(auth)
            .processor(new StringDelimitedProcessor(queue))
            .build();
    client.connect();

    for (int msgRead = 0; msgRead < 100; msgRead++) {
      String msg = queue.take();
      new parseJSON(msg);
    }
    client.stop();

  }

  public static void main(String[] args) {
    try {  
      FilterStreamExample.run("2Dhc9bOXZUdcAG9eRRw4zaJYr", "6p5cidKBw9Y3OAm9CEQwJniylQw2ryLYpTW3X20o8chj2PraLt", "196983290-DAfgL2iyTJmeTJ9hb4JhSTgYouGHyDSJEe598kmD", "KAMOAd3cMM7iCejmdvbGq5ivryxhPsPty2WGXZQdXOXZP");
    } catch (InterruptedException e) {
      System.out.println("Please Restart and Connect to Internet.");
    }
  }
}
