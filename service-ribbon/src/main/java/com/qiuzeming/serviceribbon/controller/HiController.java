package com.qiuzeming.serviceribbon.controller;

import com.qiuzeming.serviceribbon.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qiuzeming on 2018/3/20.
 */
@RestController
public class HiController {

    private final HiService hiService;

    @Autowired
    public HiController(HiService hiService) {
        this.hiService = hiService;
    }

    @GetMapping("/hi")
    public String hiController(@RequestParam String name) {
        return hiService.sayHi(name);
    }
}