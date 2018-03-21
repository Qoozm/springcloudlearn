package com.qiuzeming.serviceribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by qiuzeming on 2018/3/20.
 */
@Service
public class HiService {

    private final RestTemplate restTemplate;

    @Autowired
    public HiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "hiError")
    public String sayHi(String name) {
        return restTemplate.getForObject("http://service-hi/hi?name=" + name, String.class);
    }

    public String hiError(String name) {
        return "hi, " + name + " , sorry, error!";
    }
}