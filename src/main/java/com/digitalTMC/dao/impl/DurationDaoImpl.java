package com.digitalTMC.dao.impl;

import com.digitalTMC.dao.DurationDao;
import com.digitalTMC.dao.po.Duration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DurationDaoImpl extends BaseDaoImpl<Duration> implements DurationDao {
    private final Class<Duration> gClass;
    public DurationDaoImpl(){
        this.gClass = Duration.class;
    }

    @Override
    public Duration search(int id, Connection conn) {
        Duration duration = new Duration();
        try {
            ResultSet resultSet = super.search(gClass,id,conn);
            if(resultSet.next()) {
                duration.setDurationId(resultSet.getInt("durationId"));
                duration.setStartDate(resultSet.getDate("startDate"));
                duration.setEndDate(resultSet.getDate("endDate"));
            }else return null;
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return duration;
    }

    @Override
    public ArrayList<Duration> getAll(Connection conn) {
        ArrayList<Duration> durations = new ArrayList<>();
        try {
            ResultSet resultSet = super.getAll(gClass,conn);
            while(resultSet.next()) {
                durations.add(new Duration(
                        resultSet.getInt("durationId"),
                        resultSet.getDate("startDate"),
                        resultSet.getDate("endDate")));
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
}
