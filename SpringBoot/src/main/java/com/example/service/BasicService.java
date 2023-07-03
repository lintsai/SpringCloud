package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BasicService {
    @Value("${version}")
    private String version;
    public String version(){
        return version;
    }
}
