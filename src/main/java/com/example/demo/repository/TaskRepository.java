package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
	Optional<Task> findById(Integer id);

	void deleteById(Integer id);

	List<Task> findAll(Sort sort);

	@Query(value = "SELECT * FROM tasks WHERE CAST(ganbari AS INTEGER) <= 40", nativeQuery = true)
	List<Task> findByGanbariLessThanEqual40();

}