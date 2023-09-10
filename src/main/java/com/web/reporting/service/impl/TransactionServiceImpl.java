package com.web.reporting.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.reporting.model.TransactionModel;
import com.web.reporting.repository.TransactionRepository;
import com.web.reporting.service.TransactionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
	private int[] transaction_field = { 15, 11, 19, 11, 11, 11, 11, 11, 11, 11, 10 };

	@Autowired
	private RestTemplate restTemplate;

	private final TransactionRepository transactionRepository;

	@Override
	public List<TransactionModel> getList() {
		// TODO Auto-generated method stub
		return transactionRepository.getList();
	}

	@Override
	public TransactionModel create(TransactionModel transactionModel) {
		// TODO Auto-generated method stub
		return transactionRepository.save(transactionModel);
	}
	
	@Override
	public List<TransactionModel> getListAll() {
		// TODO Auto-generated method stub
		return transactionRepository.findAll();
	}

	@Override
	public TransactionModel getDataByNoTransaction(String noTransaction) {
		// TODO Auto-generated method stub
		return transactionRepository.getDataByNoTransaction(noTransaction);
	}
	
	public List<TransactionModel> insertData(List<TransactionModel> transactionModels) {
		
		try {
			if(transactionModels.size()>0) {
				for (TransactionModel data : transactionModels) {
					TransactionModel model = new TransactionModel();
					model.setNoTransaction(data.getNoTransaction());
					model.setMerchantId(data.getMerchantId());
					model.setTransactionTime(data.getTransactionTime());
					model.setStaffId(data.getStaffId());
					model.setPayAmount(data.getPayAmount());
					model.setPaymentId(data.getPaymentId());
					model.setCustomerId(data.getCustomerId());
					model.setTax(data.getTax());
					model.setChangeAmount(data.getChangeAmount());
					model.setTotalAmount(data.getTotalAmount());
					model.setPaymentStatus(data.getPaymentStatus());
					
					transactionRepository.save(model);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return transactionModels;
	}

	public String upload(MultipartFile file, HttpSession session,	HttpServletRequest httpRequest) {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		Date date = new Date();
		String generateBatchId = dateFormat.format(date);
		long totalData = 0;
		String message = "";
		int nomor = 0;
		String[] contentType = { "", "", "", "", "", "" };
		String[] filename = new String[6];
		String path = "/transaction/";
		boolean patch_folder = new File(httpRequest.getServletContext().getRealPath(path)).mkdir();
		String dir = httpRequest.getServletContext().getRealPath(path + generateBatchId);

		File f = new File(dir);
		f.mkdir();
		String no_transaction, merchant_id, staff_id, pay_amount, payment_id, customer_id, tax,
				change_amount, total_amount, transaction_time, payment_status;
		String sparator = File.separator;
		String fileName = file.getOriginalFilename();
		try {
			filename[nomor] = dir + sparator + fileName;
			contentType[nomor] = file.getContentType();
			if (fileName != null && fileName.length() > 0) {
				file.transferTo(new File(f.getPath() + sparator + fileName));
			}
		} catch (Exception e) {
			message = "Upload Gagal";
		}

		try (BufferedReader br = new BufferedReader(new FileReader(f.getPath() + sparator + fileName), 8192)) {
			ObjectMapper mapper = new ObjectMapper();
			JSONObject payment = new JSONObject();
			JSONArray data = new JSONArray();
			String line;

			int total = 0;
			int row = 0 ;
			while ((line = br.readLine()) != null) {
				JSONObject request = new JSONObject();
				int start = 0;
				int end = transaction_field[0];

				if (line.length() == 132) {
					no_transaction = line.substring(start, end).trim();
					start += transaction_field[0];
					end += transaction_field[1];

					merchant_id = line.substring(start, end).trim();
					start += transaction_field[1];
					end += transaction_field[2];

					transaction_time = line.substring(start, end).trim();
					start += transaction_field[2];
					end += transaction_field[3];

					staff_id = line.substring(start, end).trim();
					start += transaction_field[3];
					end += transaction_field[4];

					pay_amount = line.substring(start, end).trim();
					start += transaction_field[4];
					end += transaction_field[5];

					payment_id = line.substring(start, end).trim();
					start += transaction_field[5];
					end += transaction_field[6];

					customer_id = line.substring(start, end).trim();
					start += transaction_field[6];
					end += transaction_field[7];

					tax = line.substring(start, end).trim();
					start += transaction_field[7];
					end += transaction_field[8];

					change_amount = line.substring(start, end).trim();
					start += transaction_field[8];
					end += transaction_field[9];

					total_amount = line.substring(start, end).trim();
					start += transaction_field[9];
					end += transaction_field[10];

					payment_status = line.substring(start, end).trim();

					request.put("noTransaction", no_transaction);
					request.put("merchantId", merchant_id);
					request.put("transactionTime", transaction_time);
					request.put("staffId", staff_id);
					request.put("payAmount", pay_amount);
					request.put("paymentId", payment_id);
					request.put("customerId", customer_id);
					request.put("tax", tax);
					request.put("changeAmount", change_amount);
					request.put("totalAmount", total_amount);
					request.put("paymentStatus", payment_status);
				}else {
					row++;
				}
				data.add(request);
				total++;
			}

		if(row>0) {
			message = "Staging Gagal : Panjang data tidak sesuai ketentuan";	
		}else {
			payment.put("batchId", generateBatchId);
			payment.put("totalData", total);
			payment.put("data", data);
			totalData = total;

			String jsonString = "";
			try {
				jsonString = mapper.writeValueAsString(payment);
//				System.out.println("Check JSON String "+jsonString);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			HttpHeaders headers = new HttpHeaders();
			HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(payment, headers);
			String url = "";
			String root = "";
			String fullPath = httpRequest.getRequestURL().toString();
			root = fullPath.replace(httpRequest.getServletPath(), "");

			url = root+"/api/transaction/upload";
			ResponseEntity<JSONObject> result = restTemplate.exchange(url, HttpMethod.POST, entity, JSONObject.class);
			String status = result.getBody().get("status").toString();
			message = status;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
		return message;
	}

	@Override
	public void deleteAll() {
		transactionRepository.deleteAll();
		
	}
}
