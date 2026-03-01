package com.smartBankElite.accountService.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Account Service!";
    }

    @GetMapping("/unsecure/hello")
    public String Unsecurehello() {
        return "Hello from Account Service!";
    }


}
