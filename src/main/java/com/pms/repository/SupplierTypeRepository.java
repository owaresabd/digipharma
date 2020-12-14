package com.pms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.SupplierTypeInfo;

public interface SupplierTypeRepository extends JpaRepository<SupplierTypeInfo, UUID> {

	   

}
