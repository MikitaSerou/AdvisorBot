package com.example.advisorbot.controllers;


import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {


    private static final Logger log = org.slf4j.LoggerFactory.getLogger(MainController.class);

    @RequestMapping("/")
    public String home() {

        return "index";
    }
}
