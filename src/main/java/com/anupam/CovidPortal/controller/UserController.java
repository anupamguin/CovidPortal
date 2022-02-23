package com.anupam.CovidPortal.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anupam.CovidPortal.model.RegisterModel;
import com.anupam.CovidPortal.serviceImpl.MapValidationErrorService;
import com.anupam.CovidPortal.serviceImpl.UserService;
import com.anupam.CovidPortal.validator.UserValidator;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserValidator userValidator;
	
	@Autowired
	MapValidationErrorService mapValidationErrorService;
	
	@GetMapping("/login")
	public ResponseEntity<?> login(){
		return null;
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterModel registerModel, BindingResult bindingResult){
		// Validate passwords match
		userValidator.validate(registerModel, bindingResult);
		
		System.out.println(registerModel);
		
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(bindingResult);
		if(errorMap != null) return errorMap;
		
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			userService.saveUser(registerModel);
			
			int otp = (int)(double) (Math.random() * 1000000);
			
			String header="Covid Portal OTP from Anupam";
			
			String body= "Hi,"+registerModel.getName()+"\n Your Covid Portal OTP is : "+otp+
			"\n Thanks For Your Registration on this Portal Dear "+registerModel.getName()+
			". We think that You will be Stay at "+registerModel.getAddress()+". \n Yours Faithfully, Anupam Guin ( FullStack Software Engineer )";
			
			userService.sendOtpEmail(registerModel.getEmail(), body, header);
		}catch(Exception e) {
			map.put("success", false);
		}
		    map.put("success", true);
		return ResponseEntity.ok(map);
	}
	
	@GetMapping("/otp")
	public String otp(){
		int otp = (int)(double) (Math.random() * 1000000);
		System.out.println(otp);
		try {
		userService.sendOtpEmail("programmingboy7585@gmail.com", "Hi, Programming boy ,Your OTP is "+otp,
				"Covid Portal OTP from Anupam");
		}catch(Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return null;
	}
}
