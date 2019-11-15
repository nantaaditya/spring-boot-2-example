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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
  @Min(value = 1)
  private Long id;
  @Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
  @NotNull
  private String username;
  @Column(name = "FIRST_NAME", nullable = false)
  @NotNull
  private String firstname;
  @Column(name = "LAST_NAME", nullable = false)
  @NotNull
  private String lastname;
  @Column(name = "EMAIL_ADDRESS", nullable = false)
  @NotNull
  private String email;
  @Column(name = "ROLE", nullable = false)
  @NotNull
  private String role;
  @Column(name = "SSN", nullable = false)
  @NotNull
  private String ssn;
}
