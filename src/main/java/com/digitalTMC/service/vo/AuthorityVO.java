package com.digitalTMC.service.vo;

import java.util.ArrayList;


public class AuthorityVO {
    private String catalog;
    ArrayList<AuthorityByOfficerVO> authorityByOfficerVOArrayList;

    public AuthorityVO(String catalog, ArrayList<AuthorityByOfficerVO> authorityByOfficerVOArrayList) {
        this.catalog = catalog;
        this.authorityByOfficerVOArrayList = authorityByOfficerVOArrayList;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public ArrayList<AuthorityByOfficerVO> getAuthorityByOfficerVOArrayList() {
        return authorityByOfficerVOArrayList;
    }

    public void setAuthorityByOfficerVOArrayList(ArrayList<AuthorityByOfficerVO> authorityByOfficerVOArrayList) {
        this.authorityByOfficerVOArrayList = authorityByOfficerVOArrayList;
    }
}
