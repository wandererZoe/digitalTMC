package com.digitalTMC.service.impl;

import com.digitalTMC.dao.*;
import com.digitalTMC.dao.impl.*;
import com.digitalTMC.dao.po.*;
import com.digitalTMC.dao.basic.SQLConnection;
import com.digitalTMC.service.MemberService;
import com.digitalTMC.service.UserService;
import com.digitalTMC.service.vo.*;
import com.digitalTMC.service.vo.form.MembershipForm;
import com.digitalTMC.service.vo.form.PostForm;
import com.digitalTMC.util.DateFormatUtil;
import com.digitalTMC.util.JackJsonUtil;
import com.digitalTMC.util.enums.Pathway;
import com.digitalTMC.util.enums.PathwayLevel;
import com.digitalTMC.util.enums.Post;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

@Service
public class MemberServiceImpl implements MemberService {

    ProfileDao profileDao;
    MentorMenteeDao mentorMenteeDao;
    MembershipDao membershipDao;
    DurationDao durationDao;
    MemberPathwayDao memberPathwayDao;
    UserService userService;

    protected MemberProfileVO getProfileByMemberId(int memberId) {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        profileDao = new ProfileDaoImpl();
        Profile profile = profileDao.search(memberId,connection);
        sqlConnection.disconnect(connection);
        return new MemberProfileVO(profile.getSapNumber(),
                profile.getName(),profile.getEmail(),profile.getWechat());
    }

    @Override
    public void initialNewAccount(String sapNumber) {
        int newId = initialUser(sapNumber);
        initialMemberProfile(newId,sapNumber);
        initialMentorAssignment(newId);
    }

    private int initialUser(String sapNumber) {
        userService = new UserServiceImpl();
        return  userService.initialUser(sapNumber);
    }

    private void initialMemberProfile(int memberId, String sapNumber) {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        Profile profile = new Profile(memberId,sapNumber);
        profileDao = new ProfileDaoImpl();
        profileDao.insert(memberId,profile,connection);
        sqlConnection.disconnect(connection);
    }

    private void initialMentorAssignment(int memberId){
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        MentorMentee mentorMentee = new MentorMentee();
        mentorMentee.setMenteeId(memberId);
        mentorMenteeDao = new MentorMenteeDaoImpl();
        mentorMenteeDao.insert(memberId,mentorMentee,connection);
        sqlConnection.disconnect(connection);
    }

    private void updateMemberProfile(MemberProfileVO memberProfile) {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        profileDao = new ProfileDaoImpl();
        Profile profile = profileDao.searchBySapNumber(memberProfile.getSapNumber(),connection);
        profile.setName(memberProfile.getName());
        profile.setEmail(memberProfile.getEmailAddress());
        profile.setWechat(memberProfile.getWechatId());
        profileDao.update(profile,connection);
        sqlConnection.disconnect(connection);
    }

    @Override
    public void updateMemberProfile(String memberProfile) {
        updateMemberProfile(new JackJsonUtil<MemberProfileVO>().jsonToObject(memberProfile, MemberProfileVO.class));
    }

    private void newMembership(MembershipForm membershipForm) {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        int international = 0;
        if(membershipForm.getInternational()) international=1;
        Membership membership = new Membership(membershipForm.getMemberId(),membershipForm.getDurationId(),international);
        membershipDao = new MembershipDaoImpl();
        membershipDao.insert(membership,connection);
        sqlConnection.disconnect(connection);
    }

    @Override
    public void newMembership(String membershipForm) {
        newMembership(new JackJsonUtil<MembershipForm>().jsonToObject(membershipForm, MembershipForm.class));
    }

    private void assignPost(PostForm postForm) {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        membershipDao = new MembershipDaoImpl();
        Membership membership = membershipDao.searchByMemberId(postForm.getMemberId(),postForm.getDurationId(),connection);
        membership.setPost( postForm.getPostId());
        membershipDao.update(membership,connection);
        sqlConnection.disconnect(connection);
    }

    @Override
    public void assignPost(String postForm) {
        assignPost(new JackJsonUtil<PostForm>().jsonToObject(postForm,PostForm.class));
    }

