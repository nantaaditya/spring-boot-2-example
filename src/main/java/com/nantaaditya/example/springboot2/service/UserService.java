package com.nantaaditya.example.springboot2.service;

import com.nantaaditya.example.springboot2.entity.User;
import com.nantaaditya.example.springboot2.exception.AlreadyExistException;
import com.nantaaditya.example.springboot2.exception.NotFoundException;
import com.nantaaditya.example.springboot2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
  
  public User create(User user) throws AlreadyExistException {
    Optional<User> userOptional = userRepository.findById(user.getId());
    if (userOptional.isPresent()) {
      throw new AlreadyExistException(String.format("user with id %s already exist", user.getId()));
    }
    return userRepository.save(user);
  }
  
  public Optional<User> findById(Long id) throws NotFoundException {
    Optional<User> userOptional = userRepository.findById(id);
    if (!userOptional.isPresent()) {
      throw new NotFoundException(String.format("user with id %s not found", id));
    }
    return userOptional;
  }
  
  public void delete(Long id) throws NotFoundException {
    Optional<User> userOptional = userRepository.findById(id);
    if (!userOptional.isPresent()) {
      throw new NotFoundException(String.format("user with id %s not found", id));
    }
    userRepository.deleteById(id);  
  }
  
  public User update(Long id, User user) throws NotFoundException {
    Optional<User> userOptional = userRepository.findById(id);
    if (!userOptional.isPresent()) {
      throw new NotFoundException(String.format("user with id %s not found", id));
    }
    user.setId(id);
    return userRepository.save(user);
  }
}
