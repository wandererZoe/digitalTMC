package com.digitalTMC.util.enums;

public enum Authority {
    READ_ONLY(0),
    READ_AND_WRITE(1),
    RESTRICTIVE(-1);

    private final int value;
    Authority(int value){
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public static Authority codeOf(int code) {
        for (Authority viewMode : values()) {
            if (viewMode.getValue() == code) {
                return viewMode;
            }
        }
        throw new RuntimeException("No such Authority ID");
    }
}