package com.sz21c.springbootwebappsample.sampleservice.service;

import com.sz21c.springbootwebappsample.sampleservice.model.Users;

import java.util.List;

public interface SampleRdbService {
    public String getSqlTest() throws Exception;
    public String getSqlTestUsingInterface() throws Exception;
    public List<Users> getAllUsersUsingHibernate() throws Exception;
}
