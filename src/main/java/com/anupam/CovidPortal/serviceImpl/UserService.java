package com.anupam.CovidPortal.serviceImpl;

import com.anupam.CovidPortal.model.RegisterModel;

public interface UserService {

	void sendOtpEmail(String mailId, String body, String header);

	void saveUser(RegisterModel registerModel);

}
