package com.mukesh.telusko_prg1.controler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Homectrl {
    @RequestMapping("/")
    public String gteet(){
        return "hello";
    }
}
