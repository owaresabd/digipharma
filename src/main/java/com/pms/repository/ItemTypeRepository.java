package com.pms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.ItemTypeInfo;

public interface ItemTypeRepository extends JpaRepository<ItemTypeInfo, UUID> {

	   

}
