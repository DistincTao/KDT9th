package com.distinctao.vo;

public class DeptVo {
	private int deptNo;
	private String dname;
	private String loc;
	
	public DeptVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeptVo(int deptNo, String dname, String loc) {
		super();
		this.deptNo = deptNo;
		this.dname = dname;
		this.loc = loc;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public String getDname() {
		return dname;
	}

	public String getLoc() {
		return loc;
	}

	@Override
	public String toString() {
		return "DeptVo [deptNo=" + deptNo + ", dname=" + dname + ", loc=" + loc + "]";
	}
	
	
}
