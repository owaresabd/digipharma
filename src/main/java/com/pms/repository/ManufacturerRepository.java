package com.pms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.ManufacturerInfo;



	public interface ManufacturerRepository extends JpaRepository<ManufacturerInfo, UUID> {

		   

	}