package com.sz21c.springbootwebappsample.sampleservice.controller;

import com.sz21c.springbootwebappsample.sampleservice.model.Users;
import com.sz21c.springbootwebappsample.sampleservice.service.SampleHttpService;
import com.sz21c.springbootwebappsample.sampleservice.service.SampleRdbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SampleController {

    final static Logger LOG = LoggerFactory.getLogger(SampleController.class);

    @Autowired
    private SampleRdbService sampleRdbService;

    @Autowired
    private SampleHttpService sampleHttpService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getHome(HttpServletRequest request) throws Exception {
        String rowCount = sampleRdbService.getSqlTest();
        String rowCountUsingInterface = sampleRdbService.getSqlTestUsingInterface();
        String httpResponse = sampleHttpService.getHttpResponse();
        List<Users> userList = sampleRdbService.getAllUsersUsingHibernate();

        request.setAttribute("h1str", "Hello World!!! via controller");
        request.setAttribute("rowCount", rowCount);
        request.setAttribute("rowCountUsingInterface", rowCountUsingInterface);
        request.setAttribute("httpResponse", httpResponse);
        request.setAttribute("userList", userList);

        return new ModelAndView("home");
    }
}
