package com.mb.demo.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mb.demo.persistance.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
