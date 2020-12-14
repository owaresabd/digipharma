package com.pms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.DepartmentInfo;


public interface DepartmentRepository extends JpaRepository<DepartmentInfo, UUID> {

   

}
