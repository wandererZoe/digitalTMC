package com.digitalTMC.service.impl;

import com.digitalTMC.service.SystemService;
import org.junit.jupiter.api.Test;

class SystemServiceTest {

    @Test
    void init() {
        SystemService service = new SystemServiceImpl();
        service.init();
    }
}