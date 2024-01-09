package com.jspminipjt.vo.member;

import java.sql.Date;

public class MemberPointVo {
	private int pointlogNo;
	private Date actionDate;
	private String pointType;
	private int changePoint;
	private String UserId;
	
	public MemberPointVo(int pointlogNo, Date actionDate, String pointType, int changePoint, String userId) {
		super();
		this.pointlogNo = pointlogNo;
		this.actionDate = actionDate;
		this.pointType = pointType;
		this.changePoint = changePoint;
		UserId = userId;
	}

	public MemberPointVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getPointlogNo() {
		return pointlogNo;
	}

	public Date getActionDate() {
		return actionDate;
	}

	public String getPointType() {
		return pointType;
	}

	public int getChangePoint() {
		return changePoint;
	}

	public String getUserId() {
		return UserId;
	}

	@Override
	public String toString() {
		return "MemberPointVo [pointlogNo=" + pointlogNo + ", actionDate=" + actionDate + ", pointType=" + pointType
				+ ", changePoint=" + changePoint + ", UserId=" + UserId + "]";
	}
	
	
}
