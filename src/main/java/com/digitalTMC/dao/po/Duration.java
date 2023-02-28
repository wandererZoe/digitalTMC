package com.digitalTMC.dao.po;

import java.sql.Date;

public class Duration {
    private int durationId;
    private Date startDate;
    private Date endDate;

    public Duration() {}
    public Duration(int durationId, Date startDate, Date endDate) {
        this.durationId = durationId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getDurationId() {
        return durationId;
    }

    public void setDurationId(int durationId) {
        this.durationId = durationId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
