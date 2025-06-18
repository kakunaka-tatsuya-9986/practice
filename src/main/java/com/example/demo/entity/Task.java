package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")

public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDateTime date;

	@Column(length = 255)
	private String content;

	private String ganbari;

	@Column(length = 255)
	private String memo;

	public Task() {
	}

	public Task(String content, String ganbari, String memo) {
		this.content = content;
		this.ganbari = ganbari;
		this.memo = memo;
		this.date = LocalDateTime.now();
	}

	public Integer getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public String getMemo() {
		return memo;
	}

	public String getGanbari() {
		return ganbari;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setGanbari(String ganbari) {
		this.ganbari = ganbari;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}
