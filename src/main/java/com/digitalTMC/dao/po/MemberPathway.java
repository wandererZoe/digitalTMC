package com.digitalTMC.dao.po;

import com.digitalTMC.util.DateFormatUtil;
import com.digitalTMC.util.enums.PathwayLevel;

public class MemberPathway {
    private int recordId;
    private int memberId;
    private int pathwayId;
    private int level;
    private String changeOn;

    public MemberPathway() {
    }

    public MemberPathway(int recordId, int memberId, int pathwayId, int level, String changeOn) {
        this.recordId = recordId;
        this.memberId = memberId;
        this.pathwayId = pathwayId;
        this.level = level;
        this.changeOn = changeOn;
    }

    public MemberPathway(int memberId, int pathwayId) {
        DateFormatUtil util = new DateFormatUtil();
        this.memberId = memberId;
        this.pathwayId = pathwayId;
        this.level = PathwayLevel.INITIAL.getValue();
        this.changeOn = util.dateTimeFormatCurrent();
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getPathwayId() {
        return pathwayId;
    }

    public void setPathwayId(int pathwayId) {
        this.pathwayId = pathwayId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getChangeOn() {
        return changeOn;
    }

    public void setChangeOn(String changeOn) {
        this.changeOn = changeOn;
    }

    public void setChangeOn() {
        DateFormatUtil util = new DateFormatUtil();
        this.changeOn = util.dateTimeFormatCurrent();
    }
}