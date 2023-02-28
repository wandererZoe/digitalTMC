package com.digitalTMC.dao.po;

import com.digitalTMC.util.enums.UserStatus;
import com.digitalTMC.util.DateFormatUtil;

public class User {
    private int memberId;
    private String sapNumber;
    private int activated;
    private String createOn;
    private String activeOn;
    private String username;
    private int lockUsername;

    public User() {
    }

    public User(String sapNumber) {
        DateFormatUtil dateFormatUtil = new DateFormatUtil();
        this.sapNumber = sapNumber;
        this.activated = UserStatus.Inactivated.getValue();
        this.createOn = dateFormatUtil.getCurrentDateTime();
        this.activeOn = dateFormatUtil.getInitialDateTime();
        this.username = "";
        this.lockUsername = UserStatus.Unlocked.getValue();
    }

    public User(int memberId, String sapNumber, int activated, String createOn, String activeOn, String username, int lockUsername) {
        this.memberId = memberId;
        this.sapNumber = sapNumber;
        this.activated = activated;
        this.createOn = createOn;
        this.activeOn = activeOn;
        this.username = username;
        this.lockUsername = lockUsername;
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

    public int getActivated() {
        return activated;
    }

    public void setActivated(int activated) {
        this.activated = activated;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    public String getActiveOn() {
        return activeOn;
    }

    public void setActiveOn(String activeOn) {
        this.activeOn = activeOn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLockUsername() {
        return lockUsername;
    }

    public void setLockUsername(int lockUsername) {
        this.lockUsername = lockUsername;
    }
}