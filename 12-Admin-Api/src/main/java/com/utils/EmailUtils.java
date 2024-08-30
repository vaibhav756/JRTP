package com.utils;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {

	private Logger logger=LoggerFactory.getLogger(EmailUtils.class);

	@Autowired
	private JavaMailSender mailsender;
	
	public boolean sendEmail(String subject,String body,String toAddress,File file)
	{
		boolean result=false;
		try
		{
			MimeMessage mimeMessage=mailsender.createMimeMessage();
			
			MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
			
			helper.setSubject(subject);
			
			helper.setText(body, true);
			
			helper.setTo(toAddress);
			
			mailsender.send(mimeMessage);
			
			result=true;
			
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error("Error occured inside sendPwd method of EmailUtils method for Address :  "+toAddress,e);
		}
		return result;
	}
	
}
