package com.pms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.ExpenseTypeInfo;

public interface ExpenseTypeRepository extends JpaRepository<ExpenseTypeInfo, UUID> {

	   

}
