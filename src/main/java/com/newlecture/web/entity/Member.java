package com.newlecture.web.entity;

public class Member // userInfo DTO 
{
	private String id;
	private String password;
	private String name;
	private String gender;
	private String birthday;
	private String phone;
	private String email;
	
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String id_in,String password_in) {
		// TODO Auto-generated constructor stub
		this.id = id_in;
		this.password = password_in;
		
	}
	
	public Member(String id_in,String password_in,String name_in,String gender_in,String birthday_in,String phone_in,String email_in) {
		// TODO Auto-generated constructor stub
		this.id = id_in;
		this.password = password_in;
		this.name = name_in;
		this.gender =gender_in;
		this.birthday= birthday_in;
		this.phone = phone_in;
		this.email = email_in;
		
	}
	

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}