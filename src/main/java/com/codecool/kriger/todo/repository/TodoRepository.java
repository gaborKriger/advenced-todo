package com.codecool.kriger.todo.repository;

import com.codecool.kriger.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
