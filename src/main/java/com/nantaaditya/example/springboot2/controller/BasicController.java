package com.nantaaditya.example.springboot2.controller;

import com.nantaaditya.example.springboot2.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by pramuditya.anantanur
 **/
@RestController
@RequestMapping(value = "/basic")
public class BasicController {
  
  @GetMapping(value = "/hello-world")
  public String helloWorld() {
    return "Hello world";
  }
  
  @GetMapping(value = "/user")
  public User user() {
    return new User("first", "last", "city");
  }
}
