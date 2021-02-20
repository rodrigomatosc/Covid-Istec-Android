package com.example.covid_19.tasks;

import android.os.AsyncTask;

import com.example.covid_19.callbacks.APICallback;
import com.example.covid_19.models.dtos.Country;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APITask extends AsyncTask<URL, Integer, String> {

    private APICallback callback = null;

    public APITask(APICallback callback) {
        this.callback = callback;
    }

    @Override
    protected String doInBackground(URL... urls) {
        URL url = urls[0];
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;
        StringBuilder result = new StringBuilder();

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

        }catch( Exception e) {
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }


        return result.toString();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result ) {
        super.onPostExecute(result);

        if(result != null && !result.equals("") && callback != null){
            callback.finishRequest(result);
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
