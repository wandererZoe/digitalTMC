package com.digitalTMC.dao;

import com.digitalTMC.dao.po.Membership;

import java.sql.Connection;
import java.util.ArrayList;

public interface MembershipDao extends BaseDao<Membership>{
    Membership searchByMemberId (int memberId, int durationId, Connection conn);
    ArrayList<Membership> getMembershipHistoryByMemberId(int memberId, Connection conn);
    ArrayList<Membership> getMembershipByDuration(int durationId, Connection conn);
    ArrayList<Membership> getLatestMembershipEachMember (Connection conn);
}
