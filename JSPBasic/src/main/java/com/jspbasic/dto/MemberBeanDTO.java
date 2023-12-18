package com.jspbasic.dto;

public class MemberBeanDTO {
	private String userId;
	private String psw1;
	private String email;
	private String mobile;
	
	public MemberBeanDTO () {
		super();
	}

	public MemberBeanDTO(String userId, String psw1, String email, String mobile) {
		this.userId = userId;
		this.psw1 = psw1;
		this.email = email;
		this.mobile = mobile;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPsw1(String psw1) {
		this.psw1 = psw1;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getUserId() {
		return userId;
	}

	public String getPsw1() {
		return psw1;
	}

	public String getEmail() {
		return email;
	}

	public String getMobile() {
		return mobile;
	}


	@Override
	public String toString() {
		return "userId : " + userId + ", password : " + psw1 + ", Email : " + email + ", Mobile : " + mobile;
	}

}
