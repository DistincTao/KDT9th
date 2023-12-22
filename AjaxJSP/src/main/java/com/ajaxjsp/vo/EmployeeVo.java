package com.ajaxjsp.vo;

import java.sql.Date;

public class EmployeeVo {
	
	private int employee_id;
	private String fisrt_name;
	private String last_name;
	private String email;
	private String phone_number;
	private Date hire_date;
	private String job_id;
	private double salary;
	private double commition_pct;
	private int manager_id;
	private int department_id;
	private String department_name;
	
	
	
	public EmployeeVo() {
		super();
	}

	public EmployeeVo(int employee_id, String fisrt_name, String last_name, String email, String phone_number,
			Date hire_date, String job_id, double salary, double commition_pct, int manager_id,
			int department_id, String department_name) {
		super();
		this.employee_id = employee_id;
		this.fisrt_name = fisrt_name;
		this.last_name = last_name;
		this.email = email;
		this.phone_number = phone_number;
		this.hire_date = hire_date;
		this.job_id = job_id;
		this.salary = salary;
		this.commition_pct = commition_pct;
		this.manager_id = manager_id;
		this.department_id = department_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public void setFisrt_name(String fisrt_name) {
		this.fisrt_name = fisrt_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}

	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setCommition_pct(double commition_pct) {
		this.commition_pct = commition_pct;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public String getFisrt_name() {
		return fisrt_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public Date getHire_date() {
		return hire_date;
	}

	public String getJob_id() {
		return job_id;
	}

	public double getSalary() {
		return salary;
	}

	public double getCommition_pct() {
		return commition_pct;
	}

	public int getManager_id() {
		return manager_id;
	}

	public int getDepartment_id() {
		return department_id;
	}

	public String getDepartment_name() {
		return department_name;
	}

	@Override
	public String toString() {
		return "EmployeeVo [employee_id=" + employee_id + ", fisrt_name=" + fisrt_name + ", last_name=" + last_name
				+ ", email=" + email + ", phone_number=" + phone_number + ", hire_date=" + hire_date + ", job_id="
				+ job_id + ", salary=" + salary + ", commition_pct=" + commition_pct + ", manager_id=" + manager_id
				+ ", department_id=" + department_id + "]";
	}

	
	
}
