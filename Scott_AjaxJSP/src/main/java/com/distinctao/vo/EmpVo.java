package com.distinctao.vo;

import java.sql.Date;

public class EmpVo {
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private Date hiredate;
	private double sal;
	private double comm;
	private int deptno;
	private Date quitdate;
	private String dname;
	

	public EmpVo() {
		super();
	}
	
	public EmpVo(int empno, String ename, String job, int mgr, Date hiredate, double sal, double comm, int deptno, String dname) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.hiredate = hiredate;
		this.sal = sal;
		this.comm = comm;
		this.deptno = deptno;
		this.dname = dname;
	}
	public EmpVo(int empno, String ename, String job, int mgr, Date hiredate, double sal, double comm, int deptno, Date quitdate, String dname) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.hiredate = hiredate;
		this.sal = sal;
		this.comm = comm;
		this.deptno = deptno;
		this.dname = dname;
		this.quitdate = quitdate;
	}

	public int getEmpno() {
		return empno;
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
	
	public String getDname() {
		return dname;
	}

	public Date getQuitdate() {
		return quitdate;
	}

	@Override
	public String toString() {
		return "EmpVo [empno=" + empno + ", ename=" + ename + ", job=" + job + ", mgr=" + mgr + ", hiredate=" + hiredate
				+ ", sal=" + sal + ", comm=" + comm + ", deptno=" + deptno + ", dname=" + dname + ", quitdate="
				+ quitdate + "]";
	}
	
}
