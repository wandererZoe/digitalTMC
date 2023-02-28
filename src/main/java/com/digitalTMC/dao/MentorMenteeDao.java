package com.digitalTMC.dao;

import com.digitalTMC.dao.po.MentorMentee;

import java.sql.Connection;
import java.util.ArrayList;

public interface MentorMenteeDao extends BaseDao<MentorMentee>{
    ArrayList<MentorMentee> getAllMentees(int memberId, Connection conn);
}
