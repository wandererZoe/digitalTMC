package com.digitalTMC.dao.impl;

import com.digitalTMC.dao.OfficerCatalogDao;
import com.digitalTMC.dao.po.OfficerCatalog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OfficerCatalogDaoImpl extends BaseDaoImpl<OfficerCatalog> implements OfficerCatalogDao {
    private final Class<OfficerCatalog> gClass;
    public OfficerCatalogDaoImpl(){
        this.gClass = OfficerCatalog.class;
    }

    @Override
    public OfficerCatalog search(int id, Connection conn) {
        OfficerCatalog officerCatalog = new OfficerCatalog();
        try {
            ResultSet resultSet = super.search(gClass,id,conn);
            if(resultSet.next()) {
                officerCatalog.setRecordId(resultSet.getInt("recordId"));
                officerCatalog.setOfficerId(resultSet.getInt("officerId"));
                officerCatalog.setCatalogId(resultSet.getInt("catalogId"));
                officerCatalog.setAuthority(resultSet.getInt("authority"));
            }else return null;
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return officerCatalog;
    }

    @Override
    public OfficerCatalog search(int catalogId, int officerId, Connection conn) {
        OfficerCatalog officerCatalog = new OfficerCatalog();
        String where = " WHERE (`officerId` = '" + officerId + "' AND `catalogId` = '"+catalogId+"')";
        try {
            ResultSet resultSet = super.customSearch(gClass,where,conn);
            if(resultSet.next()) {
                officerCatalog.setRecordId(resultSet.getInt("recordId"));
                officerCatalog.setOfficerId(resultSet.getInt("officerId"));
                officerCatalog.setCatalogId(resultSet.getInt("catalogId"));
                officerCatalog.setAuthority(resultSet.getInt("authority"));
            }else return null;
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return officerCatalog;
    }

    @Override
    public ArrayList<OfficerCatalog> getAll(Connection conn) {
        ArrayList<OfficerCatalog> officerCatalogs = new ArrayList<>();
        try {
            ResultSet resultSet = super.getAll(gClass,conn);
            while(resultSet.next()) {
                officerCatalogs.add(new OfficerCatalog(
                        resultSet.getInt("recordId"),
                        resultSet.getInt("officerId"),
                        resultSet.getInt("catalogId"),
                        resultSet.getInt("authority")));
            }
        } catch (SQLException e){
            System.out.println("Wrong ResultSet");
        }
        return officerCatalogs;
    }

    @Override
    public int count(Connection conn) {
        return super.count(gClass,conn);
    }

    @Override
    public void clear(Connection conn) {
        super.clear(gClass,conn);
    }
}