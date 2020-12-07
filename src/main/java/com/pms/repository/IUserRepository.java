package com.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pms.model.User;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

	User findByUserName(String userName);
}