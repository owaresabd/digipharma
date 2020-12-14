package com.pms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.DesignationInfo;



    
public interface DesignationRepository extends JpaRepository<DesignationInfo, UUID> {

   

}