package com.pms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.CustomerInfo;





	public interface CustomerRepository extends JpaRepository<CustomerInfo, UUID> {

		   

	}
