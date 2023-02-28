package com.digitalTMC.dao.impl;

import com.digitalTMC.dao.MembershipDao;
import com.digitalTMC.dao.po.Membership;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MembershipDaoImpl extends BaseDaoImpl<Membership> implements MembershipDao {
    private final Class<Membership> gClass;
    public MembershipDaoImpl(){
        this.gClass = Membership.class;
    }

    @Override
    public Membership search(int id, Connection conn) {
        Membership membership = new Membership();
        try {
            ResultSet resultSet = super.search(gClass,id,conn);
            if(resultSet.next()) {
                membership.setRecordId(resultSet.getInt("recordId"));
                membership.setMemberId(resultSet.getInt("memberId"));
                membership.setDurationId(resultSet.getInt("durationId"));
                membership.setPost(resultSet.getInt("post"));
                membership.setInternational(resultSet.getInt("international"));
            }else return null;
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return membership;
    }

    @Override
    public ArrayList<Membership> getAll(Connection conn) {
        ArrayList<Membership> memberships = new ArrayList<>();
        try {
            ResultSet resultSet = super.getAll(gClass,conn);
            while(resultSet.next()) {
                memberships.add(new Membership(
                        resultSet.getInt("recordId"),
                        resultSet.getInt("memberId"),
                        resultSet.getInt("durationId"),
                        resultSet.getInt("post"),
                        resultSet.getInt("international")));
            }
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return memberships;
    }

    @Override
    public int count(Connection conn) {
        return super.count(gClass,conn);
    }

    @Override
    public Membership searchByMemberId(int memberId, int durationId, Connection conn) {
        Membership membership = new Membership();
        String where = " WHERE (`memberId` = '" + memberId + "' AND `durationId` = '"+durationId+"')";
        try {
            ResultSet resultSet = super.customSearch(gClass,where,conn);
            if(resultSet.next()) {
                membership.setRecordId(resultSet.getInt("recordId"));
                membership.setMemberId(resultSet.getInt("memberId"));
                membership.setDurationId(resultSet.getInt("durationId"));
                membership.setPost(resultSet.getInt("post"));
                membership.setInternational(resultSet.getInt("international"));
            }else return null;
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return membership;
    }

    @Override
    public ArrayList<Membership> getMembershipHistoryByMemberId(int memberId, Connection conn) {
        ArrayList<Membership> memberships = new ArrayList<>();
        String where = " WHERE (`memberId` = '" + memberId + "')";
        try {
            ResultSet resultSet = super.customGetAll(gClass,where,conn);
            while(resultSet.next()) {
                memberships.add(new Membership(
                        resultSet.getInt("recordId"),
                        resultSet.getInt("memberId"),
                        resultSet.getInt("durationId"),
                        resultSet.getInt("post"),
                        resultSet.getInt("international")));
            }
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return memberships;
    }

    @Override
    public ArrayList<Membership> getMembershipByDuration(int durationId, Connection conn) {
        ArrayList<Membership> memberships = new ArrayList<>();
        String where = " WHERE (`durationId` = '" + durationId + "')";
        try {
            ResultSet resultSet = super.customGetAll(gClass,where,conn);
            while(resultSet.next()) {
                memberships.add(new Membership(
                        resultSet.getInt("recordId"),
                        resultSet.getInt("memberId"),
                        resultSet.getInt("durationId"),
                        resultSet.getInt("post"),
                        resultSet.getInt("international")));
            }
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return memberships;
    }

    @Override
    public ArrayList<Membership> getLatestMembershipEachMember(Connection conn) {
        ArrayList<Membership> all = getAll(conn);
        ArrayList<Membership> distinct = new ArrayList<>();
        if(all.size()==0) return null;
        distinct.add(all.get(0));
        for (Membership membership : all) {
            for (int j = 0; j < distinct.size(); j++) {
                Membership temp = distinct.get(j);
                if (membership.getMemberId() == temp.getMemberId()) {
                    if (membership.getDurationId() > temp.getDurationId()) {
                        distinct.remove(temp);
                        distinct.add(membership);
                    }
                    break;
                }
                distinct.add(membership);
            }
        }
        return distinct;
    }
}
