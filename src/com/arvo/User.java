package com.arvo;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 7836339752579005979L;
	private String name;
	private String address;
	private Integer age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

}
