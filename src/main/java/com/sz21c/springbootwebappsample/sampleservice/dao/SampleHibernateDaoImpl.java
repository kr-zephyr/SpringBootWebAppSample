package com.sz21c.springbootwebappsample.sampleservice.dao;

import com.sz21c.springbootwebappsample.common.dao.AbstractHibernateDao;
import com.sz21c.springbootwebappsample.sampleservice.model.Users;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("SampleHibernateDao")
public class SampleHibernateDaoImpl extends AbstractHibernateDao implements SampleHibernateDao {
    @Override
    public List<Users> findAllUsers() {
        Criteria criteria = getSession().createCriteria(Users.class);
        return criteria.list();
    }
}
