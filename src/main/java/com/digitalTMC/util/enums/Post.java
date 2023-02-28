package com.digitalTMC.util.enums;

import com.digitalTMC.util.DynamicEnumUtils;

public enum Post {
    President(DynamicEnumUtils.getOfficerNumber("President")),
    Secretary(DynamicEnumUtils.getOfficerNumber("Secretary")),
    VPE(DynamicEnumUtils.getOfficerNumber("VPE")),
    VPM(DynamicEnumUtils.getOfficerNumber("VPM")),
    VPPR(DynamicEnumUtils.getOfficerNumber("VPPR")),
    SAA(DynamicEnumUtils.getOfficerNumber("SAA")),
    Treasury(DynamicEnumUtils.getOfficerNumber("Treasury")),
    Member(DynamicEnumUtils.getOfficerNumber("Member"));

    private final int value;
    Post(int value){
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public static Post codeOf(int code) {
        for (Post post : values()) {
            if (post.getValue() == code) {
                return post;
            }
        }
        throw new RuntimeException("No such Post");
    }
}