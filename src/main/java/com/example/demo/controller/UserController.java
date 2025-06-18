package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	HttpSession httpSession;

	@GetMapping("/users")
	public String index(
			HttpSession session) {

		session.invalidate();

		return "user";

	}

	@GetMapping("/users/new")
	public String showcreuser() {

		return "creUser";
	}

	@GetMapping("/users/login")
	public String showloguser() {

		return "logUser";
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
			Model model,
			HttpSession session) {

		List<String> errList = new ArrayList<>();

		if (name.trim().isEmpty()) {
			errList.add("名前は必須です");
		}

		if (password.trim().isEmpty()) {
			errList.add("パスワードは必須です");
		}

		if (!errList.isEmpty()) {
			model.addAttribute("errList", errList);
			model.addAttribute("name", name);
			return "logUser";
		}

		List<User> users = userRepository.findByNameAndPassword(name, password);

		if (!users.isEmpty()) {

			User user = users.get(0);
			session.setAttribute("user", user);
			model.addAttribute("user", user);

			return "task";
		} else {
			errList.add("名前またはパスワードが間違っています");
			model.addAttribute("errList", errList);
			model.addAttribute("name", name);

			return "logUser";

		}

	}

	@PostMapping("/users/complete")
	public String completeuser(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "usid", required = false) Integer usid,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model,
			HttpSession session) {

		User user = new User(name, password, usid);
		userRepository.save(user);
		session.setAttribute("user", user);
		model.addAttribute("user", user);

		return "compUser";
	}

	@PostMapping("/task/cre")
	public String conuser(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "usid", required = false) Integer usid,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model,
			HttpSession session) {

		User user = new User(name, password, usid);
		userRepository.save(user);
		session.setAttribute("user", user);
		model.addAttribute("user", user);

		List<Task> tasks = taskRepository.findAll();
		model.addAttribute("tasks", tasks);

		return "task";
	}

	@PostMapping("/users/con")
	public String creuser(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "usid", required = false) Integer usid,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model,
			HttpSession session) {

		List<String> errList = new ArrayList<>();

		if (name.trim().isEmpty()) {
			errList.add("名前フォームは必須です");

		} else if (name.length() < 3) {
			errList.add("名前は3文字以上で設定してください");

		} else if (name.length() > 10) {
			errList.add("名前は10文字以下で設定してください");

		}
		if (password.trim().isEmpty()) {
			errList.add("パスワードフォームは必須です");

		} else if (password.length() < 5) {
			errList.add("パスワードは5文字以上で設定してください");

		} else if (password.length() > 20) {
			errList.add("パスワードは20文字以下で設定してください");

		}
		if (usid == null) {
			errList.add("IDフォームは必須です");

		} else if (String.valueOf(usid).length() > 9) {
			errList.add("IDは10文字以下の数字で設定してください");

		} else if (String.valueOf(usid).length() < 5) {
			errList.add("IDは5文字以上の数字で設定してください");

		}

		if (!errList.isEmpty()) {
			model.addAttribute("errList", errList);
			model.addAttribute("name", name);
			model.addAttribute("usid", usid);
			return "creUser";
		}
		User user = new User(name, password, usid);
		model.addAttribute("user", user);

		session.setAttribute("user", user);

		return "conUser";
	}
}