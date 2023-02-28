package com.digitalTMC.util.enums;

public enum SystemMessage {
    //duration
    NO_CURRENT_DURATION(10000,"ERROR","No current duration"),
    OVERLAP_DURATION(10001,"ERROR","Duration overlap"),

    //user
    UNKNOWN_USER(20001,"ERROR","Unknown User"),
    INACTIVATED_USER(20002,"WARNING","Inactivated User"),
    WRONG_USERNAME_OR_PWD(20003,"ERROR","Wrong user name or password"),
    ALREADY_REQUESTED(20004,"WARNING","You have sent an email. Apply again?"),
    ALREADY_UNLOCK(20005,"INFORMATION","Your user is unlocked"),
;
    private final int messageCode;
    private final String type;
    private final String message;

    SystemMessage(int messageCode, String type, String message) {
        this.messageCode = messageCode;
        this.type = type;
        this.message = message;
    }

    public int getMessageCode() {
        return messageCode;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
