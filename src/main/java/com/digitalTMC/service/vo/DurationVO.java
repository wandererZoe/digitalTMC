package com.digitalTMC.service.vo;

import com.digitalTMC.util.DateFormatUtil;

import java.util.Date;


public class DurationVO {
    private int durationId;
    private Date startDate;
    private Date endDate;
    private String description;
    private String status;

    public DurationVO(int durationId, Date startDate, Date endDate) {
        this.durationId = durationId;
        this.startDate = startDate;
        this.endDate = endDate;
        setDescription();
        setStatus();
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
        setDescription();
        setStatus();
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
        setDescription();
        setStatus();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription() {
        DateFormatUtil util = new DateFormatUtil();
        this.description = "From "+util.simpleDateFormat(startDate)+" to "+util.simpleDateFormat(endDate);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus() {
        DateFormatUtil util = new DateFormatUtil();
        this.status = util.getCurrentStatus(startDate,endDate).name();
    }
}
