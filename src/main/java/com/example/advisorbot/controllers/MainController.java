package com.example.advisorbot.controllers;



import com.example.advisorbot.utils.MySqlExecutor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    private static Boolean databaseIsInit = false;

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(MainController.class);

    @Autowired
    private MySqlExecutor sqlExecutor;

    @RequestMapping("/")
    public String home() {

        if (!databaseIsInit) {
            sqlExecutor.initDataBase();
            databaseIsInit=true;
        }

        return "index";
    }
}
