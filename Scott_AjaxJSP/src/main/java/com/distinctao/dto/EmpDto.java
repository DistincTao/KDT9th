package com.distinctao.dto;

import java.sql.Date;

public class EmpDto {
	private String ename;
	private String job;
	private int mgr;
	private Date hiredate;
	private double sal;
	private double comm;
	private int deptno;
	
	public EmpDto() {
		super();
	}

	public EmpDto(String ename, String job, int mgr, Date hiredate, double sal, double comm, int deptno) {
		super();
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.hiredate = hiredate;
		this.sal = sal;
		this.comm = comm;
		this.deptno = deptno;
	}

	public String getEname() {
		return ename;
	}

	public String getJob() {
		return job;
	}

	public int getMgr() {
		return mgr;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public double getSal() {
		return sal;
	}

	public double getComm() {
		return comm;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}

	public void setComm(double comm) {
		this.comm = comm;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	@Override
	public String toString() {
		return "EmpDto [ename=" + ename + ", job=" + job + ", mgr=" + mgr + ", hiredate=" + hiredate + ", sal=" + sal
				+ ", comm=" + comm + ", deptno=" + deptno + "]";
	}
	
	
}
