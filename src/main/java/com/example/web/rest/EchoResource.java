package com.example.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = { "/api", "/api/ext" })
public class EchoResource {

    @GetMapping("/echo")
    public String echo() {
        return "success";
    }
}
