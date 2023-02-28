package com.digitalTMC.dao.impl;

import com.digitalTMC.dao.ProfileDao;
import com.digitalTMC.dao.po.Profile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class ProfileDaoImpl extends BaseDaoImpl<Profile> implements ProfileDao {
    private final Class<Profile> gClass;
    public ProfileDaoImpl(){
        this.gClass = Profile.class;
    }

    @Override
    public Profile search(int id, Connection conn) {
        Profile profile = new Profile();
        try {
            ResultSet resultSet = super.search(gClass,id,conn);
            if(resultSet.next()) {
                profile.setMemberId(resultSet.getInt("memberId"));
                profile.setSapNumber(resultSet.getString("sapNumber"));
                profile.setName(resultSet.getString("name"));
                profile.setEmail(resultSet.getString("email"));
                profile.setWechat(resultSet.getString("wechat"));
            }else return null;
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return profile;
    }

    @Override
    public Profile searchBySapNumber(String sapNumber, Connection conn) {
        Profile profile = new Profile();
        String where = " WHERE (`sapNumber` = '" + sapNumber + "')";
        try {
            ResultSet resultSet = super.customSearch(gClass,where,conn);
            if(resultSet.next()) {
                profile.setMemberId(resultSet.getInt("memberId"));
                profile.setSapNumber(resultSet.getString("sapNumber"));
                profile.setName(resultSet.getString("name"));
                profile.setEmail(resultSet.getString("email"));
                profile.setWechat(resultSet.getString("wechat"));
            }else return null;
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return profile;
    }

    @Override
    public ArrayList<Profile> getAll(Connection conn) {
        return null;
    }

    @Override
    public int count(Connection conn) {
        return super.count(gClass,conn);
    }
}
