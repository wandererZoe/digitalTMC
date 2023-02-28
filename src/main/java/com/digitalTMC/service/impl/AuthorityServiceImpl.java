package com.digitalTMC.service.impl;

import com.digitalTMC.app.Parameters;
import com.digitalTMC.dao.MembershipDao;
import com.digitalTMC.dao.OfficerCatalogDao;
import com.digitalTMC.dao.impl.MembershipDaoImpl;
import com.digitalTMC.dao.impl.OfficerCatalogDaoImpl;
import com.digitalTMC.dao.po.OfficerCatalog;
import com.digitalTMC.dao.basic.SQLConnection;
import com.digitalTMC.service.AuthorityService;
import com.digitalTMC.util.DynamicEnumUtils;
import com.digitalTMC.util.enums.Authority;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    OfficerCatalogDao officerCatalogDao;
    MembershipDao membershipDao;

    ArrayList<OfficerCatalog> officerCatalogs;
    int catalogCount;
    int officerCount;

    public AuthorityServiceImpl(){

        officerCatalogs = new ArrayList<>();
        catalogCount = 0;
        officerCount = 0;
        ResourceBundle catalogBundle = ResourceBundle.getBundle("catalog");
        ResourceBundle officerBundle = ResourceBundle.getBundle("officer");
        Enumeration<String> catalogs = catalogBundle.getKeys();
        Enumeration<String> officers = officerBundle.getKeys();
        while(catalogs.hasMoreElements()) {
            catalogCount ++;
            officerCount = 0;
            String catalog = catalogs.nextElement();
            while (officers.hasMoreElements()){
                officerCount ++;
                String officer = officers.nextElement();
                int officerIndex = Integer.parseInt(officerBundle.getString(officer));
                int catalogIndex = Integer.parseInt(catalogBundle.getString(catalog));
                officerCatalogs.add(new OfficerCatalog(officerIndex,catalogIndex));
            }
            officers = officerBundle.getKeys();
        }
    }

    @Override
    public void resetAll() {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        officerCatalogDao = new OfficerCatalogDaoImpl();
        officerCatalogDao.clear(connection);
        for (OfficerCatalog officerCatalog : officerCatalogs) {
            officerCatalogDao.insert(officerCatalog,connection);
        }
        sqlConnection.disconnect(connection);
    }

    @Override
    public boolean checkCatalogConsistency() {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        officerCatalogDao = new OfficerCatalogDaoImpl();
        int recordCount = officerCatalogDao.count(connection);
        sqlConnection.disconnect(connection);
        return recordCount / catalogCount == officerCount;
    }

    @Override
    public boolean isReadable(String pagePerm) {
        return accessible(pagePerm) != Authority.RESTRICTIVE;
    }

    @Override
    public boolean isWritable(String pagePerm) {
        return accessible(pagePerm) == Authority.READ_AND_WRITE;
    }

    private Authority accessible(String pagePerm) {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        membershipDao = new MembershipDaoImpl();
        officerCatalogDao = new OfficerCatalogDaoImpl();
        int postId = membershipDao.searchByMemberId(Parameters.userId,Parameters.currentDurationId,connection).getPost();
        int authority = officerCatalogDao.search(DynamicEnumUtils.getCatalogNumber(pagePerm),postId,connection).getAuthority();
        sqlConnection.disconnect(connection);
        return Authority.codeOf(authority);
    }
}