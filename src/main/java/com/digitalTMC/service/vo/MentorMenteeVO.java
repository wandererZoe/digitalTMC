package com.digitalTMC.service.vo;

public class MentorMenteeVO {
    private int mentorId;
    private int menteeId;
    private String mentorName;
    private String menteeName;

    public MentorMenteeVO() {
    }

    public MentorMenteeVO(int mentorId, int menteeId) {
        this.mentorId = mentorId;
        this.menteeId = menteeId;
    }

    public MentorMenteeVO(int mentorId, int menteeId, String mentorName, String menteeName) {
        this.mentorId = mentorId;
        this.menteeId = menteeId;
        this.mentorName = mentorName;
        this.menteeName = menteeName;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public int getMenteeId() {
        return menteeId;
    }

    public void setMenteeId(int menteeId) {
        this.menteeId = menteeId;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getMenteeName() {
        return menteeName;
    }

    public void setMenteeName(String menteeName) {
        this.menteeName = menteeName;
    }
}
