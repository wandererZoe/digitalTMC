package com.digitalTMC.service.vo;

import com.digitalTMC.util.enums.Post;

public class MembershipVO {
    private DurationVO duration;
    private Post post;
    private boolean isInternational;

    public MembershipVO() {
    }

    public MembershipVO(DurationVO duration, boolean isInternational) {
        this.duration = duration;
        this.isInternational = isInternational;
        this.post = Post.Member;
    }

    public DurationVO getDuration() {
        return duration;
    }

    public MembershipVO(DurationVO duration, Post post, boolean isInternational) {
        this.duration = duration;
        this.post = post;
        this.isInternational = isInternational;
    }

    public void setDuration(DurationVO duration) {
        this.duration = duration;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public boolean isInternational() {
        return isInternational;
    }

    public void setInternational(boolean international) {
        isInternational = international;
    }
}
