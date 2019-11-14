# Spring Boot 2 Example Documentation

## Table of Content
- Basic Setup
- Basic Controller
- Basic Entity, JPA, Service


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
