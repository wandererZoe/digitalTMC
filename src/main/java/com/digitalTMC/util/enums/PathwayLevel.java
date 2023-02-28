package com.digitalTMC.util.enums;

public enum PathwayLevel {
    INITIAL(0),
    LEVEL_1_1(11),
    LEVEL_1_2_1(121),
    LEVEL_1_2_2(122),
    LEVEL_3(3),
    LEVEL_4(4),
    LEVEL_5(5),
    COMPLETED(-1);

    private final int value;
    PathwayLevel(int value){
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public static PathwayLevel codeOf(int code) {
        for (PathwayLevel level : values()) {
            if (level.getValue() == code) {
                return level;
            }
        }
        throw new RuntimeException("No such Pathway Level");
    }
}