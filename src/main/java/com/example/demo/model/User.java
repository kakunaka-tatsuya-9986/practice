package com.example.demo.model;

//@Entity
public class User {

	//	@Id
	//	private Integer id;

	private String name;
	private String password;
	private Integer id;

	public User() {

	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public User(String name, String password, Integer id) {
		this.name = name;
		this.password = password;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String setName(String name) {
		return this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public Integer getusId() {
		return id;
	}

	public Integer setId(Integer id) {
		return this.id = id;
	}

}
