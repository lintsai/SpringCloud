package com.example.zipkinserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;
import zipkin2.server.internal.EnableZipkinServer;

@EnableEurekaClient
@EnableZipkinServer
@SpringBootApplication
@EnableScheduling
public class ZipkinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApplication.class, args);
    }

    @Controller
    public class WebController {
        @Value("${zipkin.server.protocol:http}")
        private String protocol;
        @Value("${zipkin.server.host:127.0.0.1}")
        private String host;
        @Value("${zipkin.server.port:9411}")
        private String port;
        @GetMapping("/")
        public RedirectView root(){
            return new RedirectView(protocol+"://"+host+":"+port);
        }
    }
}
