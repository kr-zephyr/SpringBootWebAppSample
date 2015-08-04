package com.sz21c.springbootwebappsample.sampleservice.service;

import com.sz21c.springbootwebappsample.sampleservice.dao.SampleDao;
import com.sz21c.springbootwebappsample.sampleservice.mapper.SampleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleMySqlServiceImpl implements SampleMySqlService {

    final static Logger LOG = LoggerFactory.getLogger(SampleMySqlServiceImpl.class);

    @Autowired
    private SampleDao sampleDao;

    @Autowired
    SampleMapper sampleMapper;

    public String getSqlTest() throws Exception {
        return sampleDao.getTest();
    }

    /**
     * interface기반의 mapper에서 데이터를 읽어온다.
     */
    public String getSqlTestUsingInterface() throws Exception {
        return sampleMapper.selectMapperSql();
    }
}
