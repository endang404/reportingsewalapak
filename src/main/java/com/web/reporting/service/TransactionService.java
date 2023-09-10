package com.web.reporting.service;

import java.util.List;

import com.web.reporting.model.TransactionModel;

public interface TransactionService {

	List<TransactionModel> getList();
	List<TransactionModel> getListAll();
	TransactionModel create(TransactionModel transactionModel);
	TransactionModel getDataByNoTransaction(String noTransaction);
	void deleteAll();
}
