package com.digitalTMC.dao.basic;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

class SQLExecuteTest {

    @Test
    void createScheme() {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        SQLSetup sqlSetup = new SQLSetup();
        sqlSetup.createScheme(connection);
        sqlSetup.createTables(connection);
        sqlConnection.disconnect(connection);
    }
}