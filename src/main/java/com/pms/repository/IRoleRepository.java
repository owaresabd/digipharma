package com.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pms.model.Role;

@Repository("roleRepository")
public interface IRoleRepository extends JpaRepository<Role, Integer> {
	Role findByRole(String role);

}