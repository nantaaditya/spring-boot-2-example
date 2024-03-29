# Spring Boot 2 Example Documentation

## Table of Content
- Basic Setup
- Basic Controller
- Basic Entity, JPA, Service
- REST Global Exception Handler


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

### Basic Entity, JPA, Service
- application.properties
```properties
spring.jpa.show-sql=true
spring.h2.console.enabled=true
```
- data.sql
```h2
insert into user values(101, 'kreddy@stacksimplify.com', 'Kalyan', 'Reddy', 'admin', 'ssn101', 'kreddy');
insert into user values(102, 'gwiser@stacksimplify.com', 'Greg', 'Wiser', 'admin', 'ssn102', 'gwiser');
insert into user values(103, 'dmark@stacksimplify.com', 'David', 'Mark', 'admin', 'ssn103', 'dmark');   
```
- H2 Console
```markdown
    - H2 Console URL: localhost:8080/h2-console
    - JDBC URL: jdbc:h2:mem:testdb
```
- Entity
```java
@Table
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue
  private Long id;
  @Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
  private String username;
  @Column(name = "FIRST_NAME", nullable = false)
  private String firstname;
  @Column(name = "LAST_NAME", nullable = false)
  private String lastname;
  @Column(name = "EMAIL_ADDRESS", nullable = false)
  private String email;
  @Column(name = "ROLE", nullable = false)
  private String role;
  @Column(name = "SSN", nullable = false)
  private String ssn;
}
```

- JPA Repository
```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
```

- Service
```java
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
```

### REST Global Exception Handler
- Global Controller Advice
```java
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
```
