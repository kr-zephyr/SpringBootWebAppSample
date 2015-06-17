package com.sz21c.springbootwebappsample.sampleservice.dao;

import com.sz21c.springbootwebappsample.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class SampleDao extends BaseDao {

    public String getTest() throws Exception {
        return getSqlSession().selectOne("SpringBootSampleMapper.testSql");
    }
}
