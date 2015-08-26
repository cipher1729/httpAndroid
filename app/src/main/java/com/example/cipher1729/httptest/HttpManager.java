package com.example.cipher1729.httptest;

import android.content.Entity;
import android.net.http.AndroidHttpClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

/**
 * Created by cipher1729 on 8/26/2015.
 */
public class HttpManager {

   public static String getData(String uri)
   {
       AndroidHttpClient client = AndroidHttpClient.newInstance("AndroidAgent");
       HttpGet request = new HttpGet(uri);
       HttpResponse httpResponse;

       try {
           httpResponse = client.execute(request);
           return
                   EntityUtils.toString(httpResponse.getEntity());
       } catch (IOException e) {
           e.printStackTrace();
           return null;
       }
       finally {
           client.close();
       }
   }
}
