package com.mahesh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mahesh.blog.entities.User;

public interface UserRepo extends JpaRepository<User,Integer> {

}
