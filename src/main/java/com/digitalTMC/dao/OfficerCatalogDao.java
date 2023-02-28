package com.digitalTMC.dao;

import com.digitalTMC.dao.po.OfficerCatalog;

import java.sql.Connection;

public interface OfficerCatalogDao extends BaseDao<OfficerCatalog>{
    void clear(Connection conn);
    OfficerCatalog search(int catalogId, int officerId, Connection conn);
}
