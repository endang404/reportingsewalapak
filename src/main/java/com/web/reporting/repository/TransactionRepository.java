package com.web.reporting.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.web.reporting.model.TransactionModel;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, String>{
	
	@Query("select u from TransactionModel u")
	List<TransactionModel> getList();
	
	@Query("select u from TransactionModel u where u.noTransaction=?1")
	TransactionModel getDataByNoTransaction(String noTransaction);

	void deleteAll();
}
