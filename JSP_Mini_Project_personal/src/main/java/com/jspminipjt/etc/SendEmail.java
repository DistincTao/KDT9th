package com.jspminipjt.etc;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class SendEmail {
	public static void sendEmail(String emailAddr, String code) throws AddressException, MessagingException {
		// SMTP  : 메일 전송 통신 규약
		
		// Properties에 맵 형식으로 이메일을 발송할 SMTP의 설정을
		Properties props = new Properties();
		
		String subjcet = "Goott 5강의실에서 Test용으로 보낸 인증번호 입니다.";
		String message = "<h1>DISTINCTAO<h1>"
						+ "<p> 인증코드 : < <span style='font-weight : bold;'>" + code + "</span> > 를 입력하시고 회원가입을 완료하세요.</p>"
						+ "<div style='font-size : 14px;'><a href='http://localhost:8084/JSPMiniProject/'> 홈페이지로 이동 </a></div>";
		
		// 메일 서버에 따른 SMTP 환경 설정
		props.put("mail.smtp.starttls.required", "true"); // 메일 서버 환경 설정 시작
		props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // 사용될 ssl 보안 프로토콜 설정
//		props.put("mail.smtp.host", "smtp.gmail.com"); // smtp 서버 호스트 이름
		props.put("mail.smtp.host", "smtp.naver.com"); // smtp 서버 호스트 이름
		props.put("mail.smtp.port", "465"); // smpt 포트 번호
		props.put("mail.smtp.auth", "true"); // 인증과정 진행 여부
		props.put("mail.smtp.ssl.enable", "true"); // ssl 사용 여부
		
		Session mailSession = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EmailAccount.emailAddr, EmailAccount.emailPwd);
			}
		});
		
		if (mailSession != null) {
			MimeMessage mime = new MimeMessage(mailSession);
			
			mime.setFrom(new InternetAddress("goott_dbtest@naver.com"));
			mime.addRecipient(RecipientType.TO, new InternetAddress(emailAddr));
			
			mime.setSubject(subjcet);
			mime.setText(message, "utf-8", "html");
			
			Transport trans = mailSession.getTransport("smtp");
			trans.connect(EmailAccount.emailAddr, EmailAccount.emailPwd);
			trans.sendMessage(mime, mime.getAllRecipients());
			trans.close();
			
			
		}
		
	}
}
