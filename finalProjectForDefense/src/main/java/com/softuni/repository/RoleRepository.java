package com.softuni.repository;

import com.softuni.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
  //  Optional<Role> findByName(String name);
    Role findByAuthority(String auth);

}
