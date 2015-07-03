package com.sz21c.springbootwebappsample.sampleservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sz21c.common.util.ApacheHttpClient;
import com.sz21c.springbootwebappsample.common.properties.HttpTestProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SampleHttpServiceImpl implements SampleHttpService {

    final static Logger LOG = LoggerFactory.getLogger(SampleHttpServiceImpl.class);

    @Autowired
    private HttpTestProperties httpTestProperties;

    @Autowired
    ObjectMapper objectMapper;

    public String getHttpResponse() throws Exception {
        ApacheHttpClient apacheHttpClient = new ApacheHttpClient();

        String reqUrl = httpTestProperties.getDomain() + httpTestProperties.getUrl();

        Map<String, Object> httpHeaderMap = new HashMap<>();
        httpHeaderMap.put("Content-Type", "application/json;charset=UTF-8");

        Map<String, Object> postParam = new HashMap<>();
        postParam.put("message", "This is a message.");
        String postParamStr = objectMapper.writeValueAsString(postParam);

        String platformResponse = apacheHttpClient.requestPOST(
                reqUrl, postParamStr, httpHeaderMap, "application/json", "UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(platformResponse);

        return jsonNode.get("json").get("message").textValue();
    }

}
