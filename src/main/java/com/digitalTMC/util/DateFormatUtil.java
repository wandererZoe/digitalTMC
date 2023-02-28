package com.digitalTMC.util;

import com.digitalTMC.util.enums.DateStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUtil {
    public String simpleDateFormatCurrent(){
        Calendar calendar= Calendar.getInstance();
        return simpleDateFormat(calendar.getTime());
    }

    public String dateTimeFormatCurrent(){
        Calendar calendar= Calendar.getInstance();
        return simpleDateFormat(calendar.getTime());
    }

    public String simpleDateFormat(Date date){
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public String dateTimeFormat(Date date){
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }

    public Date transferString2DateTime(String s) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
        } catch (ParseException e) {
            System.out.println("DateTime Transfer Error");
        }
        return date;
    }

    public Date transferString2UtilDate(String s) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(s);
        } catch (ParseException e) {
            System.out.println("Date Transfer Error");
        }
        return date;
    }

    public java.sql.Date transferString2SqlDate(String s) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(s);
        } catch (ParseException e) {
            System.out.println("Date Transfer Error");
        }
        return util2sql(date);
    }

    public boolean withinDuration(Date startDate, Date endDate){
        if(getCurrentStatus(startDate,endDate)== DateStatus.Current)
            return true;
        return false;
    }

    public boolean withinDuration(Date date4check, Date startDate, Date endDate){
        if(getStatus(date4check,startDate,endDate)== DateStatus.Current)
            return true;
        return false;
    }

    public DateStatus getCurrentStatus(Date startDate, Date endDate) {
        Date today = new Date(System.currentTimeMillis());
        return getStatus(today,startDate,endDate);
    }

    public DateStatus getStatus(Date date4check, Date startDate, Date endDate) {
        if(date4check.before(startDate)){
            return DateStatus.Not_in_effect;
        }else if(date4check.after(endDate)){
            return DateStatus.Expire;
        }else{
            return DateStatus.Current;
        }
    }

    public Date sql2util(java.sql.Date sqlDate){
        Date utilDate = new Date(sqlDate.getTime());
        return utilDate;
    }

    public java.sql.Date util2sql(Date utilDate){
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

    public String getCurrentDateTime(){
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(calendar.getTime());
    }

    public String getInitialDateTime(){
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(0);
    }
}
