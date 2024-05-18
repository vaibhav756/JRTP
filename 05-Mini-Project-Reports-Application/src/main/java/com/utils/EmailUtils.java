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

	private Logger log=LoggerFactory.getLogger(EmailUtils.class);
	
	//Clients should talk to the mail sender through this interface if they need mail functionality beyond
	@Autowired
	private JavaMailSender mailsender;  //Mailsender available in java
	
	public boolean sendEmail(String subject,String body,String toAddress,File file)
	{
		try
		{
			log.info("Inside sendEmail method of EmailUtils class.");
			//To go with attachment we use MimeMesssage impl class.If we want to send simple msg we go with SimpleMessage impl class.
			MimeMessage mimemsg = mailsender.createMimeMessage();
			
			//Offers support to the MimeMessage class to add details and attachments.
			MimeMessageHelper helper=new MimeMessageHelper(mimemsg,true);  //True we need to pass for sending attachment file.
			
			//Setting email subject
			helper.setSubject(subject);

			//Setting body of the email
			//If our body contains HTML tag then setText(body,true) else false
			helper.setText(body,true);
			
			//Setting whom to send this message
			helper.setTo(toAddress);
			
			helper.addAttachment("CitizenPlanInfo", file);
			
			//For simple message go with simpleMessage for Attachment go with MimeMessage
			//mailsender.send(mimemsg);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			log.error("Error occured while sending mail to "+toAddress,e);
		}
		return true;
	}
	
}
