package com.anupam.CovidPortal.serviceImpl;

import com.anupam.CovidPortal.model.OtpResponseModel;
import com.anupam.CovidPortal.model.RegisterModel;

public interface UserService {

	public boolean sendOtpEmail(String mailId, String body, String header);

	void saveUser(RegisterModel registerModel);

	public OtpResponseModel checkOtp(int id, int otp);

}
