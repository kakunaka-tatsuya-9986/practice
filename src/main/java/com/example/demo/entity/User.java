package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Entity
@Table(name = "users")

@Component
@SessionScope
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String password;
	private Integer usid;

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

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getusId() {
		return usid;
	}

	public void setusId(Integer usid) {
		this.usid = usid;
	}

}
