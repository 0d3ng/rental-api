package com.frent.service;

import org.springframework.mail.SimpleMailMessage;

/**
 * Created by Olga on 8/22/2016.
 */
public interface EmailService {
	void sendSimpleMessage(SimpleMailMessage message);

	void sendSimpleMessageUsingTemplate(SimpleMailMessage simpleMailMessage, String template, boolean isHTML) throws Exception;

	void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);
}
