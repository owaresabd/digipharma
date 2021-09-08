package com.pms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.DoctorInfo;

public interface DoctorRepository extends JpaRepository<DoctorInfo, UUID> {

	   

}
