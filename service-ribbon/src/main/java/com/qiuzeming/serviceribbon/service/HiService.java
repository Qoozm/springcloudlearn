package com.qiuzeming.serviceribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
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

    public String sayHi(String name) {
        return restTemplate.getForObject("http://service-hi/hi?name=" + name, String.class);
    }
}