package com.anupam.CovidPortal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user_table")
public class RegisterModel {

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

	@NotBlank(message = "Address is not be empty")
	@Column
	private String address;

	@NotBlank(message = "Password field is Required")
	@Column
	private String password;

	@Transient
	private String confirmPassword;

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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "RegisterModel [id=" + id + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", age=" + age
				+ ", address=" + address + ", password=" + password + ", confirmPassword=" + confirmPassword + "]";
	}
}
