package com.digitalTMC.service.impl;

import com.digitalTMC.app.Parameters;
import com.digitalTMC.dao.MembershipDao;
import com.digitalTMC.dao.impl.MembershipDaoImpl;
import com.digitalTMC.dao.po.Membership;
import com.digitalTMC.dao.basic.SQLConnection;
import com.digitalTMC.service.MemberService;
import com.digitalTMC.service.ViewPageService;
import com.digitalTMC.service.vo.MemberVO;
import com.digitalTMC.util.JackJsonUtil;
import com.digitalTMC.util.enums.Post;
import com.digitalTMC.util.enums.ViewMode;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.ArrayList;

@Service
public class ViewPageServiceImpl implements ViewPageService {

    MembershipDao membershipDao;
    MemberService memberService;

    private ArrayList<MemberVO> currentMembers;
    private ArrayList<MemberVO> currentOfficers;
    private ArrayList<MemberVO> others;
    private ArrayList<MemberVO> all;

    @Override
    public void init() {
        currentMembers = new ArrayList<>();
        currentOfficers = new ArrayList<>();
        others = new ArrayList<>();
        all = new ArrayList<>();

        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        membershipDao = new MembershipDaoImpl();
        memberService = new MemberServiceImpl();
        ArrayList<Membership> memberships = membershipDao.getLatestMembershipEachMember(connection);

        if(memberships==null) return;

        for (Membership membership : memberships) {
            if (membership.getDurationId() == Parameters.currentDurationId) {
                if (Post.codeOf(membership.getPost()) == Post.Member) {
                    currentMembers.add(memberService.getMemberDetail(membership.getMemberId()));
                } else {
                    currentOfficers.add(memberService.getMemberDetail(membership.getMemberId()));
                }
            } else {
                others.add(memberService.getMemberDetail(membership.getMemberId()));
            }
        }
        all.addAll(currentMembers);
        all.addAll(currentOfficers);
        all.addAll(others);
    }

    private ArrayList<MemberVO> getMembers(ViewMode criteria) {
        switch (criteria){
            case Current: return getCurrentMembers();
            case Officers: return getCurrentOfficers();
            case Expire: return getOthers();
        }
        return getAll();
    }

    @Override
    public String getMembers(String criteria) {
        return new JackJsonUtil<>().objectToJson(getMembers(ViewMode.codeOf(Integer.parseInt(criteria))));
    }

    @Override
    public ArrayList<MemberVO> getAllMembers() {
        return getMembers(ViewMode.Activated_ALL);
    }

    private ArrayList<MemberVO> getCurrentMembers() {
        return currentMembers;
    }

    private ArrayList<MemberVO> getCurrentOfficers() {
        return currentOfficers;
    }

    private ArrayList<MemberVO> getOthers() {
        return others;
    }

    private ArrayList<MemberVO> getAll() {
        return all;
    }
}
