package com.stanly.ar9am;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import io.realm.Realm;

public class SplashActivity extends FragmentActivity {


    //https://jsonblob.com/b548aca4-6358-11e7-ae4c-fbb837ffe2c8
    //https://gist.githubusercontent.com/anonymous/9a0eabfe91ab9e27adc1151aabf75c68/raw/79de054ba3b2f8d9428e31adc7578793e5a52db0/b548aca4-6358-11e7-ae4c-fbb837ffe2c8.json
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Realm r = Realm.getDefaultInstance();
        r.beginTransaction();
        r.where(Application.class).findAll().deleteAllFromRealm();

        r.where(girl.class).findAll().deleteAllFromRealm();

        r.where(ElementSwipe.class).findAll().deleteAllFromRealm();
        r.where(Data.class).findAll().deleteAllFromRealm();


        r.commitTransaction();

        new HttpAsyncTask().execute("https://gist.githubusercontent.com/anonymous/57915312426d4df3257ea9f8a50e3e01/raw/ea6c5dfc7354e630d128168318256ce184ecff29/b548aca4-6358-11e7-ae4c-fbb837ffe2c8.json");

    }
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(final String result) {
            //Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();

            Realm r = Realm.getDefaultInstance();
            r.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.createOrUpdateObjectFromJson(Application.class, result);
                }
            });
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();

        }
    }
    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.e("InputStream", e.getLocalizedMessage());
        }

        return result;
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

}
