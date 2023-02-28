package com.digitalTMC.controller;

import com.digitalTMC.service.DurationService;
import com.digitalTMC.service.vo.form.DurationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DurationController {
    @Autowired
    DurationService durationService;

    @RequestMapping(value = "/create-duration", method = RequestMethod.POST )
    public ResponseEntity<DurationForm> createNewDuration(){
        return new ResponseEntity<>(durationService.createNewDuration("{\"startDate\":\"2025-04-01\",\"endDate\":\"2025-09-30\"}"), HttpStatus.ACCEPTED);
    }
}
