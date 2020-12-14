package com.pms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.CustomerTypeInfo;

public interface CustomerTypeRepository extends JpaRepository<CustomerTypeInfo, UUID> {

	   

}
