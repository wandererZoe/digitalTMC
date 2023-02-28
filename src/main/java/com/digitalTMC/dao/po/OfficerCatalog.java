package com.digitalTMC.dao.po;

import com.digitalTMC.util.enums.Authority;

public class OfficerCatalog {

    private int recordId;
    private int officerId;
    private int catalogId;
    private int authority;

    public OfficerCatalog() {
    }

    public OfficerCatalog(int recordId, int officerId, int catalogId, int authority) {
        this.recordId = recordId;
        this.officerId = officerId;
        this.catalogId = catalogId;
        this.authority = authority;
    }

    public OfficerCatalog(int officerId, int catalogId) {
        this.officerId = officerId;
        this.catalogId = catalogId;
        this.authority = Authority.RESTRICTIVE.getValue();
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getOfficerId() {
        return officerId;
    }

    public void setOfficerId(int officerId) {
        this.officerId = officerId;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }
}
