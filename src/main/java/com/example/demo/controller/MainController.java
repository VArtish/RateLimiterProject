package com.example.demo.controller;

import com.example.demo.Demo13Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {
    private static final Logger LOGGER = LogManager.getLogger(Demo13Application.class);

    @GetMapping
    public String main() {
        LOGGER.info("Card number: 4255 1901 2775 9952");
        return "index";
    }
}
