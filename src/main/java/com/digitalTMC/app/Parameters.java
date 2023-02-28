package com.digitalTMC.app;

public class Parameters {
    public static int userId = 2;
    public static String userName = "visitor";
    public static int currentDurationId;

    public static void setUserId(int userId) {
        Parameters.userId = userId;
    }

    public static void setUserName(String userName) {
        Parameters.userName = userName;
    }

    public static void setCurrentDurationId(int currentDurationId) {
        Parameters.currentDurationId = currentDurationId;
    }
}
