package com.digitalTMC.dao.impl;

import com.digitalTMC.dao.po.MemberPathway;
import com.digitalTMC.dao.MemberPathwayDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberPathwayDaoImpl extends BaseDaoImpl<MemberPathway> implements MemberPathwayDao {
    private final Class<MemberPathway> gClass;
    public MemberPathwayDaoImpl(){
        this.gClass = MemberPathway.class;
    }

    @Override
    public MemberPathway searchSpecific(int memberId, int pathwayId, Connection conn) {
        MemberPathway memberPathway = new MemberPathway();
        String where = " WHERE (`memberId` = '" + memberId + "' AND `pathwayId` = '" + pathwayId + "')";
        try {
            ResultSet resultSet = super.customSearch(gClass,where,conn);
            if(resultSet.next()) {
                memberPathway.setRecordId(resultSet.getInt("recordId"));
                memberPathway.setMemberId(resultSet.getInt("memberId"));
                memberPathway.setPathwayId(resultSet.getInt("pathwayId"));
                memberPathway.setLevel(resultSet.getInt("level"));
                memberPathway.setChangeOn(resultSet.getString("changeOn"));
            }else return null;
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return memberPathway;
    }

    @Override
    public MemberPathway search(int id, Connection conn) {
        MemberPathway memberPathway = new MemberPathway();
        try {
            ResultSet resultSet = super.search(gClass,id,conn);
            if(resultSet.next()) {
                memberPathway.setRecordId(resultSet.getInt("recordId"));
                memberPathway.setMemberId(resultSet.getInt("memberId"));
                memberPathway.setPathwayId(resultSet.getInt("pathwayId"));
                memberPathway.setLevel(resultSet.getInt("level"));
                memberPathway.setChangeOn(resultSet.getString("changeOn"));
            }else return null;
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return memberPathway;
    }

    @Override
    public ArrayList<MemberPathway> getAll(Connection conn) {
        ArrayList<MemberPathway> memberPathways = new ArrayList<>();
        try {
            ResultSet resultSet = super.getAll(gClass,conn);
            while(resultSet.next()) {
                memberPathways.add(new MemberPathway(
                        resultSet.getInt("recordId"),
                        resultSet.getInt("memberId"),
                        resultSet.getInt("pathwayId"),
                        resultSet.getInt("level"),
                        resultSet.getString("changeOn")));
            }
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return memberPathways;
    }

    @Override
    public int count(Connection conn) {
        return super.count(gClass,conn);
    }

    @Override
    public ArrayList<MemberPathway> getMemberPathwayHistoryByMemberId(int memberId, Connection conn) {
        ArrayList<MemberPathway> memberPathways = new ArrayList<>();
        String where = " WHERE (`memberId` = '" + memberId + "')";
        try {
            ResultSet resultSet = super.customGetAll(gClass,where,conn);
            while(resultSet.next()) {
                memberPathways.add(new MemberPathway(
                        resultSet.getInt("recordId"),
                        resultSet.getInt("memberId"),
                        resultSet.getInt("pathwayId"),
                        resultSet.getInt("level"),
                        resultSet.getString("changeOn")));
            }
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return memberPathways;
    }
}
