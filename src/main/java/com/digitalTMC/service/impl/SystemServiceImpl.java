package com.digitalTMC.service.impl;

import com.digitalTMC.dao.basic.SQLConnection;
import com.digitalTMC.dao.basic.SQLSetup;
import com.digitalTMC.service.AuthorityService;
import com.digitalTMC.service.DurationService;
import com.digitalTMC.service.SystemService;
import com.digitalTMC.service.UserService;

import java.sql.Connection;
import java.util.ResourceBundle;

public class SystemServiceImpl implements SystemService {

    AuthorityService authorityService;
    UserService userService;
    DurationService durationService;

    @Override
    public void init() {
        initDatabase();
        initAuthorityList();
        initAdminAccount();
        initCurrentDuration();
    }

    private void initAuthorityList(){
        authorityService = new AuthorityServiceImpl();
        authorityService.resetAll();
    }

    private void initDatabase(){
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        SQLSetup sqlSetup = new SQLSetup();
        sqlSetup.createScheme(connection);
        sqlSetup.createTables(connection);
        sqlConnection.disconnect(connection);
    }

    private void initAdminAccount(){
        ResourceBundle bundle = ResourceBundle.getBundle("admin");
        userService = new UserServiceImpl();
        userService.initialUser(bundle.getString("sapName"));
        userService.activeAccount(bundle.getString("json"));
    }

    private void initCurrentDuration(){
        ResourceBundle bundle = ResourceBundle.getBundle("admin");
        durationService = new DurationServiceImpl();
        durationService.createNewDuration(bundle.getString("duration"));
    }
}