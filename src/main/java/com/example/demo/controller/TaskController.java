package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.repository.TaskRepository;

@Controller
public class TaskController {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	User user;

	@GetMapping("/task")
	public String task(
			Model model,
			HttpSession session) {

		User sessionUser = (User) session.getAttribute("user");
		model.addAttribute("user", sessionUser);

		List<Task> tasks = taskRepository.findAll(Sort.by(Sort.Order.asc("id")));
		model.addAttribute("tasks", tasks);
		return "task";
	}

	@PostMapping("/task/new")
	public String tasknew(
			Model model) {

		model.addAttribute("date", LocalDate.now());

		return "taskNew";
	}

	@PostMapping("/task/add")
	public String Taskcreate(

			@RequestParam(name = "content", defaultValue = "") String content,
			@RequestParam(name = "ganbari", required = false) String ganbari,
			@RequestParam(name = "memo", defaultValue = "") String memo,
			Model model) {

		List<String> errList = new ArrayList<>();

		if (content.equals("")) {
			errList.add("運動内容は必須です");
			model.addAttribute("date", LocalDate.now());
			model.addAttribute("content", content);

		} else if (content.length() < 3) {
			errList.add("運動内容は3文字以上で入力してください");
			model.addAttribute("date", LocalDate.now());
			model.addAttribute("content", content);
		} else if (content.length() > 10) {
			errList.add("運動内容は10文字以下で入力してください");
			model.addAttribute("date", LocalDate.now());
			model.addAttribute("content", content);
		}
		if (ganbari == null || ganbari.trim().isEmpty()) {
			errList.add("頑張り度は必須です");
			model.addAttribute("date", LocalDate.now());
			model.addAttribute("content", content);
		} else {
			int ganbariValue = Integer.parseInt(ganbari.trim());

			if (ganbariValue < 1 || ganbariValue > 100) {
				errList.add("頑張り度は1～100の間で入力してください");
				model.addAttribute("date", LocalDate.now());
				model.addAttribute("content", content);
			}
		}

		if (!errList.isEmpty()) {
			model.addAttribute("errList", errList);
			return "taskNew";
		}
		Task task = new Task(content, ganbari, memo);
		taskRepository.save(task);

		return "redirect:/task";
	}

	@GetMapping("/task/{id}/detail")
	public String taskdetail(
			@PathVariable("id") Integer id,

			Model model) {

		Task task = taskRepository.findById(id).get();
		model.addAttribute("task", task);

		return "taskDetail";
	}

	@GetMapping("/task/{id}/update")
	public String taskupdate(
			@PathVariable("id") Integer id,
			Model model) {

		Task task = taskRepository.findById(id).get();

		model.addAttribute("task", task);

		return "taskUpdate";
	}

	//	@GetMapping("/task/{id}/updatecom")
	//	public String showtaskedit(
	//			@RequestParam(name = "content", defaultValue = "") String content,
	//			@RequestParam(name = "ganbari", required = false) String ganbari,
	//			@RequestParam(name = "memo", defaultValue = "") String memo,
	//			Model model) {
	//		User user = userService.getCurrentUser(); 
	//		model.addAttribute("user", user);
	//		return "taskUpdate";
	//	}

	@PostMapping("/task/{id}/updatecom")
	public String taskedit(
			@PathVariable("id") Integer id,
			@RequestParam(name = "content", defaultValue = "") String content,
			@RequestParam(name = "ganbari", required = false) String ganbari,
			@RequestParam(name = "memo", defaultValue = "") String memo,
			Model model) {

		//			RedirectAttributes redirectAttributes) {

		Task task = taskRepository.findById(id).get();

		//		List<String> errList = new ArrayList<>();
		//
		//		if (content.equals("")) {
		//			errList.add("運動内容は必須です");
		//			model.addAttribute("date", LocalDate.now());
		//			model.addAttribute("content", content);
		//
		//		} else if (content.length() < 3) {
		//			errList.add("運動内容は3文字以上で入力してください");
		//			model.addAttribute("date", LocalDate.now());
		//			model.addAttribute("content", content);
		//		} else if (content.length() > 10) {
		//			errList.add("運動内容は10文字以下で入力してください");
		//			model.addAttribute("date", LocalDate.now());
		//			model.addAttribute("content", content);
		//		}
		//		if (ganbari.length() < 1 || ganbari.length() > 100 || ganbari.trim().equals("")) {
		//			errList.add("頑張り度は1～100の間で入力してください");
		//			model.addAttribute("date", LocalDate.now());
		//			model.addAttribute("content", content);
		//
		//		}
		//
		//		if (!errList.isEmpty()) {
		//			redirectAttributes.addFlashAttribute("errList", errList);
		//
		//			return "redirect:/task/" + task.getId() + "/updatecom";
		//		}
		task.setContent(content);
		task.setGanbari(ganbari);
		task.setMemo(memo);
		taskRepository.save(task);

		return "redirect:/task";
	}

	@GetMapping("/task/{id}/condelete")
	public String taskcondelete(
			@PathVariable("id") Integer id,
			Model model) {

		Task task = taskRepository.findById(id).get();
		model.addAttribute("task", task);

		return "conDelete";
	}

	@PostMapping("/task/{id}/delete")
	public String taskdelete(
			@PathVariable("id") Integer id) {

		taskRepository.deleteById(id);

		return "redirect:/task";
	}

}