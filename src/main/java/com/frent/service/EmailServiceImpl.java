package com.frent.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Created by Olga on 7/15/2016.
 */
@Service("emailService")
public class EmailServiceImpl implements EmailService {

	@Autowired
	public JavaMailSender mailSender;

	public void sendSimpleMessage(SimpleMailMessage message) {
		try {
			mailSender.send(message);
		} catch (MailException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void sendSimpleMessageUsingTemplate(SimpleMailMessage simpleMailMessage, String template, boolean isHTML) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(simpleMailMessage.getTo());
		helper.setSubject(simpleMailMessage.getSubject());
		helper.setText(template, isHTML);
		helper.setCc(simpleMailMessage.getCc());
		mailSender.send(message);
	}

	@Override
	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			// pass 'true' to the constructor to create a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);

			FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
			helper.addAttachment("Invoice", file);

			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
