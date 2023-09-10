package com.web.reporting.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.web.reporting.service.impl.TransactionServiceImpl;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final TransactionServiceImpl transactionServiceImpl;

	@RequestMapping("/")
	public String main() {
		return "layout/main";
	}

	@RequestMapping("/transaction")
	public String transaction() {
		return "transaction";
	}

	@RequestMapping("/formUpload")
	public String formUpload() {
		return "form_upload";
	}
	
	@RequestMapping("/rule")
	public String rule() {
		return "noted";
	}
	
	@RequestMapping("/dashboard")
	public String dashboard() {
		return "dashboard";
	}
	
	@PostMapping("/upload")
	public ResponseEntity<?> handleFileUpload(@RequestParam(name = "file") MultipartFile file, HttpSession session,
			HttpServletRequest httpRequest) {
		String response="";
		try {
			response = transactionServiceImpl.upload(file, session, httpRequest);
		} catch (Exception e) {
			response = e.getMessage();
		}
		return ResponseEntity.ok(response);
	}



}
