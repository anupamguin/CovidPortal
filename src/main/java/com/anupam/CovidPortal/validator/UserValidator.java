package com.anupam.CovidPortal.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.anupam.CovidPortal.model.RegisterModel;

@Component
public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterModel.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		RegisterModel user=(RegisterModel) object;
		
		if(user.getPassword().length() < 6) {
			errors.rejectValue("password","Length","Password must be at least 6 Characters");
		}
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword","Match","Password must be match");
		}
	}
 
}
