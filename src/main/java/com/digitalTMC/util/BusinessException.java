package com.digitalTMC.util;

import com.digitalTMC.service.vo.MessageVO;
import com.digitalTMC.util.enums.SystemMessage;

public class BusinessException extends RuntimeException {
    private final String resultJson;
    public BusinessException(SystemMessage systemMessage) {
        super(systemMessage.getMessage());
        JackJsonUtil<MessageVO> jackJsonUtil = new JackJsonUtil<>();
        this.resultJson = jackJsonUtil.objectToJson(new MessageVO(systemMessage.getMessageCode(),systemMessage.getType(),systemMessage.getMessage()));
    }
    public String getResultJson() {
        return resultJson;
    }
}
