package com.digitalTMC.dao;

import com.digitalTMC.dao.po.User;

import java.sql.Connection;
import java.util.ArrayList;

public interface UserDao extends BaseDao<User>{
    User searchBySapNumber(String sapNumber, Connection conn);
    ArrayList<User> getInActivated(Connection conn);
}
