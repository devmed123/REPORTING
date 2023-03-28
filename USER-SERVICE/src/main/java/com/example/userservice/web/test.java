package com.example.userservice.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController

public class test {
    @GetMapping("/GET_USER")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String TestMethod(){
        return  "aa";
    }


}
