package com.digitalTMC.dao.impl;

import com.digitalTMC.dao.MentorMenteeDao;
import com.digitalTMC.dao.po.MentorMentee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MentorMenteeDaoImpl extends BaseDaoImpl<MentorMentee> implements MentorMenteeDao {
    private final Class<MentorMentee> gClass;
    public MentorMenteeDaoImpl(){
        this.gClass = MentorMentee.class;
    }

    @Override
    public MentorMentee search(int id, Connection conn) {
        MentorMentee mentorMentee = new MentorMentee();
        try {
            ResultSet resultSet = super.search(gClass,id,conn);
            if(resultSet.next()) {
                mentorMentee.setMenteeId(resultSet.getInt("menteeId"));
                mentorMentee.setMentorId(resultSet.getInt("mentorId"));
            }else return null;
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return mentorMentee;
    }

    @Override
    public ArrayList<MentorMentee> getAll(Connection conn) {
        ArrayList<MentorMentee> durations = new ArrayList<>();
        try {
            ResultSet resultSet = super.getAll(gClass,conn);
            while(resultSet.next()) {
                durations.add(new MentorMentee(
                        resultSet.getInt("menteeId"),
                        resultSet.getInt("mentorId")));
            }
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return durations;
    }

    @Override
    public int count(Connection conn) {
        return super.count(gClass,conn);
    }

    @Override
    public ArrayList<MentorMentee> getAllMentees(int memberId, Connection conn) {
        ArrayList<MentorMentee> mentorMentees = new ArrayList<>();
        String where = " WHERE (`mentorId` = '" + memberId + "')";
        try {
            ResultSet resultSet = super.customGetAll(gClass,where,conn);
            while(resultSet.next()) {
                mentorMentees.add(new MentorMentee(
                        resultSet.getInt("menteeId"),
                        resultSet.getInt("mentorId")));
            }
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return mentorMentees;
    }
}
