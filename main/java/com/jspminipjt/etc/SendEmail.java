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
		// SMTP (Simple Mail Transfer Protocol) : 메일 전송 통신규약

		Properties props = new Properties();
		
		String subject = "GOOTT 5강의실에서 보낸 이메일 인증번호 입니다.";
		String message = "<h1>DISTINCTAO<h1>" 
						+ "<p>인증코드 : < " + code + " >를 입력하시고 회원가입을 완료하세요.</p>"
						+ "<div><a href='http://localhost:8084/JSPMiniProject/'> 홈페이지로 이동 </a></div>";
		
		// gamail / naver 서버에 따르는 SMTP 환경 설정
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
		
		System.out.println("mail 세션 : " + mailSession.toString());
		
		if (mailSession != null) {
			MimeMessage mime = new MimeMessage(mailSession);
			
//			mime.setFrom(new InternetAddress("goott.dbtest@gamil.com")); // 보내는 메일 주소
			mime.setFrom(new InternetAddress("goott_dbtest@naver.com")); // 보내는 메일 주소
			mime.addRecipient(RecipientType.TO, new InternetAddress(emailAddr)); // 받는 사람

			mime.setSubject(subject);
			mime.setText(message, "utf-8", "html");
			
			Transport trans = mailSession.getTransport("smtp");
			trans.connect(EmailAccount.emailAddr, EmailAccount.emailPwd);
			trans.sendMessage(mime, mime.getAllRecipients()); // 발송
			trans.close();

		}

	}
	

}
