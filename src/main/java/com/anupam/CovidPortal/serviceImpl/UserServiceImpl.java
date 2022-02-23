package com.anupam.CovidPortal.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.anupam.CovidPortal.UserRepo;
import com.anupam.CovidPortal.model.RegisterModel;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	UserRepo userRepo;

	@Override
	public void sendOtpEmail(String mailId, String body, String header) {

		   System.err.println("Mail Start Sending .....");
		   SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		   simpleMailMessage.setFrom("anupamguin7585@gmail.com");
		   simpleMailMessage.setTo(mailId);
		   simpleMailMessage.setSubject(header);
		   simpleMailMessage.setText(body);   
		   simpleMailMessage.setSentDate(new Date());
		   javaMailSender.send(simpleMailMessage);
		   System.err.println("Mail Sending Successfully.....");

		
//		try {
//			JavaMailSenderImpl sender = new JavaMailSenderImpl();
//			sender.setHost("anupamguin7585@gmail.com");
//			MimeMessage mimeMessage = sender.createMimeMessage();
//			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//			FileSystemResource file = new FileSystemResource(new File("/Users/user/Anupam/photos/Web Series/1.jpg"));
//
////		   helper.setFrom("anupamguin7585@gmail.com");
//			helper.setTo(mailId);
//			helper.setSubject(header);
//			helper.setText(body);
//			helper.setSentDate(new Date());
//			helper.addAttachment("1.jpg", file);
//			sender.send(mimeMessage);
//
//			System.err.println("Sending complete");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

	@Override
	public void saveUser(RegisterModel registerModel) {

//		registerModel.setPassword();
		int key = (int) (double) (Math.random() * 100000000);
		System.err.println(key);
		registerModel.setId(key);
		userRepo.save(registerModel);

	}

}
