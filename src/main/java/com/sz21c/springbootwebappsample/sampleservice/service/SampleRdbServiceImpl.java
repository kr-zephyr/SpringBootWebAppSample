package com.sz21c.springbootwebappsample.sampleservice.service;

import com.sz21c.springbootwebappsample.sampleservice.dao.SampleDao;
import com.sz21c.springbootwebappsample.sampleservice.dao.SampleHibernateDao;
import com.sz21c.springbootwebappsample.sampleservice.mapper.SampleMapper;
import com.sz21c.springbootwebappsample.sampleservice.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SampleRdbServiceImpl implements SampleRdbService {

    @Autowired
    private SampleDao sampleDao;

    @Autowired
    SampleMapper sampleMapper;

    @Autowired
    SampleHibernateDao sampleHibernateDao;

    public String getSqlTest() throws Exception {
        return sampleDao.getTest();
    }

    /**
     * interface기반의 mapper에서 데이터를 읽어온다.
     */
    public String getSqlTestUsingInterface() throws Exception {
        return sampleMapper.selectMapperSql();
    }

    @Override
    public List<Users> getAllUsersUsingHibernate() throws Exception {
        return sampleHibernateDao.findAllUsers();
    }
}
