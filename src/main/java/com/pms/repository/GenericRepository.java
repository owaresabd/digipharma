package com.pms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.GenericInfo;



    
public interface GenericRepository extends JpaRepository<GenericInfo, UUID> {

   

}