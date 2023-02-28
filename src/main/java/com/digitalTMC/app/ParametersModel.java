package com.digitalTMC.app;

public class ParametersModel {
    public int userId;
    public String userName;
    public int currentDurationId;

    public ParametersModel() {
    }

    public ParametersModel(int userId, String userName, int currentDurationId) {
        this.userId = userId;
        this.userName = userName;
        this.currentDurationId = currentDurationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCurrentDurationId() {
        return currentDurationId;
    }

    public void setCurrentDurationId(int currentDurationId) {
        this.currentDurationId = currentDurationId;
    }
}
