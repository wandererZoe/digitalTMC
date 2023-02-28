package com.digitalTMC.dao;

import com.digitalTMC.dao.po.MemberPathway;

import java.sql.Connection;
import java.util.ArrayList;

public interface MemberPathwayDao extends BaseDao<MemberPathway>{
    MemberPathway searchSpecific (int memberId, int pathwayId, Connection conn);
    ArrayList<MemberPathway> getMemberPathwayHistoryByMemberId(int memberId, Connection conn);
}
