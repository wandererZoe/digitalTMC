package com.digitalTMC.util.enums;

public enum DateStatus {
    Expire(-1),
    Current(0),
    Not_in_effect(1);

    private final int value;
    DateStatus(int value){
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public static DateStatus codeOf(int code) {
        for (DateStatus dateStatus : values()) {
            if (dateStatus.getValue() == code) {
                return dateStatus;
            }
        }
        throw new RuntimeException("No such Status ID");
    }
}