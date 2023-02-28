package com.digitalTMC.util.enums;

public enum UserStatus {
    Inactivated(-1),
    ActivatedAndLocked(1),
    Unlocked(0),
    UsernameChangeRequested(8),
    UsernameChangeCompleted(9),
    RequestExpire(-2);

    private final int value;
    UserStatus(int value){
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public static UserStatus codeOf(int code) {
        for (UserStatus userStatus : values()) {
            if (userStatus.getValue() == code) {
                return userStatus;
            }
        }
        throw new RuntimeException("No such UserStatus ID");
    }
}