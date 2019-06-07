package com.cperbony.springoauth2.repository;

import com.cperbony.springoauth2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
