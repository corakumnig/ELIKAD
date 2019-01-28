package com.example.cora.elikad_alarmierungsapp.Data;

import android.content.Context;
import android.content.SharedPreferences;
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

    private static final String BASE_URL = "http://{{ip}}/api/";
    private URL url;
    private String httpMethod;
    private AsyncTaskHandler handler;
    private SharedPreferences preferences;

    private static String accessToken;

    public String getUrl(){
        return url.toString();
    }

    public String getHttpMethod(){
        return httpMethod;
    }

    public AsyncWebserviceTask(String method, String route, AsyncTaskHandler handler, Context context) throws MalformedURLException {
        this.httpMethod = method;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String ip = preferences.getString("ip","elikadweb.herokuapp.com");
        //String ip = preferences.getString("ip","192.168.43.142:8080");
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

            if (accessToken != null) {
                connection.setRequestProperty("Token", accessToken);
            }

            if(jsonString != null && httpMethod == "POST"){
                write(connection, httpMethod, jsonString);
            }

            if (jsonString == null && httpMethod == "DELETE") {
                write(connection, httpMethod, "");
            }

            if(jsonString !=null && httpMethod == "PUT"){
                write(connection, httpMethod, jsonString);
            }

            int statusCode = connection.getResponseCode();
            String content = read(connection);

            if (accessToken == null) {
                accessToken = connection.getHeaderField("Token");
            }

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

        preferences.edit().putString("Token", connection.getHeaderField("Token"));
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

    public static String getAccesToken(){
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        AsyncWebserviceTask.accessToken = accessToken;
    }
}
