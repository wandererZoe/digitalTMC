package com.digitalTMC.service.vo;

public class AuthorityByOfficerVO {
    String officer;
    boolean read;
    boolean write;

    public AuthorityByOfficerVO() {
    }

    public AuthorityByOfficerVO(String officer, boolean read, boolean write) {
        this.officer = officer;
        this.read = read;
        this.write = write;
    }

    public String getOfficer() {
        return officer;
    }

    public void setOfficer(String officer) {
        this.officer = officer;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isWrite() {
        return write;
    }

    public void setWrite(boolean write) {
        this.write = write;
    }
}
