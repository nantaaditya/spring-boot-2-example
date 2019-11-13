# Spring Boot 2 Example Documentation

## Table of Content
- Basic Setup
- Basic Controller


### Basic Setup
- Initialize Spring Boot 2 Project
    - Spring Data JPA
    - Spring Web
    - Spring Boot DevTools
    - Lombok

### Basic Controller
- String Controller
```java
  @GetMapping(value = "/hello-world")
  public String helloWorld() {
    return "Hello world";
  }
```
- User Controller
```java
  @GetMapping(value = "/user")
  public User user() {
    return new User("first", "last", "city");
  }
```
