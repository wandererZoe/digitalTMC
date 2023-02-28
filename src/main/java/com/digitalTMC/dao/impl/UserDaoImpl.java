package com.digitalTMC.dao.impl;

import com.digitalTMC.dao.po.User;
import com.digitalTMC.util.enums.UserStatus;
import com.digitalTMC.dao.UserDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    private final Class<User> gClass;
    public UserDaoImpl(){
        this.gClass = User.class;
    }

    @Override
    public User searchBySapNumber(String sapNumber, Connection conn) {
        User user = new User();
        String where = " WHERE (`sapNumber` = '" + sapNumber + "')";
        try {
            ResultSet resultSet = super.customSearch(gClass,where,conn);
            if(resultSet.next()) {
                user.setMemberId(resultSet.getInt("memberId"));
                user.setSapNumber(resultSet.getString("sapNumber"));
                user.setActivated(resultSet.getInt("activated"));
                user.setCreateOn(resultSet.getString("createOn"));
                user.setActiveOn(resultSet.getString("activeOn"));
                user.setUsername(resultSet.getString("username"));
                user.setLockUsername(resultSet.getInt("lockUsername"));
            }else return null;
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return user;
    }
    @Override
    public User search(int id, Connection conn) {
        User user = new User();
        try {
            ResultSet resultSet = super.search(gClass,id,conn);
            if(resultSet.next()) {
                user.setMemberId(resultSet.getInt("memberId"));
                user.setSapNumber(resultSet.getString("sapNumber"));
                user.setActivated(resultSet.getInt("activated"));
                user.setCreateOn(resultSet.getString("createOn"));
                user.setActiveOn(resultSet.getString("activeOn"));
                user.setUsername(resultSet.getString("username"));
                user.setLockUsername(resultSet.getInt("lockUsername"));
            }else return null;
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return user;
    }

    @Override
    public ArrayList<User> getAll(Connection conn) {
        ArrayList<User> users = new ArrayList<>();
        try {
            ResultSet resultSet = super.getAll(gClass,conn);
            while(resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("memberId"),
                        resultSet.getString("sapNumber"),
                        resultSet.getInt("activated"),
                        resultSet.getString("createOn"),
                        resultSet.getString("activeOn"),
                        resultSet.getString("username"),
                        resultSet.getInt("lockUsername")));
            }
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return users;
    }

    @Override
    public int count(Connection conn) {
        return super.count(gClass,conn);
    }

    @Override
    public ArrayList<User> getInActivated(Connection conn) {
        ArrayList<User> users = new ArrayList<>();
        String where = " WHERE (`activated` = '"+ UserStatus.Inactivated.getValue() +"')";
        try {
            ResultSet resultSet = super.customGetAll(gClass,where,conn);
            while(resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("memberId"),
                        resultSet.getString("sapNumber"),
                        resultSet.getInt("activated"),
                        resultSet.getString("createOn"),
                        resultSet.getString("activeOn"),
                        resultSet.getString("username"),
                        resultSet.getInt("lockUsername")));
            }
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return users;
    }
}
