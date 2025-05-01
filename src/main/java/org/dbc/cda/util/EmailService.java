package org.dbc.cda.util;

import org.dbc.cda.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender jms;

	public void registeredEmail(User user) {
	    MimeMessage message = jms.createMimeMessage();

	    try {
	        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
	        helper.setTo(user.getEmail());
	        helper.setSubject("🎉 CDA Account Created Successfully");

	        String htmlContent = "<!DOCTYPE html>"
	                + "<html lang='en'>"
	                + "<head>"
	                + "<meta charset='UTF-8'>"
	                + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
	                + "</head>"
	                + "<body style='font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;'>"
	                + "<div style='max-width: 600px; margin: auto; background-color: #ffffff; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); overflow: hidden;'>"
	                + "<div style='background-color: #4CAF50; color: white; padding: 20px; text-align: center;'>"
	                + "<h2>Welcome to CDA 🎓</h2>"
	                + "</div>"
	                + "<div style='padding: 30px; color: #333;'>"
	                + "<p>Dear <strong>" + user.getUsername() + "</strong>,</p>"
	                + "<p>Your <strong>College Directory Application (CDA)</strong> account has been successfully created.</p>"
	                + "<p>To get started, please activate your account by using the OTP below:</p>"
	                + "<div style='text-align: center; margin: 30px 0;'>"
	                + "<span style='font-size: 24px; font-weight: bold; color: #4CAF50;'>" + user.getOtp() + "</span>"
	                + "</div>"
	                + "<p>Once activated, you can view college updates, information, and much more. Don't forget to set up your profile!</p>"
	                + "<p style='margin-top: 40px;'>Happy Learning! 🌟✌️<br><em>- Tencent Team</em></p>"
	                + "</div>"
	                + "</div>"
	                + "</body>"
	                + "</html>";

	        helper.setText(htmlContent, true);
	        jms.send(message);
	    } catch (MessagingException e) {
	        e.printStackTrace();
	    }
	}

}
