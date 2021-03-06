package com.stackroute.authenticationservice.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class HelloWorldController {

    @RequestMapping({ "/hello" })
    public String firstPage() {
        return "Route to respective profiles to ANgular with roles token";
    }

}