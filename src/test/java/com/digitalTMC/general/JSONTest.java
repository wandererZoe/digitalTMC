package com.digitalTMC.general;

import com.digitalTMC.util.JackJsonUtil;
import com.digitalTMC.service.vo.MemberProfileVO;
import org.junit.Test;

public class JSONTest {

    @Test
    public void testObjectToJson() {
        System.out.println("==============ObjectToJson=================");
        JackJsonUtil<MemberProfileVO> jackJsonUtil = new JackJsonUtil<>();
        String str = "{\"name\":\"Zoe\",\"sapNumber\":\"I332387\",\"emailAddress\":\"zoe.tian@sap.com\",\"wechatId\":\"Tayataya\"}";
        MemberProfileVO profileForm = jackJsonUtil.jsonToObject(str,MemberProfileVO.class);

        System.out.println(jackJsonUtil.objectToJson(profileForm));
        System.out.println("==============ObjectToJson=================");
        System.out.println();
    }
}
