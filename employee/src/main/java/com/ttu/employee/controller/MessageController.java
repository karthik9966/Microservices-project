package com.ttu.employee.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RefreshScope
@RequestMapping("/app/employee/message")
public class MessageController {

    @Value("${message}")
    private String message;

    @GetMapping
    public String getMessage()
    {
        return message;
    }
}
