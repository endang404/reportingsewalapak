package com.web.reporting.model.api.request;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import com.web.reporting.model.TransactionModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiTransactionRequest {
//	@NotEmpty(message = "Field batchId (Batch ID) cannot be empty or null")
//	@Size(max = 16, message = "Field batchId (Batch ID) exceed allowed length (16)")
	private String batchId;

//	@NotEmpty(message = "Field totalData (Total Data) cannot be empty or null")
//	@Size(max = 6, message = "Field totalData (Total Data) exceed allowed length (6)")
	private String totalData;
	
//	@NotEmpty(message = "Field data (Data) cannot be empty or null")
	private List<TransactionModel> data;
}
