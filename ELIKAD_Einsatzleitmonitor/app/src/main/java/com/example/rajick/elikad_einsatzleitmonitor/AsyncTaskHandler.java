package com.example.rajick.elikad_einsatzleitmonitor;

public interface AsyncTaskHandler {
    void onPreExecute();
    void onSuccess(int statusCode, String content);
    void onError(Error err);
}
