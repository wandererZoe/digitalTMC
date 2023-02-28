package com.digitalTMC.dao.basic;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SQLSetup {
    private final String schema;
    public SQLSetup() {
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        this.schema = bundle.getString("schema");
    }

    public void createScheme(Connection conn) {
        StringBuilder sql = new StringBuilder(
                "CREATE SCHEMA `"+schema+"` DEFAULT CHARACTER SET utf8;"
        );
        System.out.println(sql);
        try{
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql.toString());
            statement.close();
        } catch (SQLException e) {
            System.out.println("SQLException:" + sql);
        }
    }

    public void createTables(Connection conn) {
        StringBuilder sql;

        //duration
        sql = new StringBuilder(
                "CREATE TABLE "+schema+".`duration` (\n" +
                        "  `durationId` int NOT NULL AUTO_INCREMENT,\n" +
                        "  `startDate` date DEFAULT NULL,\n" +
                        "  `endDate` date DEFAULT NULL,\n" +
                        "  PRIMARY KEY (`durationId`)\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3;\n"
        );
        System.out.println(sql);
        try{
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql.toString());
            statement.close();
        } catch (SQLException e) {
            System.out.println("SQLException:" + sql);
        }

        //meeting
        sql = new StringBuilder(
                "CREATE TABLE "+schema+".`meeting` (\n" +
                        "  `meetingId` int NOT NULL AUTO_INCREMENT,\n" +
                        "  `theme` varchar(45) DEFAULT NULL,\n" +
                        "  `startTime` datetime DEFAULT NULL,\n" +
                        "  `endTime` datetime DEFAULT NULL,\n" +
                        "  `standard` int DEFAULT NULL,\n" +
                        "  `offline` int DEFAULT NULL,\n" +
                        "  `posterId` int DEFAULT NULL,\n" +
                        "  `recordingId` int DEFAULT NULL,\n" +
                        "  `comments` varchar(45) DEFAULT NULL,\n" +
                        "  `markAsDeleted` int DEFAULT NULL,\n" +
                        "  PRIMARY KEY (`meetingId`)\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n"
        );
        System.out.println(sql);
        try{
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql.toString());
            statement.close();
        } catch (SQLException e) {
            System.out.println("SQLException:" + sql);
        }

        //memberpathway
        sql = new StringBuilder(
                "CREATE TABLE "+schema+".`memberpathway` (\n" +
                        "  `recordId` int NOT NULL AUTO_INCREMENT,\n" +
                        "  `memberId` int NOT NULL,\n" +
                        "  `pathwayId` int DEFAULT NULL,\n" +
                        "  `level` int DEFAULT NULL,\n" +
                        "  `changeOn` datetime DEFAULT NULL,\n" +
                        "  PRIMARY KEY (`recordId`,`memberId`)\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n"
        );
        System.out.println(sql);
        try{
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql.toString());
            statement.close();
        } catch (SQLException e) {
            System.out.println("SQLException:" + sql);
        }

        //membership
        sql = new StringBuilder(
                "CREATE TABLE "+schema+".`membership` (\n" +
                        "  `recordId` int NOT NULL AUTO_INCREMENT,\n" +
                        "  `memberId` int NOT NULL,\n" +
                        "  `durationId` int NOT NULL,\n" +
                        "  `post` int DEFAULT NULL,\n" +
                        "  `international` int DEFAULT NULL,\n" +
                        "  PRIMARY KEY (`recordId`,`memberId`,`durationId`)\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n"
        );
        System.out.println(sql);
        try{
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql.toString());
            statement.close();
        } catch (SQLException e) {
            System.out.println("SQLException:" + sql);
        }

        //mentormentee
        sql = new StringBuilder(
                "CREATE TABLE "+schema+".`mentormentee` (\n" +
                        "  `menteeId` int NOT NULL,\n" +
                        "  `mentorId` int DEFAULT NULL,\n" +
                        "  PRIMARY KEY (`menteeId`)\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n"
        );
        System.out.println(sql);
        try{
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql.toString());
            statement.close();
        } catch (SQLException e) {
            System.out.println("SQLException:" + sql);
        }

        //officercatalog
        sql = new StringBuilder(
                "CREATE TABLE "+schema+".`officercatalog` (\n" +
                        "  `recordId` int NOT NULL AUTO_INCREMENT,\n" +
                        "  `officerId` int DEFAULT NULL,\n" +
                        "  `catalogId` int DEFAULT NULL,\n" +
                        "  `authority` int DEFAULT '-1',\n" +
                        "  PRIMARY KEY (`recordId`)\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n"
        );
        System.out.println(sql);
        try{
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql.toString());
            statement.close();
        } catch (SQLException e) {
            System.out.println("SQLException:" + sql);
        }

        //poster
        sql = new StringBuilder(
                "CREATE TABLE "+schema+".`poster` (\n" +
                        "  `posterId` int NOT NULL AUTO_INCREMENT,\n" +
                        "  `name` varchar(45) NOT NULL,\n" +
                        "  `address` varchar(255) DEFAULT NULL,\n" +
                        "  `tags` varchar(45) DEFAULT NULL,\n" +
                        "  PRIMARY KEY (`posterId`)\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n"
        );
        System.out.println(sql);
        try{
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql.toString());
            statement.close();
        } catch (SQLException e) {
            System.out.println("SQLException:" + sql);
        }

        //profile
        sql = new StringBuilder(
                "CREATE TABLE "+schema+".`profile` (\n" +
                        "  `memberId` int NOT NULL,\n" +
                        "  `sapNumber` varchar(7) NOT NULL,\n" +
                        "  `name` varchar(45) DEFAULT NULL,\n" +
                        "  `email` varchar(45) DEFAULT NULL,\n" +
                        "  `wechat` varchar(45) DEFAULT NULL,\n" +
                        "  PRIMARY KEY (`memberId`,`sapNumber`)\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n"
        );
        System.out.println(sql);
        try{
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql.toString());
            statement.close();
        } catch (SQLException e) {
            System.out.println("SQLException:" + sql);
        }

        //registration
        sql = new StringBuilder(
                "CREATE TABLE "+schema+".`registration` (\n" +
                        "  `meetingId` int NOT NULL,\n" +
                        "  `roleId` int NOT NULL,\n" +
                        "  `counter` int NOT NULL,\n" +
                        "  `memberId` int NOT NULL,\n" +
                        "  `markAsDeleted` int DEFAULT NULL,\n" +
                        "  PRIMARY KEY (`meetingId`,`roleId`,`counter`)\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n"
        );
        System.out.println(sql);
        try{
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql.toString());
            statement.close();
        } catch (SQLException e) {
            System.out.println("SQLException:" + sql);
        }

        //specialrole
        sql = new StringBuilder(
                "CREATE TABLE "+schema+".`specialrole` (\n" +
                        "  `meetingid` int NOT NULL,\n" +
                        "  `roleid` varchar(45) NOT NULL,\n" +
                        "  `description` varchar(45) DEFAULT NULL,\n" +
                        "  PRIMARY KEY (`meetingid`,`roleid`)\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n"
        );
        System.out.println(sql);
        try{
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql.toString());
            statement.close();
        } catch (SQLException e) {
            System.out.println("SQLException:" + sql);
        }

        //user
        sql = new StringBuilder(
                "CREATE TABLE "+schema+".`user` (\n" +
                        "  `memberId` int NOT NULL AUTO_INCREMENT,\n" +
                        "  `sapNumber` varchar(7) NOT NULL,\n" +
                        "  `activated` tinyint DEFAULT NULL,\n" +
                        "  `createOn` datetime DEFAULT NULL,\n" +
                        "  `activeOn` datetime DEFAULT NULL,\n" +
                        "  `username` varchar(45) DEFAULT NULL,\n" +
                        "  `lockUsername` tinyint DEFAULT NULL,\n" +
                        "  PRIMARY KEY (`memberId`,`sapNumber`)\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n"
        );
        System.out.println(sql);
        try{
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql.toString());
            statement.close();
        } catch (SQLException e) {
            System.out.println("SQLException:" + sql);
        }

        //userchangelog
        sql = new StringBuilder(
                "CREATE TABLE "+schema+".`userchangelog` (\n" +
                        "  `recordId` int NOT NULL AUTO_INCREMENT,\n" +
                        "  `requestBy` char(7) DEFAULT NULL,\n" +
                        "  `requestOn` datetime DEFAULT NULL,\n" +
                        "  `status` int DEFAULT NULL,\n" +
                        "  `processBy` char(7) DEFAULT NULL,\n" +
                        "  `processOn` datetime DEFAULT NULL,\n" +
                        "  PRIMARY KEY (`recordId`)\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n"
        );
        System.out.println(sql);
        try{
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql.toString());
            statement.close();
        } catch (SQLException e) {
            System.out.println("SQLException:" + sql);
        }
    }
}
