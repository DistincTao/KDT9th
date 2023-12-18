package com.jspbasic.dto;

import java.sql.Date;

public class MemberDTO {
	private String userId;
	private String psw1;
	private String email;
	private String mobile;
	private Date bDate;
	private int age;
	private String gender;
	private String hobby;
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

	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	private String job;
	private String memo;
	
	public MemberDTO () {
		super();
	}

	public MemberDTO(String userId, String psw1, String email, String mobile, Date bDate, int age, String gender,
			String hobby, String job, String memo) {
		this.userId = userId;
		this.psw1 = psw1;
		this.email = email;
		this.mobile = mobile;
		this.bDate = bDate;
		this.age = age;
		this.gender = gender;
		this.hobby = hobby;
		this.job = job;
		this.memo = memo;
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

	public Date getbDate() {
		return bDate;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public String getHobby() {
		return hobby;
	}

	public String getJob() {
		return job;
	}

	public String getMemo() {
		return memo;
	}

	@Override
	public String toString() {
		return "userId : " + userId + ", password : " + psw1 + ", Email : " + email + ", Mobile : " + mobile + ", Birthday : "
				+ bDate + ", Age : " + age + ", Gender : " + gender + ", Hobby : " + hobby + ", Job : " + job + ", Memo : " + memo;
	}

}
