package com.pms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.SupplierInfo;




	public interface SupplierRepository extends JpaRepository<SupplierInfo, UUID> {

		   

	}
