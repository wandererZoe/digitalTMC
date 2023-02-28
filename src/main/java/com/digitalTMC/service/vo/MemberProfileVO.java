package com.digitalTMC.service.vo;

public class MemberProfileVO {
    private String name;
    private String sapNumber;
    private String emailAddress;
    private String wechatId;

    public MemberProfileVO() {
    }

    public MemberProfileVO(String sapNumber, String name, String emailAddress, String wechatId) {
        this.name = name;
        this.sapNumber = sapNumber;
        this.emailAddress = emailAddress;
        this.wechatId = wechatId;
    }

    public MemberProfileVO(String sapNumber) {
        this.sapNumber = sapNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSapNumber() {
        return sapNumber;
    }

    public void setSapNumber(String sapNumber) {
        this.sapNumber = sapNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }
}
