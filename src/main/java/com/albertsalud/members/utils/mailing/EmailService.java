package com.albertsalud.members.utils.mailing;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.albertsalud.members.model.entities.Member;
import com.albertsalud.members.model.services.result.ResultBean;

import lombok.Data;

@Component
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private MailingGenerator mailingGenerator;
	
	private void sendMessage(MimeMessage message, Member member) throws MessagingException {
		
		MimeMessageHelper helper = new MimeMessageHelper(message, false);
		helper.setFrom("daudecinc@gmail.com");
		helper.setTo(member.getEmail());
		
		emailSender.send(message);
	}

	public void sendActivationMessage(Member member) {
		// TODO Auto-generated method stub
		
	}

	public EmailServiceResultBean sendRecoveryMessage(Member member) {
		EmailServiceResultBean result = new EmailServiceResultBean(true, null);
		
		try {
			MimeMessage message = emailSender.createMimeMessage();
			message.setSubject("Recuperació de paraula de pas");
			
			message.setContent(mailingGenerator.generateRecoveryEmailBody(member), "text/html");
			this.sendMessage(message, member);
		
		} catch(Exception e) {
			return new EmailServiceResultBean(false, e.getMessage());
		}
		
		return result;
		
	}
	
	@Data
	public class EmailServiceResultBean extends ResultBean{
		
		private EmailServiceResultBean(boolean ok, String message) {
			this.ok = ok;
			this.error = message;
		}
	}
}
