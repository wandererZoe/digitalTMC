package com.digitalTMC.dao;

import java.sql.Connection;
import java.util.ArrayList;

public interface BaseDao<T> {
    int insert(T t, Connection conn);
    int insert(int id,T t, Connection conn);
    int delete(T t, Connection conn);
    boolean update(T t, Connection conn);
    T search(int id, Connection conn);
    ArrayList<T> getAll(Connection conn);
    int count(Connection conn);
}
