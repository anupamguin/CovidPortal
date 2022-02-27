package com.anupam.CovidPortal.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anupam.CovidPortal.model.ContactForm;
import com.anupam.CovidPortal.serviceImpl.FirstService;
import com.anupam.CovidPortal.serviceImpl.MapValidationErrorService;

@RestController
@RequestMapping("/first")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FirstController {

	@Autowired
	MapValidationErrorService mapValidationErrorService;

	@Autowired
	FirstService firstService;

	@GetMapping("/one")
	public String one() {
		return "Hi ANupam Guin";
	}

	@PostMapping("/contactForm")
	public ResponseEntity<?> contactForm(@Valid @RequestBody ContactForm contact, BindingResult bindingResult) {

		Map<String,Object> map=new HashMap<>();
		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<String,String>();
			
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println(errorMap);
			}
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.CONFLICT);
		}

		try {
			firstService.saveContactForm(contact);
			map.put("message", true);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);
		} catch (Exception e) {
			map.put("message", false);
			return ResponseEntity.status(HttpStatus.CHECKPOINT).body(map);
		}

	}
}
