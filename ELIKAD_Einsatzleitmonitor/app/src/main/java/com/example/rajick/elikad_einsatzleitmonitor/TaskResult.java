package com.example.rajick.elikad_einsatzleitmonitor;

public class TaskResult {
    private int statusCode;
    private String content;
    private Error error;

    public Error getError() {
        return error;
    }

    public TaskResult(int statusCode, String content) {
        super();
        this.statusCode = statusCode;
        this.content = content;
    }

    public TaskResult(Exception ex) {
        super();
        this.error = new Error(ex);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getContent() {
        return content;
    }
}
