package com.ajaxjsp.dto;

import java.sql.Date;

public class EmployeeByNameDto {
	
	private String first_name;
	private String last_name;

	public EmployeeByNameDto() {
		super();
	}

	public EmployeeByNameDto(String first_name, String last_name, String email, String phone_number,
			Date hire_date, String job_id, double salary, double commition_pct, int manager_id,
			int department_id) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;

	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}



	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	@Override
	public String toString() {
		return "EmployeeByNameDto [first_name=" + first_name + ", last_name=" + last_name + "]";
	}



	
	
}
