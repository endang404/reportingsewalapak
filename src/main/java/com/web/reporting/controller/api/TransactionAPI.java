package com.web.reporting.controller.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.reporting.model.TransactionModel;
import com.web.reporting.model.api.request.ApiTransactionRequest;
import com.web.reporting.model.api.response.ApiGeneralResponse;
import com.web.reporting.service.impl.TransactionServiceImpl;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionAPI {
	
	private final TransactionServiceImpl transactionServiceImpl;
	private final String fileDirectory = "noted/absolut/ke/direktori/file";
	
	@GetMapping("/getList")
	public List<TransactionModel> getList(){
		List<TransactionModel> data = transactionServiceImpl.getListAll();
		return data;
	}
	
	@GetMapping("/getData/{no}")
	public TransactionModel getData(@PathVariable(name = "no") String no) {
		TransactionModel data = transactionServiceImpl.getDataByNoTransaction(no);
		return data;
	}
	
	@GetMapping("/sample/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            // Membaca file dari direktori "static"
            Resource resource = new ClassPathResource("static/noted/" + fileName);

            if (resource.exists() && resource.isReadable()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
                return ResponseEntity.ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
	 
	@PostMapping("/upload")
	public ResponseEntity<ApiGeneralResponse> upload(@RequestBody ApiTransactionRequest apiTransactionRequest, HttpServletRequest request){
		Map<Object, Object> mapData = new HashMap<>();
				
		mapData.put("payment_upload", transactionServiceImpl.insertData(apiTransactionRequest.getData()));
		
		return ResponseEntity.ok(ApiGeneralResponse.builder().path(request.getRequestURI())
				.method(request.getMethod())
				.timestamp(LocalDateTime.now())
				.data(mapData)
				.message("Transaction Uploaded")
				.status(HttpStatus.CREATED)
				.statusCode(HttpStatus.CREATED.value()).build());
	}
	
	@DeleteMapping("/clear")
	public String clearData() {
			transactionServiceImpl.deleteAll();
		return "00";
	}
}
