package com.nantaaditya.example.springboot2.repository;

import com.nantaaditya.example.springboot2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * created by pramuditya.anantanur
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
