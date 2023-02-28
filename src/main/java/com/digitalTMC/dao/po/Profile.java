package com.digitalTMC.dao.po;

public class Profile {
    private int memberId;
    private String sapNumber;
    private String name;
    private String email;
    private String wechat;

    public Profile() {
    }

    public Profile(int memberId, String sapNumber) {
        this.memberId = memberId;
        this.sapNumber = sapNumber;
        this.name = "";
        this.email = "";
        this.wechat = "";
    }

    public Profile(int memberId, String sapNumber, String name, String email, String wechat) {
        this.memberId = memberId;
        this.sapNumber = sapNumber;
        this.name = name;
        this.email = email;
        this.wechat = wechat;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getSapNumber() {
        return sapNumber;
    }

    public void setSapNumber(String sapNumber) {
        this.sapNumber = sapNumber;
    }

    public String getName() {
        if(name==null) return "";
        else return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        if(email==null) return "";
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWechat() {
        if(wechat==null) return "";
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
}
