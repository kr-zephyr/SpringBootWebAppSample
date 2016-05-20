package com.sz21c.springbootwebappsample.sampleservice.service;

import com.sz21c.springbootwebappsample.SpringBootWebAppSampleApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SpringBootWebAppSampleApplication.class})
@WebIntegrationTest
public class SampleRdbServiceTest {

    @Autowired
    SampleRdbService sampleRdbService;

    @Test
    public void testGetSqlTest() throws Exception {
        String rtn = sampleRdbService.getSqlTest();

        assertNotNull(rtn);
        assertEquals(rtn, "3");
    }

    @Test
    public void testGetSqlTestUsingInterface() throws Exception {
        String rtn = sampleRdbService.getSqlTestUsingInterface();

        assertNotNull(rtn);
        assertEquals(rtn, "3");
    }
}
