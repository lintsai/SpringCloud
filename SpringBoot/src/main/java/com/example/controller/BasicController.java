package com.example.controller;

import com.example.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BasicController {
    @Autowired
    BasicService basicService;
    @GetMapping("/version")
    public String version() {
        return basicService.version();
    }

}