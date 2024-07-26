package com.otclub.humate.domain.activity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/activity")
@RestController
public class ActivityController {

    @GetMapping("/123")
    public String hello() {
        return "hello123123";
    }
}
