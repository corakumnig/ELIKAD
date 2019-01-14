package com.example.cora.elikad_alarmierungsapp.Data;

public interface AsyncTaskHandler {
    void onPreExecute();
    void onSuccess(int statusCode, String content);
    void onError(Error err);
}
