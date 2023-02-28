package com.digitalTMC.service.vo.form;

public class MembershipForm {
    private int memberId;
    private int durationId;
    private boolean international;

    public MembershipForm() {
    }

    public int getMemberId() {
        return memberId;
    }

    public int getDurationId() {
        return durationId;
    }

    public boolean getInternational() {
        return international;
    }
}
