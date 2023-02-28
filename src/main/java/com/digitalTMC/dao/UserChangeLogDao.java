package com.digitalTMC.dao;

import com.digitalTMC.dao.po.UserChangeLog;

import java.sql.Connection;

public interface UserChangeLogDao extends BaseDao<UserChangeLog>{
    UserChangeLog searchOpenRequestBySapNumber(String sapNumber, Connection conn);
    UserChangeLog searchProcessedRequestBySapNumber(String sapNumber, Connection conn);
}
