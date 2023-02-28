package com.digitalTMC.controller;

import com.digitalTMC.app.Parameters;
import com.digitalTMC.app.ParametersModel;
import com.digitalTMC.service.UserService;
import com.digitalTMC.util.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST )
    public ParametersModel login(){
        try{
            userService.login("{\"sapNumber\":\"ITMCADM\",\"username\":\"admin\"}");
            return new ParametersModel(Parameters.userId,Parameters.userName,Parameters.currentDurationId);
        }catch (BusinessException e) {
            System.out.println(e.getResultJson());
            return new ParametersModel(Parameters.userId,Parameters.userName,Parameters.currentDurationId);
        }
    }

    @RequestMapping(value = "/initiative/{sapNumber}", method = RequestMethod.POST)
    public void newMember(@PathVariable(value= "sapNumber")String sapNumber,String username) {
        System.out.println(sapNumber);
        System.out.println(username);

//        userService.activeAccount("{\"sapNumber\":\"I332387\",\"username\":\"zoetxt\"}");
    }
}