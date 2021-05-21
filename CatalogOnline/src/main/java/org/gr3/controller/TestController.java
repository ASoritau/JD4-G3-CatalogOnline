package org.gr3.controller;

import org.gr3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String hello() {
        return "test";
    }

//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public String loginProto() {
//        return "index";
//    }
}