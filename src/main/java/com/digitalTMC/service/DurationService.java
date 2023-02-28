package com.digitalTMC.service;

import com.digitalTMC.service.vo.form.DurationForm;

public interface DurationService {
    DurationForm createNewDuration(String form);
    int getCurrentDurationId();
    String getAllDuration();
}
