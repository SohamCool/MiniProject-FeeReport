package com.Accountant;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Accountant implements Serializable {
	private static final long serialVersionUID = 611853102785260869L;
private int id;
private String name,pwd,email,phone;
public Accountant(){}
public Accountant(int id, String name, String pwd, String email, String phone) {
	this.id = id;
	this.name =name;
	this.pwd = pwd;
	this.email = email;
	this.phone = phone;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
}
