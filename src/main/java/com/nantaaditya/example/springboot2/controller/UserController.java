package com.nantaaditya.example.springboot2.controller;

import com.nantaaditya.example.springboot2.entity.User;
import com.nantaaditya.example.springboot2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * created by pramuditya.anantanur
 **/
@RestController
@RequestMapping(value = "/users")
public class UserController {
  
  @Autowired
  private UserService userService;
  
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  public User save(@RequestBody User user) {
    return userService.create(user);
  }
  
  @GetMapping(value = "/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public Optional<User> finById(@PathVariable Long id) {
    return userService.findById(id);
  }
  
  @DeleteMapping
  public void delete(Long id) {
    userService.delete(id);
  }
  
  @PutMapping(value = "/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  private User update(@PathVariable Long id, @RequestBody User user) {
    return userService.update(id, user);
  }
  
  @GetMapping(
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public List<User> findAll() {
    return userService.findAll();
  }
}
