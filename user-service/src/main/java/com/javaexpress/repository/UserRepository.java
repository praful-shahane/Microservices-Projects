package com.javaexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
