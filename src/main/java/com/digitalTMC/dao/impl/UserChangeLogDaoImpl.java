package com.digitalTMC.dao.impl;

import com.digitalTMC.dao.UserChangeLogDao;
import com.digitalTMC.dao.po.UserChangeLog;
import com.digitalTMC.util.enums.UserStatus;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class UserChangeLogDaoImpl extends BaseDaoImpl<UserChangeLog> implements UserChangeLogDao {
    private final Class<UserChangeLog> gClass;
    public UserChangeLogDaoImpl(){
        this.gClass = UserChangeLog.class;
    }

    @Override
    public UserChangeLog search(int id, Connection conn) {
        UserChangeLog changeLog = new UserChangeLog();
        try {
            ResultSet resultSet = super.search(gClass,id,conn);
            if(resultSet.next()) {
                changeLog.setRecordId(resultSet.getInt("recordId"));
                changeLog.setRequestBy(resultSet.getString("requestBy"));
                changeLog.setRequestOn(resultSet.getString("requestOn"));
                changeLog.setStatus(resultSet.getInt("status"));
                changeLog.setProcessBy(resultSet.getString("processBy"));
                changeLog.setProcessOn(resultSet.getString("processOn"));
            }else return null;
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return changeLog;
    }

    @Override
    public ArrayList<UserChangeLog> getAll(Connection conn) {
        ArrayList<UserChangeLog> changeLogs = new ArrayList<>();
        try {
            ResultSet resultSet = super.getAll(gClass,conn);
            while(resultSet.next()) {
                changeLogs.add(new UserChangeLog(
                        resultSet.getInt("recordId"),
                        resultSet.getString("requestBy"),
                        resultSet.getString("requestOn"),
                        resultSet.getInt("status"),
                        resultSet.getString("processBy"),
                        resultSet.getString("processOn")));
            }
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return changeLogs;
    }

    @Override
    public int count(Connection conn) {
        return super.count(gClass,conn);
    }

    @Override
    public UserChangeLog searchOpenRequestBySapNumber(String sapNumber, Connection conn) {
        return searchRequestBySapNumber(UserStatus.UsernameChangeRequested.getValue(),sapNumber,conn);
    }

    @Override
    public UserChangeLog searchProcessedRequestBySapNumber(String sapNumber, Connection conn) {
        return searchRequestBySapNumber(UserStatus.Unlocked.getValue(),sapNumber,conn);
    }

    private UserChangeLog searchRequestBySapNumber(int requestType, String sapNumber, Connection conn) {
        UserChangeLog changeLog = new UserChangeLog();
        String where = " WHERE (`requestBy` = '" + sapNumber + "' AND `status` = '" + requestType +"' )";
        try {
            ResultSet resultSet = super.customSearch(gClass,where,conn);
            if(resultSet.next()) {
                changeLog.setRecordId(resultSet.getInt("recordId"));
                changeLog.setRequestBy(resultSet.getString("requestBy"));
                changeLog.setRequestOn(resultSet.getString("requestOn"));
                changeLog.setStatus(resultSet.getInt("status"));
                changeLog.setProcessBy(resultSet.getString("processBy"));
                changeLog.setProcessOn(resultSet.getString("processOn"));
            }else return null;
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return changeLog;
    }
}