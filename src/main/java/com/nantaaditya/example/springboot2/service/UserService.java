package com.nantaaditya.example.springboot2.service;

import com.nantaaditya.example.springboot2.entity.User;
import com.nantaaditya.example.springboot2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * created by pramuditya.anantanur
 **/
@Service
public class UserService {
  
  @Autowired
  private UserRepository userRepository;
  
  public List<User> findAll() {
    return userRepository.findAll();
  }
  
  public User create(User user) {
    return userRepository.save(user);
  }
  
  public Optional<User> findById(Long id) {
    return userRepository.findById(id);
  }
  
  public void delete(Long id) {
    if (userRepository.findById(id).isPresent()) 
      userRepository.deleteById(id);
  }
  
  public User update(Long id, User user) {
    user.setId(id);
    return userRepository.save(user);
  }
}
