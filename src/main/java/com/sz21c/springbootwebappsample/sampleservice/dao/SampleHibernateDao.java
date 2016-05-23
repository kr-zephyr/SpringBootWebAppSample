package com.sz21c.springbootwebappsample.sampleservice.dao;


import com.sz21c.springbootwebappsample.sampleservice.model.Users;

import java.util.List;

public interface SampleHibernateDao {

    List<Users> findAllUsers();
}
