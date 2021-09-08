package com.pms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.TestInfo;

public interface TestRepository extends JpaRepository<TestInfo, UUID> {

	   

}
