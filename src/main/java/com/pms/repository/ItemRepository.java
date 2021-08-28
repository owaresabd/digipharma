package com.pms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.ItemInfo;

public interface ItemRepository extends JpaRepository<ItemInfo, UUID> {

	   

}
