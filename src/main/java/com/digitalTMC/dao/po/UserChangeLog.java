package com.digitalTMC.dao.po;

public class UserChangeLog {

    private int recordId;
    private String requestBy;
    private String requestOn;
    private int status;
    private String processBy;
    private String processOn;

    public UserChangeLog() {
    }

    public UserChangeLog(int recordId, String requestBy, String requestOn, int status, String processBy, String processOn) {
        this.recordId = recordId;
        this.requestBy = requestBy;
        this.requestOn = requestOn;
        this.status = status;
        this.processBy = processBy;
        this.processOn = processOn;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    public String getRequestOn() {
        return requestOn;
    }

    public void setRequestOn(String requestOn) {
        this.requestOn = requestOn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProcessBy() {
        return processBy;
    }

    public void setProcessBy(String processBy) {
        this.processBy = processBy;
    }

    public String getProcessOn() {
        return processOn;
    }

    public void setProcessOn(String processOn) {
        this.processOn = processOn;
    }
}
