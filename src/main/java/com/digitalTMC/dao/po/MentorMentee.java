package com.digitalTMC.dao.po;

public class MentorMentee {
    private int menteeId;
    private int mentorId;

    public MentorMentee() {
    }

    public MentorMentee(int menteeId, int mentorId) {
        this.menteeId = menteeId;
        this.mentorId = mentorId;
    }

    public int getMenteeId() {
        return menteeId;
    }

    public void setMenteeId(int menteeId) {
        this.menteeId = menteeId;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }
}
