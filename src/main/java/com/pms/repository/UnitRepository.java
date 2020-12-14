package com.pms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.UnitInfo;



    
public interface UnitRepository extends JpaRepository<UnitInfo, UUID> {

   

}