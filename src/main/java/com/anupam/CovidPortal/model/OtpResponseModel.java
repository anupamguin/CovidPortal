package com.anupam.CovidPortal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user_final_table")
public class OtpResponseModel {

	@Id
	private int id;

	@Column
	@NotBlank(message = "Name is Required")
	private String name;

	@Email(message = "Invalid Email")
	@NotBlank(message = "Email is not be Blank")
	@Column(unique = true,length=32)
	private String email;

	@NotBlank(message = "Mobile is not be empty")
	@Column()
	private String mobile;

	@Column()
	private int age;
	
	@NotBlank(message = "Password field is Required")
	@Column
	private String password;


	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "OtpResponse [id=" + id + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", age=" + age
				+ ", password=" + password + "]";
	}

	public OtpResponseModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OtpResponseModel(int id, @NotBlank(message = "Name is Required") String name,
			@Email(message = "Invalid Email") @NotBlank(message = "Email is not be Blank") String email,
			@NotBlank(message = "Mobile is not be empty") String mobile, int age,
			@NotBlank(message = "Password field is Required") String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.age = age;
		this.password = password;
	}
}
