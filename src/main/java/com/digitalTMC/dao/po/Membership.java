package com.digitalTMC.dao.po;

import com.digitalTMC.util.enums.Post;

public class Membership {
    private int recordId;
    private int memberId;
    private int durationId;
    private int post;
    private int international;

    public Membership(int recordId, int memberId, int durationId, int post, int international) {
        this.recordId = recordId;
        this.memberId = memberId;
        this.durationId = durationId;
        this.post = post;
        this.international = international;
    }

    public Membership(int memberId, int durationId, int international) {
        this.memberId = memberId;
        this.durationId = durationId;
        this.post = Post.Member.getValue();
        this.international = international;
    }

    public Membership() {
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

    public int getDurationId() {
        return durationId;
    }

    public void setDurationId(int durationId) {
        this.durationId = durationId;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public int getInternational() {
        return international;
    }

    public void setInternational(int international) {
        this.international = international;
    }
}
