package com.digitalTMC.dao.impl;

import com.digitalTMC.dao.BaseDao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ResourceBundle;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {
    private final String schema;
    public BaseDaoImpl() {
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        this.schema = bundle.getString("schema");
    }

    @Override
    public int insert(T t, Connection conn) {
        Class<?> className = t.getClass();
        String tableName = className.getSimpleName();
        StringBuilder sql = new StringBuilder("INSERT INTO "+schema+"." + tableName.toLowerCase() + " (");
        Field[] df = className.getDeclaredFields();
        for (int i = 1; i < df.length; i++) {
            Field field = df[i];
            String attribute = field.getName();
            sql.append("`").append(attribute).append("`, ");
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 2));
        sql = new StringBuilder(sql+") VALUES (");

        for (int i = 1; i < df.length; i++) {
            Field field = df[i];
            String attribute = field.getName();
            String methodName = "get" + attribute.substring(0, 1).toUpperCase() + attribute.substring(1);
            try {
                Method method = className.getDeclaredMethod(methodName);
                Object value = method.invoke(t);
                sql.append("'").append(value).append("', ");
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                System.out.println("Wrong sql:" + sql);
                return -1;
            }
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 2)+")");
        System.out.println(sql);

        int id = -1;
        try{
            Statement statement = conn.createStatement();
            int rows = statement.executeUpdate(sql.toString(),Statement.RETURN_GENERATED_KEYS); //Return the number of rows of affected data
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("SQLException:" + sql);
        }
        return id;
    }

    @Override
    public int insert(int id, T t, Connection conn) {
        Class<?> className = t.getClass();
        String tableName = className.getSimpleName();
        StringBuilder sql = new StringBuilder("INSERT INTO "+schema+"." + tableName.toLowerCase() + " (");
        Field[] df = className.getDeclaredFields();
        for (Field field : df) {
            String attribute = field.getName();
            sql.append("`").append(attribute).append("`, ");
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 2));
        sql = new StringBuilder(sql+") VALUES (");

        for (Field field : df) {
            String attribute = field.getName();
            String methodName = "get" + attribute.substring(0, 1).toUpperCase() + attribute.substring(1);
            try {
                Method method = className.getDeclaredMethod(methodName);
                Object value = method.invoke(t);
                sql.append("'").append(value).append("', ");
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                System.out.println("Wrong sql:" + sql);
                return -1;
            }
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 2)+")");
        System.out.println(sql);

        try{
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql.toString());
            statement.close();
        } catch (SQLException e) {
            System.out.println("SQLException:" + sql);
        }
        return id;
    }

    @Override
    public int delete(T t, Connection conn) {
        int affectedRows = 0;
        Class<?> className = t.getClass();
        String tableName = className.getSimpleName();
        StringBuilder sql = new StringBuilder("DELETE FROM "+schema+"." + tableName.toLowerCase());
        Field[] df = className.getDeclaredFields();
        String id = df[0].getName();
        String getId = "get" + id.substring(0, 1).toUpperCase() + id.substring(1);
        String where = " WHERE (";
        try{
            Method method = className.getDeclaredMethod(getId);
            Object value = method.invoke(t);
            where += "`"+id +"` = '"+value+"'";
        }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.out.println("Wrong sql:"+sql);
            return affectedRows;
        }

        sql.append(where).append(")");
        System.out.println(sql);

        try{
            Statement statement = conn.createStatement();
            affectedRows = statement.executeUpdate(sql.toString()); //Return the number of rows of affected data
            System.out.println(affectedRows);
            statement.close();
        } catch (SQLException e){
            System.out.println("SQLException:"+sql);
            return affectedRows;
        }
        return affectedRows;
    }

    @Override
    public boolean update(T t, Connection conn) {
        Class<?> className = t.getClass();
        String tableName = className.getSimpleName();
        StringBuilder sql = new StringBuilder("UPDATE "+schema+"." + tableName.toLowerCase() + " SET ");
        Field[] df = className.getDeclaredFields();
        for (Field field : df) {
            String attribute = field.getName();
            String methodName = "get" + attribute.substring(0, 1).toUpperCase() + attribute.substring(1);
            try {
                Method method = className.getDeclaredMethod(methodName);
                Object value = method.invoke(t);
                sql.append("`").append(attribute).append("` = '").append(value).append("', ");
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                System.out.println("Wrong sql:" + sql);
                return false;
            }
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 2));

        String id = df[0].getName();
        String getId = "get" + id.substring(0, 1).toUpperCase() + id.substring(1);
        String where = " WHERE (";
        try {
            Method method = className.getDeclaredMethod(getId);
            Object value = method.invoke(t);
            where += "`"+id +"` = '"+value+"'";
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.out.println("Wrong sql:"+sql);
            return false;
        }

        sql.append(where).append(")");
        System.out.println(sql);

        try {
            Statement statement = conn.createStatement();
            int rows = statement.executeUpdate(sql.toString()); //Return the number of rows of affected data
            System.out.println(rows);
            statement.close();
        } catch (SQLException e){
            System.out.println("SQLException:" + sql);
            return false;
        }
        return true;
    }

    protected void clear(Class<T> className, Connection conn){
        String tableName = className.getSimpleName();
        StringBuilder sql = new StringBuilder("TRUNCATE "+schema+"." + tableName.toLowerCase());
        System.out.println(sql);

        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql.toString()); //Return the number of rows of affected data
            statement.close();
        } catch (SQLException e){
            System.out.println("SQLException:" + sql);
        }
    }

    protected ResultSet search(Class<T> className, int id, Connection conn){
        String tableName = className.getSimpleName();
        StringBuilder sql = new StringBuilder("SELECT ");
        Field[] df = className.getDeclaredFields();
        for (Field field : df) {
            String attribute = field.getName();
            sql.append("`").append(attribute).append("`, ");
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 2)+" FROM "+schema+"." + tableName.toLowerCase()+" WHERE (`"+df[0].getName() +"` = '"+id+"')");
        System.out.println(sql);

        try {
            Statement statement = conn.createStatement();
            return statement.executeQuery(sql.toString());
        } catch (SQLException e) {
            return null;
        }
    }

    protected ResultSet getAll(Class<T> className, Connection conn) {
        String tableName = className.getSimpleName();
        StringBuilder sql = new StringBuilder("SELECT * FROM "+schema+"." + tableName.toLowerCase());
        System.out.println(sql);

        try {
            Statement statement = conn.createStatement();
            return statement.executeQuery(sql.toString());
        } catch (SQLException e) {
            return null;
        }
    }

    protected int count(Class<T> className, Connection conn) {
        String tableName = className.getSimpleName();
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) AS count FROM "+schema+"." + tableName.toLowerCase());
        System.out.println(sql);
        int size = 0;

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql.toString());
            if(resultSet.next()) {
                size = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("SQLException");
        }
        return size;
    }

    protected ResultSet customSearch(Class<T> className, String where, Connection conn) throws SQLException {
        String tableName = className.getSimpleName();
        StringBuilder sql = new StringBuilder("SELECT ");
        Field[] df = className.getDeclaredFields();
        for (Field field : df) {
            String attribute = field.getName();
            sql.append("`").append(attribute).append("`, ");
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 2)+" FROM "+schema+"." + tableName.toLowerCase()+where);
        System.out.println(sql);

        try {
            Statement statement = conn.createStatement();
            return statement.executeQuery(sql.toString());
        } catch (SQLException e) {
            return null;
        }
    }

    protected ResultSet customGetAll(Class<T> className, String where, Connection conn) {
        String tableName = className.getSimpleName();
        StringBuilder sql = new StringBuilder("SELECT * FROM "+schema+"." + tableName.toLowerCase()+where);
        System.out.println(sql);

        try {
            Statement statement = conn.createStatement();
            return statement.executeQuery(sql.toString());
        } catch (SQLException e) {
            return null;
        }
    }
}