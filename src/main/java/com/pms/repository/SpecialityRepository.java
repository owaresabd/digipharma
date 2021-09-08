package com.pms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.SpecialityInfo;

public interface SpecialityRepository extends JpaRepository<SpecialityInfo, UUID> {

	   

}
