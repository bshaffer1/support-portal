package com.palaceflophouse.supportportal.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * Author: Brandon Shaffer
 * Date: 7/23/2022
 */
@Service
@Slf4j
public class EmailService implements EmailSender{

	private final JavaMailSender mailSender;

	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void send(String to, String details) {
		try{
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setText(details);
			helper.setTo(to);
			helper.setSubject("Forgot Password Reset");
			helper.setFrom("support@supportportal.com");

			mailSender.send(mimeMessage);
		} catch (Exception e){
			log.error("Failed to send email", e);
		}
	}
}
