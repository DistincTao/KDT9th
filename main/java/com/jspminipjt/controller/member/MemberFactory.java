package com.jspminipjt.controller.member;

import com.jspminipjt.service.MemberService;
import com.jspminipjt.service.member.ConfirmEmailCodeService;
import com.jspminipjt.service.member.DuplicateUserIdService;
import com.jspminipjt.service.member.LoginMemberService;
import com.jspminipjt.service.member.LogoutMemberService;
import com.jspminipjt.service.member.MyPageService;
import com.jspminipjt.service.member.RegisterMemberService;
import com.jspminipjt.service.member.SendMailService;

public class MemberFactory {
	private static MemberFactory instance;
	
	private boolean isRedirect;
	private String whereToGo;
	
	public MemberFactory () {}
	
	public static MemberFactory getInstance() {
		if (instance == null) {
			instance = new MemberFactory();
		}
		
		return instance;
	}
	
	public static void setInstance(MemberFactory instance) {
		MemberFactory.instance = instance;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	public void setWhereToGo(String whereToGo) {
		this.whereToGo = whereToGo;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public String getWhereToGo() {
		return whereToGo;
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
		} else if (command.equals("/member/login.mem")) {
			result = new LoginMemberService();
		} else if (command.equals("/member/logout.mem")) {
			result = new LogoutMemberService();
		} else if (command.equals("/member/myPage.mem")) {
			result = new MyPageService();
		}
		
		return result;
	}
}
