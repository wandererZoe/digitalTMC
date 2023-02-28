package com.digitalTMC.service.vo;

public class MessageVO {

    private int messageCode;
    private String type;
    private String message;

    public MessageVO(int messageCode, String type, String message) {
        this.messageCode = messageCode;
        this.type = type;
        this.message = message;
    }

    public int getMessageCode() {
        return messageCode;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessageCode(int messageCode) {
        this.messageCode = messageCode;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
