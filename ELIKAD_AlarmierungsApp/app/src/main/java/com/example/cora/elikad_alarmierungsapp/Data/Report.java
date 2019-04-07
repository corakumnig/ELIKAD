package com.example.cora.elikad_alarmierungsapp.Data;

public class Report {
    private int operationId;
    private String operationTime;
    private String operationReason;
    private String operationObject;
    private int operationInjured;
    private int operationKilled;
    private String operationOther;
    private int departmentId;

    public Report(int operationId, String operationTime, String operationReason, String operationObject, int operationInjured, int operationKilled, String operationOther, int departmentId) {
        this.operationId = operationId;
        this.operationTime = operationTime;
        this.operationReason = operationReason;
        this.operationObject = operationObject;
        this.operationInjured = operationInjured;
        this.operationKilled = operationKilled;
        this.operationOther = operationOther;
        this.departmentId = departmentId;
    }

    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperationReason() {
        return operationReason;
    }

    public void setOperationReason(String operationReason) {
        this.operationReason = operationReason;
    }

    public String getOperationObject() {
        return operationObject;
    }

    public void setOperationObject(String operationObject) {
        this.operationObject = operationObject;
    }

    public int getOperationInjured() {
        return operationInjured;
    }

    public void setOperationInjured(int operationInjured) {
        this.operationInjured = operationInjured;
    }

    public int getOperationKilled() {
        return operationKilled;
    }

    public void setOperationKilled(int operationKilled) {
        this.operationKilled = operationKilled;
    }

    public String getOperationOther() {
        return operationOther;
    }

    public void setOperationOther(String operationOther) {
        this.operationOther = operationOther;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Report{" +
                "operationId=" + operationId +
                ", operationTime='" + operationTime + '\'' +
                ", operationReason='" + operationReason + '\'' +
                ", operationObject='" + operationObject + '\'' +
                ", operationInjured=" + operationInjured +
                ", operationKilled=" + operationKilled +
                ", operationOther='" + operationOther + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }
}
