package com.anupam.CovidPortal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first")
public class FirstController {

	@GetMapping("/one")
	public String one() {
		return "Hi ANupam Guin";
	}
}
