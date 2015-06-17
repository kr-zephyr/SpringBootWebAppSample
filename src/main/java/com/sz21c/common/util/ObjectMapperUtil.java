package com.sz21c.common.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectMapperUtil {

    static final Logger LOG = LoggerFactory.getLogger(ObjectMapperUtil.class);

    /**
     * ObjectMapper 객체 생성
     * @return
     */
    public static ObjectMapper generateObjectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();
        //** 받아들이려는 Model보다 큰 데이터 구조가 왔을 때, Model에 없는 필드 무시
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper;
    }

    /**
     * Object를 Json 형태의 String으로 반환
     * @param object
     * @return
     */
    public static String convertObjectIntoJsonString(Object object) {
        String jsonBody = null;

        ObjectMapper objectMapper = generateObjectMapper();
        try{
            if(null != object)
                jsonBody = objectMapper.writeValueAsString(object);
        }catch (Exception e) {
            System.out.println("##### ERROR DURING CONVERT OBJECT INTO JSON #####");
            e.printStackTrace();
        }

        return jsonBody;
    }
}
