package com.digitalTMC.service.vo;

import java.util.ArrayList;

public class MemberVO {
    private int memberId;
    private MemberProfileVO memberProfileVO;
    private ArrayList<MembershipVO> memberships;
    private ArrayList<MemberPathwayVO> pathways;

    private MentorMenteeVO mentor;
    private ArrayList<MentorMenteeVO> mentees;

    public MemberVO() {
    }

    public MemberVO(int memberId, MemberProfileVO memberProfileVO, ArrayList<MembershipVO> memberships, ArrayList<MemberPathwayVO> pathways, MentorMenteeVO mentor, ArrayList<MentorMenteeVO> mentees) {
        this.memberId = memberId;
        this.memberProfileVO = memberProfileVO;
        this.memberships = memberships;
        this.pathways = pathways;
        this.mentor = mentor;
        this.mentees = mentees;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public MemberProfileVO getMemberProfileVO() {
        return memberProfileVO;
    }

    public void setMemberProfileVO(MemberProfileVO memberProfileVO) {
        this.memberProfileVO = memberProfileVO;
    }

    public ArrayList<MembershipVO> getMemberships() {
        return memberships;
    }

    public void setMemberships(ArrayList<MembershipVO> memberships) {
        this.memberships = memberships;
    }

    public ArrayList<MemberPathwayVO> getPathways() {
        return pathways;
    }

    public void setPathways(ArrayList<MemberPathwayVO> pathways) {
        this.pathways = pathways;
    }

    public MentorMenteeVO getMentor() {
        return mentor;
    }

    public void setMentor(MentorMenteeVO mentor) {
        this.mentor = mentor;
    }

    public ArrayList<MentorMenteeVO> getMentees() {
        return mentees;
    }

    public void setMentees(ArrayList<MentorMenteeVO> mentees) {
        this.mentees = mentees;
    }
}