    protected ArrayList<MembershipVO> getMembershipHistory(int memberId) {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        membershipDao = new MembershipDaoImpl();
        durationDao = new DurationDaoImpl();
        ArrayList<Membership> memberships = membershipDao.getMembershipHistoryByMemberId(memberId,connection);
        ArrayList<MembershipVO> vos = new ArrayList<>();
        for (Membership membership : memberships) {
            int durationId = membership.getDurationId();
            Duration duration = durationDao.search(durationId, connection);
            DurationVO durationVO = new DurationVO(durationId,duration.getStartDate(), duration.getEndDate());
            boolean isInternational = membership.getInternational() == 1;
            Post post = Post.codeOf(membership.getPost());

            vos.add(new MembershipVO(durationVO, post, isInternational));
        }
        sqlConnection.disconnect(connection);
        return vos;
    }

    protected ArrayList<MemberPathwayVO> getMemberPathwayHistory(int memberId) {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        memberPathwayDao = new MemberPathwayDaoImpl();
        ArrayList<MemberPathway> memberPathways = memberPathwayDao.getMemberPathwayHistoryByMemberId(memberId,connection);
        ArrayList<MemberPathwayVO> vos = new ArrayList<>();
        DateFormatUtil util = new DateFormatUtil();
        Date latest = new Date(0);
        int currentIndex = 0;

        for(int i=0;i<memberPathways.size();i++){
            MemberPathway memberPathway = memberPathways.get(i);
            Pathway pathway = Pathway.codeOf(memberPathway.getPathwayId());
            PathwayLevel level = PathwayLevel.codeOf(memberPathway.getLevel());

            Date changeOn = util.transferString2DateTime(memberPathway.getChangeOn());
            if(changeOn.after(latest)){
                latest = changeOn;
                currentIndex = i;
            }
            vos.add(new MemberPathwayVO(pathway,level,false));
        }
        if(vos.size()==0) return null;
        else {
            vos.get(currentIndex).setCurrent(true);
            sqlConnection.disconnect(connection);
            return vos;
        }
    }

    @Override
    public MemberVO getMemberDetail(int memberId) {
        return new MemberVO(memberId,
                getProfileByMemberId(memberId),
                getMembershipHistory(memberId),
                getMemberPathwayHistory(memberId),
                getMentor(memberId),
                getMentees(memberId)
        );
    }

    protected MentorMenteeVO getMentor(int menteeId) {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        mentorMenteeDao = new MentorMenteeDaoImpl();
        MentorMentee mentorMentee = mentorMenteeDao.search(menteeId,connection);
        int mentorId = mentorMentee.getMentorId();
        if(mentorId != 0) {
            profileDao = new ProfileDaoImpl();
            MentorMenteeVO mentorMenteeVO = new MentorMenteeVO(mentorId, menteeId);
            mentorMenteeVO.setMentorName(profileDao.search(mentorId, connection).getName());
            mentorMenteeVO.setMenteeName(profileDao.search(menteeId, connection).getName());
            sqlConnection.disconnect(connection);
            return mentorMenteeVO;
        }else return null;
    }

    protected ArrayList<MentorMenteeVO> getMentees(int mentorId) {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        mentorMenteeDao = new MentorMenteeDaoImpl();
        profileDao = new ProfileDaoImpl();
        ArrayList<MentorMentee> mentorMentees = mentorMenteeDao.getAllMentees(mentorId,connection);
        ArrayList<MentorMenteeVO> mentorMenteeVOs = new ArrayList<>();
        for (MentorMentee mentorMentee : mentorMentees) {
            int menteeId = mentorMentee.getMenteeId();
            MentorMenteeVO mentorMenteeVO = new MentorMenteeVO(mentorId, menteeId);
            mentorMenteeVO.setMentorName(profileDao.search(mentorId, connection).getName());
            mentorMenteeVO.setMenteeName(profileDao.search(menteeId, connection).getName());
            mentorMenteeVOs.add(mentorMenteeVO);
        }
        sqlConnection.disconnect(connection);
        return mentorMenteeVOs;
    }
}
