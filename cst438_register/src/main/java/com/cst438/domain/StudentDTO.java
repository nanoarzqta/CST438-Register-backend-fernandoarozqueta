package com.cst438.domain;

public class StudentDTO {

	public int id;
	public String studentEmail;
	public String studentName;
	public String studentStatus;
	public int statusCode;
	
	public StudentDTO() {
		this.id = 0;
		this.studentEmail=null;
		this.studentName=null;
		this.studentStatus=null;
		this.statusCode = 0;
	}
	
	
	public StudentDTO(String studentEmail, String studentName, String studentStatus, int statusCode) {
		this.id = 0;
		this.studentEmail=studentEmail;
		this.studentName=studentName;
		this.studentStatus = studentStatus;
		this.statusCode = statusCode;
	}


	@Override
	public String toString() {
		return "StudentDTO [id=" + id + ", studentEmail=" + studentEmail + ", studentName=" + studentName
				+ " studentStatus=" + studentStatus + ", statusCode=" + statusCode + "]";
	}
	
}