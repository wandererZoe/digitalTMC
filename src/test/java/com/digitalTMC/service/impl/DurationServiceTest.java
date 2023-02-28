package com.digitalTMC.service.impl;

import com.digitalTMC.service.DurationService;
import com.digitalTMC.service.impl.DurationServiceImpl;
import org.junit.Test;

public class DurationServiceTest {
//    @Test
//    public void testNewDuration() {
//        System.out.println("==============NewDuration=================");
//        DurationService service = new DurationServiceImpl();
//        DurationVO vo = new DurationVO(new Date(),new Date());
//        service.createNewDuration(vo);
//        System.out.println("==============NewDuration=================");
//        System.out.println();
//    }


    @Test
    public void testGetAllDuration() {
        System.out.println("==============GetAllDuration=================");
        DurationService service = new DurationServiceImpl();
        System.out.println(service.getAllDuration());
        System.out.println("==============GetAllDuration=================");
        System.out.println();
    }
}