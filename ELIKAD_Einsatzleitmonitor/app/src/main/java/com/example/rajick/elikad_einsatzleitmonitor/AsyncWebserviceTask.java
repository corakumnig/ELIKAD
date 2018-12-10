package com.example.rajick.elikad_einsatzleitmonitor;

import android.content.Context;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncWebserviceTask extends AsyncTask<String, Void, TaskResult> {

    private static final String BASE_URL = "http://192.168.43.142:8080/api/";
    private URL url;
    private String httpMethod;
    private AsyncTaskHandler handler;

    public AsyncWebserviceTask(String method, String route, AsyncTaskHandler handler, Context context) throws MalformedURLException {
        this.httpMethod = method;
        String ip = PreferenceManager.getDefaultSharedPreferences(context).getString("ip","127.0.0.1");
        this.url = new URL(BASE_URL.replace("{{ip}}",ip) + route);
        this.handler = handler;
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        TaskResult result = null;
        String queryString = (params.length > 0) ? params[0] : null;
        String jsonString = (params.length > 1) ? params[1] : null;

        try{
            if(queryString != null)
            {
                url = new URL(url.toString() + "?" + queryString);
            }

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if(jsonString != null && httpMethod == "POST"){
                write(connection, httpMethod, jsonString);
            }

            int statusCode = connection.getResponseCode();
            String content = read(connection);

            result = new TaskResult(statusCode, content);

            connection.disconnect();
        }
        catch(Exception ex)
        {
            result = new TaskResult(ex);
            onCancelled(result);
        }

        return result;
    }

    //region LISTENER
    @Override
    protected void onPreExecute() {
        handler.onPreExecute();
    }

    @Override
    protected void onCancelled(TaskResult result) {
        handler.onError(result.getError());
    }

    @Override
    protected void onCancelled() {
        handler.onError(null);
    }

    @Override
    protected void onPostExecute(TaskResult result) {
        handler.onSuccess(result.getStatusCode(), result.getContent());
    }

    private String read(HttpURLConnection connection) throws IOException {
        InputStream stream = connection.getErrorStream();
        if(stream == null)
        {
            stream = connection.getInputStream();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader((stream)));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        reader.close();
        return sb.toString();
    }

    private void write(HttpURLConnection connection, String httpMethod, String json) throws IOException {
        connection.setRequestMethod(httpMethod);
        connection.setRequestProperty("Content-Type", "application/json");

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        writer.write(json);
        writer.flush();
        writer.close();

        connection.getResponseCode();
    }
}
