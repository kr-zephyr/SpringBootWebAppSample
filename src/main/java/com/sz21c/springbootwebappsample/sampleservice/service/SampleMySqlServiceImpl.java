package com.sz21c.springbootwebappsample.sampleservice.service;

import com.sz21c.springbootwebappsample.sampleservice.dao.SampleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleMySqlServiceImpl implements SampleMySqlService {

    final static Logger LOG = LoggerFactory.getLogger(SampleMySqlServiceImpl.class);

    @Autowired
    private SampleDao sampleDao;

    public String getSqlTest() throws Exception {
        return sampleDao.getTest();
    }
}
