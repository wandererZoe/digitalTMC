package com.digitalTMC.service.impl;

import com.digitalTMC.dao.DurationDao;
import com.digitalTMC.dao.impl.DurationDaoImpl;
import com.digitalTMC.dao.po.Duration;
import com.digitalTMC.dao.basic.SQLConnection;
import com.digitalTMC.service.DurationService;
import com.digitalTMC.service.vo.DurationVO;
import com.digitalTMC.service.vo.form.DurationForm;
import com.digitalTMC.util.BusinessException;
import com.digitalTMC.util.DateFormatUtil;
import com.digitalTMC.util.JackJsonUtil;
import com.digitalTMC.util.enums.SystemMessage;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

@Service
public class DurationServiceImpl implements DurationService {

    DurationDao impl;
    private int createNewDuration(DurationForm form) {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        Duration duration = new Duration();
        DateFormatUtil util = new DateFormatUtil();
        Date startDate = util.transferString2SqlDate(form.getStartDate());
        Date endDate = util.transferString2SqlDate(form.getEndDate());
        duration.setStartDate(startDate);
        duration.setEndDate(endDate);

        //validation
        impl = new DurationDaoImpl();
        ArrayList<Duration> durations = impl.getAll(connection);
        for (Duration d : durations) {
            if (util.withinDuration(startDate, d.getStartDate(), d.getEndDate())) {
                sqlConnection.disconnect(connection);
                throw new BusinessException(SystemMessage.OVERLAP_DURATION);
            }
            if (util.withinDuration(endDate, d.getStartDate(), d.getEndDate())) {
                sqlConnection.disconnect(connection);
                throw new BusinessException(SystemMessage.OVERLAP_DURATION);
            }
        }

        int newId = impl.insert(duration,connection);
        sqlConnection.disconnect(connection);
        return newId;
    }

    @Override
    public DurationForm createNewDuration(String form) {
        createNewDuration(new JackJsonUtil<DurationForm>().jsonToObject(form, DurationForm.class));
        return new JackJsonUtil<DurationForm>().jsonToObject(form, DurationForm.class);
    }

    @Override
    public int getCurrentDurationId() {
        ArrayList<DurationVO> durations = getAllDurationList();
        DateFormatUtil util = new DateFormatUtil();
        for (DurationVO vo : durations) {
            if (util.withinDuration(vo.getStartDate(), vo.getEndDate()))
                return vo.getDurationId();
        }
        throw new BusinessException(SystemMessage.NO_CURRENT_DURATION);
    }

    private ArrayList<DurationVO> getAllDurationList() {
        SQLConnection sqlConnection = new SQLConnection();
        Connection connection = sqlConnection.connect();
        impl = new DurationDaoImpl();
        ArrayList<Duration> durationEntities = impl.getAll(connection);
        sqlConnection.disconnect(connection);
        ArrayList<DurationVO> durations = new ArrayList<>();
        for (Duration duration : durationEntities) {
            durations.add(new DurationVO(duration.getDurationId(),
                    duration.getStartDate(),
                    duration.getEndDate()
            ));
        }
        return durations;
    }

    @Override
    public String getAllDuration() {
        return new JackJsonUtil<>().objectToJson(getAllDurationList());
    }
}
