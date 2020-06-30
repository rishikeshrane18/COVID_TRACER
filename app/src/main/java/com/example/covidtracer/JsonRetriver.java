package com.example.covidtracer;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import static com.example.covidtracer.MainActivity.INDIA_OPT;
import static com.example.covidtracer.MainActivity.WORLD_OPT;
import static com.example.covidtracer.MainActivity.populate_india;
import static com.example.covidtracer.MainActivity.populate_world;

public class JsonRetriver extends AsyncTask<String,Void,String> {

   private int option;
    public JsonRetriver(int option) {
        this.option = option;
    }

    @Override
    protected String doInBackground(String... urls) {

        String result = "";
        URL url;
        HttpURLConnection urlConnection;
        urlConnection = null;
        try {
            url = new URL( urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);

            int data = reader.read();
            while (data!= -1){
                char current = (char) data;
                result += current;
                data = reader.read();
            }
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if(option == WORLD_OPT)
            populate_world(result);
        //else if(option == INDIA_OPT)
        else
            populate_india(result);
    }
}
