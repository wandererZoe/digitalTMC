package com.digitalTMC.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

public class JackJsonUtil<T> {

    private ObjectMapper objectMapper;
    private static final String DEFAULT_DATE_FORMAT="yyyy-MM-dd";

    public String objectToJson(Object obj) {
        String json = "";
        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat(DEFAULT_DATE_FORMAT));
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public T jsonToObject(String json, Class<T> obj) {
        try {
            objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}