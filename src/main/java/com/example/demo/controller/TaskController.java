package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;

@Controller
public class TaskController {

	@Autowired
	TaskRepository taskRepository;

	@GetMapping("/task")
	public String task(
			Model model) {

		List<Task> task = taskRepository.findAll();
		model.addAttribute("tasks", task);

		return "task";
	}

	@GetMapping("/task/new")
	public String tasknew(
			Model model) {

		model.addAttribute("date", LocalDate.now());

		return "taskNew";
	}

	@PostMapping("/task/add")
	public String Taskcreate(
			//			@PathVariable(name = "id") Integer id,
			@RequestParam(name = "content", defaultValue = "") String content,
			@RequestParam(name = "ganbari", required = false) Integer ganbari,
			@RequestParam(name = "memo", defaultValue = "") String memo,
			Model model) {
		Task task = new Task(content, ganbari, memo);

		if (content.equals("") || ganbari == null) {
			model.addAttribute("err", "フォームを正しく入力してください");
			model.addAttribute("date", LocalDate.now());
			return "taskNew";
		}

		taskRepository.save(task);

		return "redirect:/task";
	}

	@GetMapping("/task/{id}/detail")
	public String taskdetail(
			@PathVariable("id") Integer id,
			Model model) {

		Task task = taskRepository.findById(id).orElse(null);
		model.addAttribute("task", task);

		return "taskDetail";
	}

	@GetMapping("/task/{id}/update")
	public String taskupdate(
			@PathVariable("id") Integer id,
			Model model) {

		Task task = taskRepository.findById(id).orElse(null);
		model.addAttribute("task", task);

		return "taskUpdate";
	}

	@PostMapping("/task/{id}/updatecom")
	public String taskedit(
			@PathVariable("id") Integer id,
			@RequestParam(name = "content", defaultValue = "") String content,
			@RequestParam(name = "ganbari", required = false) Integer ganbari,
			@RequestParam(name = "memo", defaultValue = "") String memo,

			Model model) {
		Task task = taskRepository.findById(id).get();

		task.setContent(content);
		task.setGanbari(ganbari);
		task.setMemo(memo);

		return "redirect:/task";
	}

	@PostMapping("/task/{id}/delete")
	public String taskdelete(
			@PathVariable("id") Integer id,
			Model model) {

		taskRepository.deleteById(id);

		return "redirect:/task";
	}

}