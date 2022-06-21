package com.mb.demo.persistance.repository;

import org.springframework.stereotype.Repository;

import com.mb.demo.persistance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
