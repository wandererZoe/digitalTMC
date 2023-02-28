package com.digitalTMC.util.enums;

public enum ViewMode {
    Activated_ALL(0),
    Inactivated_All(-1),
    Current(1),
    Officers(2),
    Expire(3);

    private final int value;
    ViewMode(int value){
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public static ViewMode codeOf(int code) {
        for (ViewMode viewMode : values()) {
            if (viewMode.getValue() == code) {
                return viewMode;
            }
        }
        throw new RuntimeException("No such View Mode ID");
    }
}