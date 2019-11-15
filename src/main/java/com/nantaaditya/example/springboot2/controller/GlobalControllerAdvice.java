package com.nantaaditya.example.springboot2.controller;

import com.nantaaditya.example.springboot2.exception.AlreadyExistException;
import com.nantaaditya.example.springboot2.exception.ErrorHelper;
import com.nantaaditya.example.springboot2.exception.GlobalError;
import com.nantaaditya.example.springboot2.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * created by pramuditya.anantanur
 **/
@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {
  
  private static final String ERROR_MESSAGE_FORMAT = "Error on {} caused by {}";
  private static final String ERROR_DETAIL_MESSAGE_FORMAT = "Error on {} caused by {} with errors {}";
  private static final String BAD_REQUEST_MESSAGE = "request not valid";
  
  @Autowired
  private MessageSource messageSource;
  
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(AlreadyExistException.class)
  public ResponseEntity<GlobalError> alreadyExist(AlreadyExistException ex) {
    log.error(ERROR_MESSAGE_FORMAT, ex.getClass().getName(), ex.getMessage());
    GlobalError globalError = GlobalError.builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .message(ex.getMessage())
        .build();
    return new ResponseEntity<GlobalError>(globalError, HttpStatus.BAD_REQUEST);
  }
  
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<GlobalError> notFound(NotFoundException ex) {
    log.error(ERROR_MESSAGE_FORMAT, ex.getClass().getName(), ex.getMessage());
    GlobalError globalError = GlobalError.builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .message(ex.getMessage())
        .build();
    return new ResponseEntity<GlobalError>(globalError, HttpStatus.BAD_REQUEST);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<GlobalError> methodArgumentNotValid(MethodArgumentNotValidException ex) {
    log.error(ERROR_DETAIL_MESSAGE_FORMAT, ex.getClass().getName(), ex.getMessage(), ErrorHelper.from(ex.getBindingResult(), messageSource));
    GlobalError globalError = GlobalError.builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .message(BAD_REQUEST_MESSAGE)
        .errors(ErrorHelper.from(ex.getBindingResult(), messageSource))
        .build();
    return new ResponseEntity<GlobalError>(globalError, HttpStatus.BAD_REQUEST);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BindException.class)
  public ResponseEntity<GlobalError> bindException(BindException ex) {
    log.error(ERROR_DETAIL_MESSAGE_FORMAT, ex.getClass().getName(), ex.getMessage(), ErrorHelper.from(ex.getBindingResult(), messageSource));
    GlobalError globalError = GlobalError.builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .message(BAD_REQUEST_MESSAGE)
        .errors(ErrorHelper.from(ex.getBindingResult(), messageSource))
        .build();
    return new ResponseEntity<GlobalError>(globalError, HttpStatus.BAD_REQUEST);
  }
}
