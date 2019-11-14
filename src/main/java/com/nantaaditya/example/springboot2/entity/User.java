package com.nantaaditya.example.springboot2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * created by pramuditya.anantanur
 **/
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
