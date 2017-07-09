package com.stanly.ar9am;

import android.content.Context;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by nizar on 08/07/17.
 */

public class Utils {

    private static final int timeout = 20000;
    public static String retreiveJsonFromAssetsFile(String fileName, Context context) {
        StringBuilder returnString = new StringBuilder();


        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;


        try {
            fIn = context.getResources().getAssets()
                    .open(fileName, Context.MODE_WORLD_READABLE);
            isr = new InputStreamReader(fIn);//,"ISO-8859-1");
            input = new BufferedReader(isr);
            String line = "";

            while ((line = input.readLine()) != null) {
                returnString.append(line);
                //Log.e(" File content :  "," ::  "+returnString);


            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        return returnString.toString();
    }

    public static String retrieveJson(String url) {
        HttpGet getRequest = new HttpGet(url);

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, timeout);
            httpClient.setParams(httpParams);
            HttpResponse getResponse = null;
            try {
                getResponse = httpClient.execute(getRequest);
            } catch (ClientProtocolException e) {
                Log.w("get Response error", ""+e.getMessage());
                e.printStackTrace();
            }
            final int statusCode = getResponse.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }

            HttpEntity getResponseEntity = getResponse.getEntity();

            if (getResponseEntity != null) {

                String s = EntityUtils.toString(getResponseEntity, HTTP.UTF_8 );;

                return s;//EntityUtils.toString(getResponseEntity, HTTP.UTF_8 );
            }

        } catch (ConnectTimeoutException e) {
            Log.w("Timeout exception "+timeout,   url, e);
        }catch (IOException e) {
            getRequest.abort();
            Log.w("Error in URL ",   url, e);
        }

        return null;

    }



}
