package com.anupam.CovidPortal.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anupam.CovidPortal.model.LoginRequestModel;
import com.anupam.CovidPortal.model.LoginResponseModel;
import com.anupam.CovidPortal.model.OtpResponseModel;
import com.anupam.CovidPortal.model.RegisterModel;
import com.anupam.CovidPortal.security.JwtUtil;
import com.anupam.CovidPortal.serviceImpl.MapValidationErrorService;
import com.anupam.CovidPortal.serviceImpl.UserService;
import com.anupam.CovidPortal.validator.UserValidator;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserValidator userValidator;
	
	@Autowired
	MapValidationErrorService mapValidationErrorService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequestModel loginModel,BindingResult bindingResult){
		System.out.println(loginModel);
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(bindingResult);
		if(errorMap != null) return errorMap;
		
		try {
		authenticationManager
		.authenticate(new UsernamePasswordAuthenticationToken(loginModel.getUsername(), loginModel.getPassword()));
		}catch (Exception e) {		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponseModel(false));
		}
		return ResponseEntity.ok(new LoginResponseModel(true,jwtUtil.generateToken(loginModel.getUsername())));
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
			int otp = (int)(double) (Math.random() * 1000000);
			
			String header="Covid Portal OTP from Anupam";
			
			String body= "Hi,"+registerModel.getName()+"\n Your Covid Portal OTP is : "+otp+
			"\n Thanks For Your Registration on this Portal Dear "+registerModel.getName()+
			". We think that You will be Stay at your Home. \n Yours Faithfully, Anupam Guin ( FullStack Software Engineer )";
			
			boolean b = userService.sendOtpEmail(registerModel.getEmail(), body, header);
			
			int key = (int) (double) (Math.random() * 1000000000);
			
			registerModel.setId(key);
			registerModel.setOtp(otp);
			userService.saveUser(registerModel);	
			
			map.put("success", b);
			map.put("id",key);
			return ResponseEntity.ok(map);
		}catch(Exception e) {
			map.put("success", false);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
		}	    		
	}
	
	@GetMapping("/otp")
	public ResponseEntity<?> otp(@RequestParam(name = "id",defaultValue="0") int id, @RequestParam(name = "otp",defaultValue ="0") int otp){
		
		OtpResponseModel orm;
		try {
			 orm =userService.checkOtp(id,otp);
			 
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(orm);
	}
}
