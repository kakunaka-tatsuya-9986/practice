package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.User;
import com.example.demo.repository.TaskRepository;

@Controller
public class UserController {

	@Autowired
	TaskRepository taskRepository;

	//	@Autowired
	//	UserRepository userRepository;

	@GetMapping("/users")
	public String index() {

		return "user";

	}

	@PostMapping("/users/new")
	public String creuser() {
		return "creUser";
	}

	@PostMapping("/users/login")
	public String user() {
		return "logUser";
	}

	@PostMapping("/task/top")
	public String task(
			@RequestParam("name") String name,
			@RequestParam("password") String password,
			Model model) {

		User user = new User(name, password);

		if (name.equals("") || password.equals("")) {
			model.addAttribute("err", "フォームを正しく入力してください");
			model.addAttribute("name", name);
			model.addAttribute("password", password);
			return "logUser";
		} else {
			model.addAttribute(user);
			//			userRepository.save(user);

		}
		return "task";
	}

	@PostMapping("/task/cre")
	public String cretask(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "id", defaultValue = "") Integer id,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model) {

		User user = new User(name, password, id);

		if (name.equals("") || password.equals("") || id == null) {
			model.addAttribute("err", "フォームを正しく入力してください");
			model.addAttribute("name", name);
			model.addAttribute("password", password);
			return "creUser";
		} else {
			model.addAttribute(user);
		}
		return "task";
	}

}