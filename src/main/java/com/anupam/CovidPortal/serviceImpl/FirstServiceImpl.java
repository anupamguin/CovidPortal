package com.anupam.CovidPortal.serviceImpl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anupam.CovidPortal.dao.repository.FirstRepo;
import com.anupam.CovidPortal.model.ContactForm;

@Service
public class FirstServiceImpl implements FirstService{

	@Autowired
	FirstRepo firstRepo;
	
	@Override
	public ContactForm saveContactForm(ContactForm c) {
		c.setId(new Random().nextInt(999999999));
		firstRepo.save(c);
		return c;
	}

}
