package com.digitalTMC.dao;

import com.digitalTMC.dao.po.Profile;

import java.sql.Connection;

public interface ProfileDao extends BaseDao<Profile>{
    Profile searchBySapNumber(String sapNumber, Connection conn);
}
