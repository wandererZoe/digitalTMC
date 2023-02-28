package com.digitalTMC.util.enums;

public enum Pathway {
    Dummy(-1),
    DL_Dynamic_Leadership(0),
    EC_Effective_Coaching(1),
    EH_Engaging_Humor(2),
    IP_Innovative_Planning(3),
    LD_Leadership_Development(4),
    MS_Motivational_Strategies(5),
    PI_Persuasive_Influence(6),
    PM_Presentation_Mastery(7),
    SR_Strategic_Relationships(8),
    TC_Team_Collaboration(9),
    VC_Visionary_Communication(10);

    private final int value;
    Pathway(int value){
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public static Pathway codeOf(int code) {
        for (Pathway pathway : values()) {
            if (pathway.getValue() == code) {
                return pathway;
            }
        }
        throw new RuntimeException("No such Pathway ID");
    }
}