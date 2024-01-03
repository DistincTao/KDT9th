package com.jspminipjt.controller;

import com.jspminipjt.service.MemberService;
import com.jspminipjt.service.member.ConfirmEmailCodeService;
import com.jspminipjt.service.member.DuplicateUserIdService;
import com.jspminipjt.service.member.RegisterMemberService;
import com.jspminipjt.service.member.SendMailService;

public class MemberFactory {
	private static MemberFactory instance;
	
	public MemberFactory () {}
	
	public static MemberFactory getInstance() {
		if (instance == null) {
			instance = new MemberFactory();
		}
		
		return instance;
	}
	
	public MemberService getService(String command) {
		MemberService result = null;
		
		if (command.equals("/member/duplicateUserId.mem")) {
			result = new DuplicateUserIdService();
		} else if (command.equals("/member/registerMember.mem")){
			result = new RegisterMemberService();
		} else if (command.equals("/member/sendEmail.mem")){
			result = new SendMailService();
		} else if (command.equals("/member/confirmCode.mem")) {
			result= new ConfirmEmailCodeService();
		}
		
		return result;
	}
}
