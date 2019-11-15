package com.nantaaditya.example.springboot2.exception;

/**
 * created by pramuditya.anantanur
 **/
public class AlreadyExistException extends Exception {
  public AlreadyExistException(String message) {
    super(message);
  }
}
